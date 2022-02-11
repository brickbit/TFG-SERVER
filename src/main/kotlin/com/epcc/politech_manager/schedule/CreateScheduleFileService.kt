package com.epcc.politech_manager.schedule

import com.epcc.politech_manager.subject.Subject
import com.epcc.politech_manager.utils.*
import org.apache.poi.ss.usermodel.*
import org.apache.poi.ss.util.CellRangeAddress
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.springframework.stereotype.Service
import java.io.File
import java.io.FileOutputStream
import java.util.*
import kotlin.math.ceil

@Service
class CreateScheduleFileService(
        val scheduleData: List<List<List<Subject?>>> = emptyList(),
        val fileType: FileType = FileType.SUBJECT,
        val scheduleType: ScheduleType = ScheduleType.ONE_SUBJECT,
        val degree: String = "Grado en Ingeniería informática en ingeniería de computadores",
        val year: String = ""
) {

    fun parseScheduleData():ScheduleFileData {
        val matrix = flatMatrix(scheduleData)
        val matrixWithNoEmptyColumns = deleteEmptyCols(matrix)
        val deletedCols = getDeletedCols(matrix)
        val subjectsForWeek = obtainSubjects(matrixWithNoEmptyColumns)
        val sizeOfDays = getSizeOfEachDay(deletedCols)
        val emptyRows = checkTurnEmpty(matrix)
        val emptyMorning = emptyMorning(emptyRows,matrixWithNoEmptyColumns)
        val emptyAfternoon = emptyAfternoon(emptyRows,matrixWithNoEmptyColumns)
        val indexDeletedCols = getEmptyCols(matrix)
        return ScheduleFileData(degree = degree, semester = 0, subjects = emptyAfternoon.first, subjectsName = subjectsForWeek,sizeOfDays = sizeOfDays,emptyMorning = emptyMorning.second, emptyAfternoon = emptyAfternoon.second, year = year, deletedCols = indexDeletedCols)

    }

    fun createFile(): String {
        val scheduleData = parseScheduleData()

        val workbook: Workbook = XSSFWorkbook()
        val sheet: Sheet = workbook.createSheet(scheduleData.degree)

        paint(scheduleData, sheet, workbook)
        when(fileType) {
            FileType.SUBJECT -> {
                val name = "schedule-subject.xlsx"
                closeFile(workbook, name)
                return name
            }
            FileType.DEPARTMENT -> {
                val name = "schedule-department.xlsx"
                closeFile(workbook, name)
                return name
            }
            FileType.CLASSROOM -> {
                val name = "schedule-classrooms.xlsx"
                closeFile(workbook, name)
                return name
            }
        }
    }

    private fun paint(scheduleFileData: ScheduleFileData, sheet: Sheet, workbook: Workbook) {
        when (scheduleType) {
            ScheduleType.ONE_SUBJECT -> { createRows(scheduleFileData,sheet, 5500) }
            ScheduleType.MULTIPLE_SUBJECT_MULTIPLE_CLASSROOM -> { createRows(scheduleFileData,sheet, 1500) }
        }
        createTitle(scheduleFileData,sheet,workbook)
        when (scheduleType) {
            ScheduleType.ONE_SUBJECT -> {
                createHeaderOneSubject(scheduleFileData, sheet.getRow(headerOffset), workbook)
            }
            ScheduleType.MULTIPLE_SUBJECT_MULTIPLE_CLASSROOM -> {
                createHeaderMultipleSubject(scheduleFileData, sheet.getRow(headerOffset), workbook, sheet)
            }
        }
        createHoursRow(scheduleFileData,sheet,workbook)
        createSubjectHeader(scheduleFileData,sheet.getRow(headerOffset + 1), workbook)
        fillData(scheduleFileData,sheet,workbook)
        mergeCells(scheduleFileData,sheet)
    }

    private fun createRows(scheduleFileData: ScheduleFileData, sheet: Sheet, cellWidth: Int) {
        val numRows = scheduleFileData.subjects.size + headerOffset + headerSize
        for (i in 0..numRows) {
            sheet.createRow(i)
            sheet.setColumnWidth(i, cellWidth)
        }
    }

    private fun createTitle(scheduleFileData: ScheduleFileData, sheet: Sheet, workbook: Workbook) {
        val row = sheet.createRow(0)
        var cell = row.createCell(0)
        cell.setCellValue("Curso")
        cell.cellStyle = setStyle(workbook,11, CellColor.WHITE,true)
        cell = row.createCell(1)
        cell.setCellValue(ceil((scheduleFileData.semester+1)/2.0).toInt().toString())
        cell.cellStyle = setStyle(workbook,11, CellColor.WHITE,true)
        cell = row.createCell(2)
        cell.setCellValue(scheduleFileData.degree.uppercase(Locale.getDefault()))
        cell.cellStyle = setStyle(workbook,11, CellColor.WHITE,true)
        if(scheduleType == ScheduleType.MULTIPLE_SUBJECT_MULTIPLE_CLASSROOM) {
            sheet.addMergedRegion(CellRangeAddress(0, 0, 2, 29))
        } else {
            sheet.addMergedRegion(CellRangeAddress(0, 0, 2, 4))
        }
        cell = row.createCell(scheduleFileData.subjects[0].size)
        cell.setCellValue(scheduleFileData.year)
        cell.cellStyle = setStyle(workbook,11, CellColor.WHITE,true)
        if(scheduleType == ScheduleType.MULTIPLE_SUBJECT_MULTIPLE_CLASSROOM) {
            sheet.addMergedRegion(CellRangeAddress(0, 0, scheduleFileData.subjects[0].size-1, scheduleFileData.subjects[0].size))
        }
    }

    private fun createHeaderMultipleSubject(scheduleFileData: ScheduleFileData, row: Row, workbook: Workbook, sheet: Sheet){
        val numDays = scheduleFileData.subjects[0].size
        val days = listOf("Lunes","Martes","Miércoles","Jueves","Viernes")

        val daysOfWeek = fillDaysOfWeek(scheduleFileData,numDays,days)
        daysOfWeek.add(0,"")
        daysOfWeek.mapIndexed {  index, value ->
            for (i in 1..daysOfWeek.size) {
                val headerCell: Cell = row.createCell(index)
                headerCell.setCellValue(value)
                headerCell.cellStyle = setStyle(workbook,10, CellColor.WHITE, true)
            }
        }
        val list = scheduleFileData.sizeOfDays.toMutableList()
        list.add(0,0)
        var previousValue = 0

        for(i in 1 until list.size) {
            sheet.addMergedRegion(CellRangeAddress(row.rowNum, row.rowNum, previousValue +1, list[i] + previousValue))
            previousValue += list[i]
        }
    }

    private fun createHeaderOneSubject(scheduleFileData: ScheduleFileData, row: Row, workbook: Workbook){
        val days = mutableListOf("Lunes","Martes","Miércoles","Jueves","Viernes")

        days.add(0,"")
        days.mapIndexed {  index, value ->
            for (i in 1..days.size) {
                val headerCell: Cell = row.createCell(index)
                headerCell.setCellValue(value)
                headerCell.cellStyle = setStyle(workbook,10, CellColor.WHITE, true)
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
        val style = setStyle(workbook,8, CellColor.WHITE,false)

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
        when (scheduleType) {
            ScheduleType.ONE_SUBJECT -> {
                val subjectsOfWeek = List(5) {"Asignatura"}
                for (i in 1..subjectsOfWeek.size) {
                    val cell = row.createCell(i)
                    cell.setCellValue(subjectsOfWeek[i-1])
                    cell.cellStyle = setStyle(workbook, 10, CellColor.WHITE, true)
                }
            }
            ScheduleType.MULTIPLE_SUBJECT_MULTIPLE_CLASSROOM -> {
                val subjectsOfWeek = scheduleFileData.subjectsName.filter { !it.laboratory && !it.seminary && !it.english }.distinctBy { it.acronym }
                val subjectOfDay = mutableListOf<Subject>()
                subjectsOfWeek.map {
                    subjectOfDay.add(it)
                    subjectOfDay.add(it)
                    subjectOfDay.add(it)
                }
                val allSubjects = MutableList(5) { subjectOfDay }.flatten().toMutableList()

                scheduleFileData.deletedCols.reversed().map {
                    allSubjects.removeAt(it)
                }

                for (i in 1..allSubjects.size) {
                    val cell = row.createCell(i)
                    cell.setCellValue(allSubjects[i-1].acronym)
                    cell.cellStyle = setStyle(workbook,10,allSubjects[i-1].color.toCellColor(), true)
                }
            }
        }
    }

    private fun fillData(scheduleFileData: ScheduleFileData, sheet: Sheet, workbook: Workbook) {
        val matrix = scheduleFileData.subjects
        for (i in (headerSize+ headerOffset) until matrix.size + headerSize+ headerOffset) {
            val row = sheet.getRow(i)
            for(j in 1..matrix[0].size) {
                val cell = row.createCell(j)
                val subject = matrix[i-(headerSize+ headerOffset)][j-1]

                when(fileType) {
                    FileType.SUBJECT -> {
                        val seminary: String = if (subject?.seminary == true) { "sem\n" } else { "" }
                        val laboratory: String = if (subject?.laboratory == true) { "lab\n" } else { "" }
                        val english = if(subject?.english == true) { "ing"} else{ "" }
                        val groupName = subject?.classGroup ?: ""
                        val group: String = if (subject?.laboratory != true && subject?.seminary != true && subject?.english != true && subject!= null) { "gg " } else { "" }
                        when (scheduleType) {
                            ScheduleType.ONE_SUBJECT -> {
                                cell.setCellValue( seminary + laboratory + english + (subject?.name?: "") + " " + groupName)
                            }
                            ScheduleType.MULTIPLE_SUBJECT_MULTIPLE_CLASSROOM -> {
                                cell.setCellValue(seminary + laboratory + group + english + groupName)
                            }
                        }
                    }
                    FileType.DEPARTMENT -> {
                        cell.setCellValue(subject?.department?.acronym ?: "")
                    }
                    FileType.CLASSROOM -> {
                        cell.setCellValue(subject?.classroom?.acronym ?: "")
                    }
                }
                cell.cellStyle = setStyle(workbook,9,subject?.color?.toCellColor() ?: CellColor.WHITE, false)
            }
        }
    }

    private fun mergeCells(scheduleFileData: ScheduleFileData, sheet: Sheet) {
        val matrix = makeTranspose(scheduleFileData.subjects.map { it.toMutableList() }.toMutableList())
        val mergeList: MutableList<Coordinate> = mutableListOf()
        scheduleFileData.subjectsName.mapIndexed { k, subject ->
            matrix.mapIndexed { i, list ->
                list.mapIndexed { j, value ->
                    if (subject.id == value?.id) {
                        mergeList.add(Coordinate(k,i,j))
                    }
                }
            }
        }
        val subjectsByColumns: MutableList<Collection<List<Coordinate>>> = mutableListOf()
        val coordinates = mergeList.groupBy { it.subject }.values

        coordinates.map { listSubjects ->
            subjectsByColumns.add(listSubjects.groupBy { it.x }.values)
        }
        val list: MutableList<CellPosition> = mutableListOf()
        subjectsByColumns.flatten()
        subjectsByColumns.map { collection ->
            collection.map {
                list.add(CellPosition(it.minOf { cord -> cord.y } + (headerOffset + headerSize), it.maxOf { cord -> cord.y } + (headerOffset + headerSize), it[0].x+1, it[0].x+1))
            }
        }
        val repeatedElements = list.groupingBy { it }.eachCount().filter { it.value > 1 }
        repeatedElements.map {
            list.remove(it.key)
        }

        list.map {
            //sheet.addMergedRegion(CellRangeAddress(it.x1,it.x2,it.y1,it.y2))
        }
    }

    private fun setStyle(workbook: Workbook, fontSize: Int, color: CellColor, bold: Boolean): CellStyle {
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
        val currDir = File("./scheduleFile/")
        val path: String = currDir.absolutePath
        val fileLocation = path.substring(0, path.length) + "/" + name

        val outputStream = FileOutputStream(fileLocation)
        workbook.write(outputStream)
        workbook.close()
    }

    private fun makeTranspose(matrix: MutableList<MutableList<Subject?>>): MutableList<MutableList<Subject?>> {
        val row = matrix.size
        val column = matrix[0].size

        val transpose: MutableList<MutableList<Subject?>> = MutableList(column) { MutableList(row) { null } }
        for (i in 0 until row) {
            for (j in 0 until column) {
                transpose[j][i] = matrix[i][j]
            }
        }
        return transpose
    }

    private fun checkTurnEmpty(matrix: MutableList<MutableList<Subject?>>): List<Int> {
        val positions: MutableList<Int> = mutableListOf()
        matrix.mapIndexed { i, value ->
            val nulls = value.count { subject -> subject == null }
            when (scheduleType) {
                ScheduleType.ONE_SUBJECT -> {
                    if (nulls == numColumnsOneSubjectPerDay) {
                        positions.add(i)
                    }
                }
                ScheduleType.MULTIPLE_SUBJECT_MULTIPLE_CLASSROOM -> {
                    if (nulls == numColumnsMultipleSubjectPerDay) {
                        positions.add(i)
                    }
                }
            }
        }
        return positions
    }

    private fun emptyMorning(positions: List<Int>, matrix: MutableList<MutableList<Subject?>>): Pair<MutableList<MutableList<Subject?>>,Boolean> {
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

    private fun emptyAfternoon(positions: List<Int>, matrix: MutableList<MutableList<Subject?>>): Pair<MutableList<MutableList<Subject?>>,Boolean> {
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



    private fun flatMatrix(subjects: List<List<List<Subject?>>>): MutableList<MutableList<Subject?>>{
        val matrix: MutableList<MutableList<Subject?>> = mutableListOf()
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


    private fun deleteEmptyCols(matrix: MutableList<MutableList<Subject?>>): MutableList<MutableList<Subject?>> {
        val transpose = makeTranspose(matrix)
        transpose.removeIf { subjects ->
                val nulls = subjects.count { subject -> subject == null }
                return@removeIf nulls == numHourCells
        }
        return makeTranspose(transpose)
    }

    private fun getEmptyCols(matrix: MutableList<MutableList<Subject?>>): MutableList<Int> {
        val transpose = makeTranspose(matrix)
        val listIndex = mutableListOf<Int>()
        transpose.mapIndexed { index, subjects ->
            val nulls = subjects.count { subject -> subject == null }
            if (nulls == numHourCells) {
                listIndex.add(index)
            }
        }
        return listIndex
    }



    private fun obtainSubjects(matrix: MutableList<MutableList<Subject?>>): List<Subject> {
        val transpose = makeTranspose(matrix)
        var listSubjects = mutableListOf<Subject>()
        when (scheduleType) {
            ScheduleType.ONE_SUBJECT -> {
                transpose.map { subjects ->
                    subjects.map {
                        it?.let { subject ->
                            listSubjects.add(subject)
                        }
                    }
                }
                listSubjects = listSubjects.toSet().toMutableList()
            }
            ScheduleType.MULTIPLE_SUBJECT_MULTIPLE_CLASSROOM -> {
                transpose.map { subjects ->
                    subjects.map {
                        it?.let { subject ->
                            listSubjects.add(subject)
                        }
                    }
                }
                listSubjects = listSubjects.toSet().toMutableList()
            }
        }
        return listSubjects
    }

    private fun getDeletedCols(matrix: MutableList<MutableList<Subject?>>): List<Int> {
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
        const val numColumnsMultipleSubjectPerDay = 75
        const val numColumnsOneSubjectPerDay = 5
        const val headerOffset = 2
        const val headerSize = 2
    }
}