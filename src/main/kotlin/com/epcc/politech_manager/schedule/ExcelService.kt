package com.epcc.politech_manager.schedule

import org.apache.poi.ss.usermodel.*
import org.apache.poi.ss.util.CellRangeAddress
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.springframework.stereotype.Service
import java.io.File
import java.io.FileOutputStream


@Service
class ExcelService( ) {
    var scheduleType: ScheduleType = ScheduleType.ONE_SUBJECT
    var numSubjects: Int = 5
    var numClassesPerSubject: Int = 1
    val schedule = createComputerScienceDegree()//createBuildingSchedule()

    fun createFile() {
        val workbook: Workbook = XSSFWorkbook()
        val sheet: Sheet = workbook.createSheet("Degrees")

        scheduleType = determineScheduleType()

        when(scheduleType) {
            ScheduleType.ONE_SUBJECT -> {
                numSubjects = 1
                numClassesPerSubject = 1
            }
            ScheduleType.MULTIPLE_SUBJECT -> {
                numSubjects = 5
                numClassesPerSubject = 1
            }
            ScheduleType.MULTIPLE_SUBJECT_MULTIPLE_CLASSROOM -> {
                numSubjects = 5
                numClassesPerSubject = 3
            }
        }

        for (i in 0..25) {
            sheet.createRow(i)
        }

        createHeader(sheet,sheet.getRow(0),workbook)
        createHoursRow(sheet, workbook)

        val subjects = getListOfSubjects().filter { !it.seminary && !it.laboratory && !it.english }.sortedBy { it.acronym }
        createSubjectHeader(subjects, sheet.getRow(1),workbook)

        fillData(sheet,workbook)

        when (scheduleType) {
            ScheduleType.ONE_SUBJECT -> {
                mergeSameSubjects(sheet)
                if (isEmptyAfternoon()) {
                    for (i in 14..25) {
                        sheet.createRow(i)
                    }
                }
                if (isEmptyMorning()) {
                    for (i in 0..14) {
                        sheet.createRow(i)
                    }
                }
            }
            ScheduleType.MULTIPLE_SUBJECT -> {

            }
            ScheduleType.MULTIPLE_SUBJECT_MULTIPLE_CLASSROOM -> {
                val colsToDelete = getEmptyColClassroom()
                /*colsToDelete[0].map {
                    sheet.shiftColumns(it,it,1)
                }*/
                //sheet.getRow(2).shiftCellsLeft(0, sheet.getRow(0).lastCellNum.toInt(),1)
                //sheet.lastRowNum
                //sheet.shiftColumns(2,sheet.lastRowNum,1)

            }
        }

        closeFile(workbook)
    }

    private fun determineScheduleType(): ScheduleType {
        schedule.semester.subjectsInSemester.map { day ->
            day.map { hour ->
                if (hour.size in 2..5) {
                    return ScheduleType.MULTIPLE_SUBJECT
                } else if (hour.size > 5) {
                    return ScheduleType.MULTIPLE_SUBJECT_MULTIPLE_CLASSROOM
                }
            }
        }

        return ScheduleType.ONE_SUBJECT
    }

    private fun setStyle(workbook: Workbook,fontSize: Int, color: CellColor): CellStyle {
        val headerStyle = workbook.createCellStyle()
        when (color) {
            CellColor.BLUE -> headerStyle.fillForegroundColor = IndexedColors.LIGHT_TURQUOISE.getIndex()
            CellColor.RED -> headerStyle.fillForegroundColor = IndexedColors.CORAL.getIndex()
            CellColor.YELLOW -> headerStyle.fillForegroundColor = IndexedColors.LIGHT_YELLOW.getIndex()
            CellColor.GOLD -> headerStyle.fillForegroundColor = IndexedColors.GOLD.getIndex()
            CellColor.GREEN -> headerStyle.fillForegroundColor = IndexedColors.LIGHT_GREEN.getIndex()
            CellColor.WHITE -> headerStyle.fillForegroundColor = IndexedColors.WHITE.getIndex()
        }
        headerStyle.borderTop = BorderStyle.THIN
        headerStyle.topBorderColor = IndexedColors.GREY_40_PERCENT.getIndex()
        headerStyle.borderBottom = BorderStyle.THIN
        headerStyle.bottomBorderColor = IndexedColors.GREY_40_PERCENT.getIndex()
        headerStyle.borderLeft = BorderStyle.THIN
        headerStyle.leftBorderColor = IndexedColors.GREY_40_PERCENT.getIndex()
        headerStyle.borderRight = BorderStyle.THIN
        headerStyle.rightBorderColor = IndexedColors.GREY_40_PERCENT.getIndex()

        headerStyle.fillPattern = FillPatternType.SOLID_FOREGROUND

        val font = (workbook as XSSFWorkbook).createFont()
        font.fontName = "Comic Sans MS"
        font.fontHeightInPoints = fontSize.toShort()
        font.bold = true
        headerStyle.alignment = HorizontalAlignment.CENTER
        headerStyle.verticalAlignment = VerticalAlignment.CENTER
        headerStyle.setFont(font)
        return headerStyle
    }

    private fun createHeader(sheet: Sheet, row: Row, workbook: Workbook) {
        val headerStyle = setStyle(workbook,10,CellColor.WHITE)

        val days = listOf("Lunes","Martes","Miércoles","Jueves","Viernes")
        val headerDays = mutableListOf("")
        days.map {
            for (i in 1..numSubjects*numClassesPerSubject) {
                headerDays.add(it)
            }
        }
        headerDays.mapIndexed {index, value ->
            when(scheduleType) {
                ScheduleType.ONE_SUBJECT -> {
                    sheet.setColumnWidth(index, 5000)
                }
                ScheduleType.MULTIPLE_SUBJECT -> {
                    sheet.setColumnWidth(index, 1500)
                }
                ScheduleType.MULTIPLE_SUBJECT_MULTIPLE_CLASSROOM -> {
                    sheet.setColumnWidth(index, 1500)
                }
            }
            val headerCell: Cell = row.createCell(index)
            headerCell.setCellValue(value)
            headerCell.cellStyle = headerStyle
        }
        if(numSubjects>1) {
            sheet.addMergedRegion(CellRangeAddress(0, 0, 1, numSubjects * numClassesPerSubject))
            sheet.addMergedRegion(CellRangeAddress(0, 0, 1 + numSubjects * numClassesPerSubject, (numSubjects * numClassesPerSubject * 2)))
            sheet.addMergedRegion(CellRangeAddress(0, 0, 1 + (numSubjects * numClassesPerSubject * 2), (numSubjects * numClassesPerSubject * 3)))
            sheet.addMergedRegion(CellRangeAddress(0, 0, 1 + (numSubjects * numClassesPerSubject * 3), (numSubjects * numClassesPerSubject * 4)))
            sheet.addMergedRegion(CellRangeAddress(0, 0, 1 + (numSubjects * numClassesPerSubject * 4), (numSubjects * numClassesPerSubject * 5)))
        }
    }

    private fun createSubjectHeader(subjects: List<SubjectDegree>,row: Row, workbook: Workbook) {

        val listSubjects = mutableListOf("Hora")
        for(i in 0..4) {
            subjects.map {
                for (j in 0..2) {
                    listSubjects.add(it.acronym)
                }
            }
        }

        when (scheduleType) {
            ScheduleType.ONE_SUBJECT -> {
                for (i in 1..numSubjects * numClassesPerSubject * 5) {
                    val cell: Cell = row.createCell(i)
                    cell.setCellValue("Asignatura")
                    cell.cellStyle = setStyle(workbook,9,CellColor.WHITE)
                }
            }
            ScheduleType.MULTIPLE_SUBJECT -> {
                for (i in 1..numSubjects * numClassesPerSubject * 5) {
                    val cell: Cell = row.createCell(i)
                    var module = i % (numSubjects * numClassesPerSubject)
                    if (module == 0) {
                        module = numSubjects * numClassesPerSubject
                    }
                    cell.setCellValue(subjects[module - 1].acronym)
                    cell.cellStyle = setStyle(workbook,9,CellColor.WHITE)
                }
            }
            ScheduleType.MULTIPLE_SUBJECT_MULTIPLE_CLASSROOM -> {
                for (i in 1 until numSubjects * numClassesPerSubject * 5 + 1) {
                    val cell: Cell = row.createCell(i)
                    cell.setCellValue(listSubjects[i])

                    val subjectsForDay = mutableListOf<SubjectDegree>()
                    subjects.map {
                        subjectsForDay.add(it)
                        subjectsForDay.add(it)
                        subjectsForDay.add(it)
                    }
                    val subjectsForWeek = List(5) {subjectsForDay}.flatten().toMutableList()
                    subjectsForWeek.add(0,subjects[0])

                    cell.cellStyle = setStyle(workbook,9,subjectsForWeek[i].color.toCellColor())
                }
            }
        }

    }

    private fun createHoursRow(sheet: Sheet, workbook: Workbook) {
        sheet.setColumnWidth(0, 2500)
        val headerStyle = setStyle(workbook,8,CellColor.WHITE)

        val hours = listOf("HORA","8:30","9:00","9:30","10:00","10:30","11:00","11:30","12:00","12:30","13:00","13:30","14:00","15:30","16:00","16:30","17:00","18:00","18:30","19:00","19:30","20:00","20:30","21:00")
        hours.mapIndexed { index, value ->
            val row: Row = sheet.getRow(index + 1)
            val cell: Cell = row.createCell(0)
            cell.setCellValue(value)
            cell.cellStyle = headerStyle
        }
    }

    private fun fillData(sheet: Sheet, workbook: Workbook) {
        schedule.semester.subjectsInSemester.mapIndexed { index, day ->
            day.mapIndexed{ i, hour ->
                val row: Row = sheet.getRow(i + 2)
                hour.mapIndexed { j, subject ->
                    val cell: Cell = when(scheduleType) {
                        ScheduleType.ONE_SUBJECT -> row.createCell(index + j + 1)
                        ScheduleType.MULTIPLE_SUBJECT -> row.createCell(index*5 + j + 1)
                        ScheduleType.MULTIPLE_SUBJECT_MULTIPLE_CLASSROOM -> row.createCell(index*15 + j + 1)
                    }
                    if(subject != null) {
                        when(scheduleType) {
                            ScheduleType.ONE_SUBJECT -> {
                                val seminary: String = if (subject.seminary) { " sem" } else { "" }
                                val laboratory: String = if (subject.laboratory) { " lab" } else { "" }
                                cell.setCellValue(subject.name + seminary + laboratory)
                                cell.cellStyle = setStyle(workbook,9,subject.color.toCellColor())
                            }
                            ScheduleType.MULTIPLE_SUBJECT -> cell.setCellValue(subject.acronym)
                            ScheduleType.MULTIPLE_SUBJECT_MULTIPLE_CLASSROOM ->{
                                val seminary: String = if (subject.seminary) { "sem " } else { "" }
                                val laboratory: String = if (subject.laboratory) { "lab " } else { "" }
                                val english = if(subject.english) { "ING "} else{ "" }
                                val group: String = if (!subject.laboratory && !subject.seminary && !subject.english) { "gg " } else { "" }
                                cell.setCellValue(seminary + laboratory + group + english + subject.group)
                                cell.cellStyle = setStyle(workbook,9,subject.color.toCellColor())
                            }
                        }
                    } else {
                        cell.setCellValue("")
                    }
                }
            }
        }
    }

    private fun closeFile(workbook: Workbook) {
        val currDir = File(".")
        val path: String = currDir.absolutePath
        val fileLocation = path.substring(0, path.length - 1) + "schedule.xlsx"

        val outputStream = FileOutputStream(fileLocation)
        workbook.write(outputStream)
        workbook.close()
    }

    private fun mergeSameSubjects(sheet: Sheet) {
        val listSubject = getListOfSubjects()
        val mergeList: MutableList<MutableList<Pair<Int, Int>>> = mutableListOf()
        listSubject.map {
            mergeList.add(mutableListOf())
        }
        listSubject.mapIndexed { k, subject ->
            schedule.semester.subjectsInSemester.mapIndexed { index, day ->
                day.mapIndexed { i, hour ->
                    hour.mapIndexed { j, value ->
                        value?.let {
                            if (subject.id == it.id) {
                                mergeList[k].add(Pair(i+2,index+1))
                            }
                        }
                    }
                }
            }
        }
        val pairs: MutableList<Map<Int, List<Pair<Int, Int>>>> = mutableListOf()
        mergeList.map { pairList ->
            pairs.add(pairList.groupBy { it.second })
        }
        pairs.map { map ->
            map.values.map { list ->
                val col = list[0].second
                val firstRow = list[0].first
                val lastRow = list.last().first
                sheet.addMergedRegion(CellRangeAddress(firstRow, lastRow, col, col))
            }
        }
    }

    private fun getListOfSubjects(): List<SubjectDegree> {
        val listSubject = mutableListOf<SubjectDegree>()
        schedule.semester.subjectsInSemester.map { day ->
            day.map { hour ->
                hour.map {
                    it?.let { subject ->
                        if (!listSubject.contains(subject)) {
                            listSubject.add(subject)
                        }
                    }
                }
            }
        }
        return listSubject
    }

    private fun isEmptyAfternoon(): Boolean {
        var nulls = 0
        schedule.semester.subjectsInSemester.map { day ->
            day.mapIndexed { i, _ ->
                if (i < 12) {
                    if(day[i+12].contains(null)) {
                        nulls++
                    }
                }
            }
        }
        return when (scheduleType) {
            ScheduleType.ONE_SUBJECT -> nulls == 60
            ScheduleType.MULTIPLE_SUBJECT -> nulls == 275
            ScheduleType.MULTIPLE_SUBJECT_MULTIPLE_CLASSROOM -> nulls == 825
        }
    }

    private fun isEmptyMorning(): Boolean {
        var nulls = 0
        schedule.semester.subjectsInSemester.map { day ->
            day.mapIndexed { i, _ ->
                if (i < 12) {
                    if (day[i].contains(null)) {
                        nulls++
                    }
                }
            }
        }
        return when (scheduleType) {
            ScheduleType.ONE_SUBJECT -> nulls == 55
            ScheduleType.MULTIPLE_SUBJECT -> nulls == 275
            ScheduleType.MULTIPLE_SUBJECT_MULTIPLE_CLASSROOM -> nulls == 825
        }
    }

    private fun getEmptyColClassroom(): MutableList<MutableList<Int>> {
        val removeCols: MutableList<MutableList<Int>> = mutableListOf()
        schedule.semester.subjectsInSemester.mapIndexed { i, day ->
            val transpose = calculateTranspose(day)
            val deleteList: MutableList<Int> = mutableListOf()
            transpose.mapIndexed { index, value ->
                val nulls = value.count { subject -> subject == null }
                if(nulls == 23) {
                    deleteList.add(index)
                }
            }
            removeCols.add(deleteList)
        }
        return removeCols
    }

    private fun calculateTranspose(matrix: List<List<SubjectDegree?>>): List<List<SubjectDegree?>> {
        val row = matrix.size
        val column = matrix[0].size

        // Transpose the matrix
        val transpose: MutableList<MutableList<SubjectDegree?>> = MutableList(column) { MutableList(row) { null } }
        for (i in 0 until row) {
            for (j in 0 until column) {
                transpose[j][i] = matrix[i][j]
            }
        }
        return transpose
    }
}

enum class CellColor{
    BLUE,
    RED,
    YELLOW,
    GOLD,
    GREEN,
    WHITE
}

fun Int.toCellColor(): CellColor {
    return when(this) {
        0 -> CellColor.BLUE
        1 -> CellColor.RED
        2 -> CellColor.YELLOW
        3 -> CellColor.GOLD
        4 -> CellColor.GREEN
        else -> CellColor.WHITE
    }
}