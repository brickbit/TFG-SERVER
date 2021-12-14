package com.epcc.politech_manager.schedule

import org.apache.poi.ss.usermodel.*
import org.apache.poi.ss.util.CellRangeAddress
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.springframework.stereotype.Service
import java.io.File
import java.io.FileOutputStream
import kotlin.math.ceil

@Service
class OneSubjectScheduleService {
    val scheduleData = createBuildingSchedule()
    val type = FileType.CLASSROOM

    fun parseScheduleData():ScheduleFileData {
        val matrix = flatMatrix(scheduleData.semester.subjectsInSemester)//multiple
        val matrixWithNoEmptyColumns = deleteEmptyCols(matrix)//multiple
        val deletedCols = getDeletedCols(matrix)//multiple
        val subjectsForWeek = obtainSubjects(matrixWithNoEmptyColumns)//multiple
        val sizeOfDays = getSizeOfEachDay(deletedCols)//multiple
        val emptyRows = checkTurnEmpty(matrix)
        val emptyMorning = emptyMorning(emptyRows,matrixWithNoEmptyColumns)
        val emptyAfternoon = emptyAfternoon(emptyRows,matrixWithNoEmptyColumns)
        return ScheduleFileData(scheduleData.degrees.name,scheduleData.semester.num,emptyAfternoon.first,subjectsForWeek,sizeOfDays,emptyMorning.second,emptyAfternoon.second, scheduleData.year)
    }

    fun createFile() {
        val scheduleData = parseScheduleData()

        val workbook: Workbook = XSSFWorkbook()
        val sheet: Sheet = workbook.createSheet(scheduleData.degree)

        paint(scheduleData, sheet, workbook)
        when(type) {
            FileType.SUBJECT -> {closeFile(workbook, "schedule-subject.xlsx")}
            FileType.DEPARTMENT -> {closeFile(workbook, "schedule-department.xlsx")}
            FileType.CLASSROOM -> {closeFile(workbook, "schedule-classroom.xlsx")}
        }
    }

    private fun paint(scheduleFileData: ScheduleFileData, sheet: Sheet, workbook: Workbook) {
        createRows(scheduleFileData,sheet)
        createTitle(scheduleFileData,sheet,workbook)
        createHeader(scheduleFileData,sheet.getRow(headerOffset),workbook,sheet)
        createHoursRow(scheduleFileData,sheet,workbook)
        createSubjectHeader(scheduleFileData,sheet.getRow(headerOffset + 1), workbook)
        fillData(scheduleFileData,sheet,workbook)
    }

    private fun createRows(scheduleFileData: ScheduleFileData, sheet: Sheet) {
        val numRows = scheduleFileData.subjects.size + headerOffset + headerSize
        for (i in 0..numRows) {
            sheet.createRow(i)
            sheet.setColumnWidth(i, 5500)
        }
    }

    private fun createTitle(scheduleFileData: ScheduleFileData, sheet: Sheet, workbook: Workbook) {
        val middle = scheduleFileData.subjects[0].size/2
        val row = sheet.createRow(0)
        var cell = row.createCell(0)
        cell.setCellValue("Curso")
        cell.cellStyle = setStyle(workbook,11,CellColor.WHITE,true)
        cell = row.createCell(1)
        cell.setCellValue(ceil((scheduleFileData.semester+1)/2.0).toInt().toString())
        cell.cellStyle = setStyle(workbook,11,CellColor.WHITE,true)
        cell = row.createCell(middle)
        cell.setCellValue(scheduleFileData.degree.toUpperCase())
        cell.cellStyle = setStyle(workbook,11,CellColor.WHITE,true)
        cell = row.createCell(scheduleFileData.subjects[0].size)
        cell.setCellValue(scheduleFileData.year)
        cell.cellStyle = setStyle(workbook,11,CellColor.WHITE,true)
    }

    private fun createHeader(scheduleFileData: ScheduleFileData, row: Row, workbook: Workbook, sheet: Sheet){
        val days = mutableListOf("Lunes","Martes","Miércoles","Jueves","Viernes")

        days.add(0,"")
        days.mapIndexed {  index, value ->
            for (i in 1..days.size) {
                val headerCell: Cell = row.createCell(index)
                headerCell.setCellValue(value)
                headerCell.cellStyle = setStyle(workbook,10,CellColor.WHITE, true)
            }
        }
        val list = scheduleFileData.sizeOfDays.toMutableList()
        list.add(0,0)
    }

    private fun fillDaysOfWeek(scheduleFileData: ScheduleFileData, numDays: Int, days: List<String>): MutableList<String> {
        val sizeOfDays = scheduleFileData.sizeOfDays
        val list = MutableList(numDays) { "" }
        var previousSize = 0
        sizeOfDays.mapIndexed { i,size ->
            for (j in previousSize..(size + previousSize)) {
                if(j < list.size) {
                    list[j] = days[i]
                }
            }
            previousSize += size

        }
        return list
    }

    private fun createHoursRow(scheduleFileData: ScheduleFileData, sheet: Sheet, workbook: Workbook) {
        val style = setStyle(workbook,8,CellColor.WHITE,false)

        val hours = listOf("","HORA","8:30\n","9:00\n","9:30\n","10:00\n","10:30\n","11:00\n","11:30\n","12:00\n","12:30\n","13:00\n","13:30\n","14:00\n","15:30\n","16:00\n","16:30\n","17:00\n","17:30\n","18:00\n","18:30\n","19:00\n","19:30\n","20:00\n","20:30\n","21:00\n")
        val size = scheduleFileData.subjects.size
        hours.mapIndexed { index, value ->
            if(index < size+ headerOffset) {
                val row: Row = sheet.getRow(index + headerOffset)
                val cell: Cell = row.createCell(0)
                cell.setCellValue(value)
                cell.cellStyle = style
            }
        }
    }

    private fun createSubjectHeader(scheduleFileData: ScheduleFileData, row: Row, workbook: Workbook) {
        val subjectsOfWeek = scheduleFileData.subjectsName
        for (i in 1..subjectsOfWeek.size) {
            val cell = row.createCell(i)
            cell.setCellValue("Asignatura")
            cell.cellStyle = setStyle(workbook,10,CellColor.WHITE, true)
        }
    }

    private fun fillData(scheduleFileData: ScheduleFileData, sheet: Sheet, workbook: Workbook) {
        val matrix = scheduleFileData.subjects
        for (i in (headerSize+ headerOffset) until matrix.size + headerSize+ headerOffset) {
            val row = sheet.getRow(i)
            for(j in 1..matrix[0].size) {
                val cell = row.createCell(j)
                val subject = matrix[i-(headerSize+ headerOffset)][j-1]

                when(type) {
                    FileType.SUBJECT -> {
                        val seminary: String = if (subject?.seminary == true) { "sem\n" } else { "" }
                        val laboratory: String = if (subject?.laboratory == true) { "lab\n" } else { "" }
                        val english = if(subject?.english == true) { "ing"} else{ "" }
                        val groupName = subject?.group ?: ""
                        cell.setCellValue( seminary + laboratory + english + (subject?.name?: "") + " " + groupName)
                    }
                    FileType.DEPARTMENT -> {
                        cell.setCellValue(subject?.department?.acronym ?: "")
                    }
                    FileType.CLASSROOM -> {
                        cell.setCellValue(subject?.classrooms?.get(0)?.acronym ?: "")
                    }
                }
                cell.cellStyle = setStyle(workbook,9,subject?.color?.toCellColor() ?: CellColor.WHITE, false)
            }
        }
    }

    private fun setStyle(workbook: Workbook,fontSize: Int, color: CellColor, bold: Boolean): CellStyle {
        val style = workbook.createCellStyle()
        when (color) {
            CellColor.BLUE -> style.fillForegroundColor = IndexedColors.LIGHT_TURQUOISE.getIndex()
            CellColor.RED -> style.fillForegroundColor = IndexedColors.CORAL.getIndex()
            CellColor.YELLOW -> style.fillForegroundColor = IndexedColors.LIGHT_YELLOW.getIndex()
            CellColor.GOLD -> style.fillForegroundColor = IndexedColors.GOLD.getIndex()
            CellColor.GREEN -> style.fillForegroundColor = IndexedColors.LIGHT_GREEN.getIndex()
            CellColor.WHITE -> style.fillForegroundColor = IndexedColors.WHITE.getIndex()
        }
        style.borderTop = BorderStyle.THIN
        style.topBorderColor = IndexedColors.GREY_40_PERCENT.getIndex()
        style.borderBottom = BorderStyle.THIN
        style.bottomBorderColor = IndexedColors.GREY_40_PERCENT.getIndex()
        style.borderLeft = BorderStyle.THIN
        style.leftBorderColor = IndexedColors.GREY_40_PERCENT.getIndex()
        style.borderRight = BorderStyle.THIN
        style.rightBorderColor = IndexedColors.GREY_40_PERCENT.getIndex()

        style.fillPattern = FillPatternType.SOLID_FOREGROUND

        val font = (workbook as XSSFWorkbook).createFont()
        font.fontName = "Comic Sans MS"
        font.fontHeightInPoints = fontSize.toShort()
        font.bold = bold
        style.alignment = HorizontalAlignment.CENTER
        style.verticalAlignment = VerticalAlignment.CENTER
        style.setFont(font)
        return style
    }

    private fun closeFile(workbook: Workbook, name: String) {
        val currDir = File(".")
        val path: String = currDir.absolutePath
        val fileLocation = path.substring(0, path.length - 1) + name

        val outputStream = FileOutputStream(fileLocation)
        workbook.write(outputStream)
        workbook.close()
    }

    private fun printMatrix(subjects: List<List<SubjectDegree?>>){
        subjects.map { day ->
            day.map { hour ->
                System.out.format("%5s", hour?.acronym ?: "null")
            }
            println()
        }
    }

    private fun print3DMatrix(subjects: List<List<List<SubjectDegree?>>>){
        subjects.map { day ->
            day.map { hour ->
                hour.map {
                    System.out.format("%5s", it?.acronym ?: "null")
                }
            }
            println()
        }
    }

    private fun makeTranspose(matrix: MutableList<MutableList<SubjectDegree?>>): MutableList<MutableList<SubjectDegree?>> {
        val row = matrix.size
        val column = matrix[0].size

        val transpose: MutableList<MutableList<SubjectDegree?>> = MutableList(column) { MutableList(row) { null } }
        for (i in 0 until row) {
            for (j in 0 until column) {
                transpose[j][i] = matrix[i][j]
            }
        }
        return transpose
    }

    private fun checkTurnEmpty(matrix: MutableList<MutableList<SubjectDegree?>>): List<Int> {
        val positions: MutableList<Int> = mutableListOf()
        matrix.mapIndexed { i, value ->
            val nulls = value.count { subject -> subject == null }
            if (nulls == numColumnsMultipleSubjectPerDay) {
                positions.add(i)
            }
        }
        return positions
    }

    private fun emptyMorning(positions: List<Int>, matrix: MutableList<MutableList<SubjectDegree?>>): Pair<MutableList<MutableList<SubjectDegree?>>,Boolean> {
        val morning = listOf(11,10,9,8,7,6,5,4,3,2,1,0)
        var isEmpty = false
        if(positions.containsAll(morning)) {
            isEmpty = true
            morning.mapIndexed { i, _ ->
                matrix.removeAt(morning[i])
            }
        }
        return Pair(matrix,isEmpty)
    }

    private fun emptyAfternoon(positions: List<Int>, matrix: MutableList<MutableList<SubjectDegree?>>): Pair<MutableList<MutableList<SubjectDegree?>>,Boolean> {
        val afternoon = listOf(23,22,21,20,19,18,17,16,15,14,13,12)
        var isEmpty = false
        if(positions.containsAll(afternoon)) {
            isEmpty = true
            afternoon.mapIndexed { i, _ ->
                matrix.removeAt(afternoon[i])
            }
        }
        return Pair(matrix,isEmpty)
    }



    private fun flatMatrix(subjects: List<List<List<SubjectDegree?>>>): MutableList<MutableList<SubjectDegree?>>{
        val matrix: MutableList<MutableList<SubjectDegree?>> = mutableListOf()
        subjects.map { day ->
            day.mapIndexed { i, hour ->
                matrix.add(mutableListOf())
                hour.map{ value ->
                    matrix[i].add(value)
                }
            }
        }
        matrix.removeIf { it.isEmpty() }
        return matrix
    }


    private fun deleteEmptyCols(matrix: MutableList<MutableList<SubjectDegree?>>): MutableList<MutableList<SubjectDegree?>> {
        val transpose = makeTranspose(matrix)
        transpose.removeIf { subjects ->
                val nulls = subjects.count { subject -> subject == null }
                return@removeIf nulls == numHourCells
        }
        return makeTranspose(transpose)
    }



    private fun obtainSubjects(matrix: MutableList<MutableList<SubjectDegree?>>): List<SubjectDegree> {
        val transpose = makeTranspose(matrix)
        val listSubjects = mutableListOf<SubjectDegree>()
        transpose.map {
            listSubjects.add(it.filterNotNull().first())
        }
        return listSubjects
    }

    private fun getDeletedCols(matrix: MutableList<MutableList<SubjectDegree?>>): List<Int> {
        val transpose = makeTranspose(matrix)
        val positions: MutableList<Int> = mutableListOf()
        transpose.mapIndexed { i, subjects ->
            val nulls = subjects.count { subject -> subject == null }
            if (nulls == numHourCells) {
                positions.add(i)
            }
        }
        return positions
    }

    private fun getSizeOfEachDay(deletedDays: List<Int>): List<Int> {
        val monday = subjectPerDay - deletedDays.count { it  in 0..14 }
        val tuesday = subjectPerDay - deletedDays.count { it in 15..29 }
        val wednesday = subjectPerDay - deletedDays.count { it in 30..44 }
        val thursday = subjectPerDay - deletedDays.count { it in 45..59 }
        val friday = subjectPerDay - deletedDays.count { it in 60..74 }
        return listOf(monday,tuesday,wednesday,thursday,friday)
    }

    companion object {
        const val numHourCells = 24
        const val subjectPerDay = 15
        const val numColumnsMultipleSubjectPerDay = 5
        const val headerOffset = 2
        const val headerSize = 2
    }
}