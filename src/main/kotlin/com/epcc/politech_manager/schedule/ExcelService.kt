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
        
        val subjects = listOf("AL","CAL","FIS","IP","TC")
        createSubjectHeader(subjects, sheet.getRow(1),workbook)

        fillData(sheet,workbook)
        mergeSameSubjects(sheet)
        //mergeSameSubjects(sheet)
        closeFile(workbook)
    }

    private fun determineScheduleType(): ScheduleType {
        schedule.semester.list.map { day ->
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

    private fun createSubjectHeader(subjects: List<String>,row: Row, workbook: Workbook) {
        val headerStyle = setStyle(workbook,9,CellColor.WHITE)

        val listSubjects = mutableListOf("Hora")
        for(i in 0..4) {
            subjects.map {
                for (j in 0..2) {
                    listSubjects.add(it)
                }
            }
        }


        when (scheduleType) {
            ScheduleType.ONE_SUBJECT -> {
                for (i in 1..numSubjects * numClassesPerSubject * 5) {
                    val cell: Cell = row.createCell(i)
                    cell.setCellValue("Asignatura")
                    cell.cellStyle = headerStyle
                }
            }
            ScheduleType.MULTIPLE_SUBJECT -> {
                for (i in 1..numSubjects * numClassesPerSubject * 5) {
                    val cell: Cell = row.createCell(i)
                    var module = i % (numSubjects * numClassesPerSubject)
                    print(module)
                    if (module == 0) {
                        module = numSubjects * numClassesPerSubject
                    }
                    cell.setCellValue(subjects[module - 1])
                    cell.cellStyle = headerStyle
                }
            }
            ScheduleType.MULTIPLE_SUBJECT_MULTIPLE_CLASSROOM -> {
                for (i in 1 until numSubjects * numClassesPerSubject * 5 + 1) {
                    val cell: Cell = row.createCell(i)
                    cell.setCellValue(listSubjects[i])
                    cell.cellStyle = headerStyle
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
        schedule.semester.list.mapIndexed { index, day ->
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
        var listToMerge = mutableListOf<Pair<Int,Int>>()
        var previousSubject: SubjectDegree? = null
        schedule.semester.list.mapIndexed { index, day ->
            day.mapIndexed { i, hour ->
                val row: Row = sheet.getRow(i + 2)
                hour.mapIndexed { j, subject ->
                    if(previousSubject!=null) {
                        if(i-1 >= 0) {
                            if(day[i][j]?.id == day[i-1][j]?.id) {
                                listToMerge.add(Pair(i+2,index+1))
                                /*if(!merged) {
                                    println("$i $j $index")
                                    sheet.addMergedRegion(CellRangeAddress(i + 1, i + 2, index + 1, index + 1))
                                    merged = true
                                } else {
                                    merged = !merged
                                }*/
                            }
                        }
                    }
                    previousSubject = subject
                }
            }
        }
        println(listToMerge)
        val fixedList = mutableListOf<Pair<Int,Int>>()
        listToMerge.mapIndexed { i, value ->
            if(i+1 < listToMerge.size) {
                if (listToMerge[i + 1].first - value.first != 1) {
                    fixedList.add(value)
                }
            }
        }
        println(fixedList)
        fixedList.map {
            //sheet.addMergedRegion(CellRangeAddress(it.first, it.first+1, it.second, it.second))
        }
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