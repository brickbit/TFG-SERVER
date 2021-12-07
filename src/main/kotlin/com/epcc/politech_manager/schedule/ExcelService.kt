package com.epcc.politech_manager.schedule

import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.ss.util.CellRangeAddress
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.springframework.stereotype.Service
import java.io.File
import java.io.FileOutputStream

@Service
class ExcelService( val scheduleType: ScheduleType = ScheduleType.ONE_SUBJECT) {
    var numSubjects: Int = 5
    var numClassesPerSubject: Int = 1
    val schedule = createSchedule()

    fun createFile() {
        val workbook: Workbook = XSSFWorkbook()
        val sheet: Sheet = workbook.createSheet("Degrees")

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

        createHeader(sheet,sheet.getRow(0))
        createHoursRow(sheet)

        val subjects = listOf("AL","CAL","FIS","IP","TC")
        createSubjectHeader(subjects, sheet.getRow(1))
        //val row = sheet.getRow(3)
        //val cell = row.createCell(3)
        //cell.setCellValue("John Smith")
        fillData(sheet)
        closeFile(workbook)
    }

    private fun createHeader(sheet: Sheet, row: Row) {
        /*val headerStyle = workbook.createCellStyle()
        //headerStyle.fillForegroundColor = IndexedColors.LIGHT_BLUE.getIndex()
        //headerStyle.fillPattern = FillPatternType.SOLID_FOREGROUND

        val font = (workbook as XSSFWorkbook).createFont()
        font.fontName = "Arial"
        font.fontHeightInPoints = 12.toShort()
        font.bold = true
        headerStyle.setFont(font)*/

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
                    sheet.setColumnWidth(index, 3000)
                }
                ScheduleType.MULTIPLE_SUBJECT -> {
                    sheet.setColumnWidth(index, 1000)
                }
                ScheduleType.MULTIPLE_SUBJECT_MULTIPLE_CLASSROOM -> {
                    sheet.setColumnWidth(index, 1000)
                }
            }
            val headerCell: Cell = row.createCell(index)
            headerCell.setCellValue(value)
            //headerCell.cellStyle = headerStyle
        }
        if(numSubjects>1) {
            sheet.addMergedRegion(CellRangeAddress(0, 0, 1, numSubjects * numClassesPerSubject))
            sheet.addMergedRegion(CellRangeAddress(0, 0, 1 + numSubjects * numClassesPerSubject, (numSubjects * numClassesPerSubject * 2)))
            sheet.addMergedRegion(CellRangeAddress(0, 0, 1 + (numSubjects * numClassesPerSubject * 2), (numSubjects * numClassesPerSubject * 3)))
            sheet.addMergedRegion(CellRangeAddress(0, 0, 1 + (numSubjects * numClassesPerSubject * 3), (numSubjects * numClassesPerSubject * 4)))
            sheet.addMergedRegion(CellRangeAddress(0, 0, 1 + (numSubjects * numClassesPerSubject * 4), (numSubjects * numClassesPerSubject * 5)))
        }
    }

    private fun createSubjectHeader(subjects: List<String>,row: Row) {
        val listSubjects = mutableListOf<String>("Hora")
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
                }
            }
            ScheduleType.MULTIPLE_SUBJECT_MULTIPLE_CLASSROOM -> {
                for (i in 1 until numSubjects * numClassesPerSubject * 5 + 1) {
                    val cell: Cell = row.createCell(i)
                    cell.setCellValue(listSubjects[i])
                }
            }
        }

    }

    private fun createHoursRow(sheet: Sheet) {
        sheet.setColumnWidth(0, 2500)

        val hours = listOf("HORA","8:30","9:00","9:30","10:00","10:30","11:00","11:30","12:00","12:30","13:00","13:30","14:00","15:30","16:00","16:30","17:00","18:00","18:30","19:00","19:30","20:00","20:30","21:00")
        hours.mapIndexed { index, value ->
            val row: Row = sheet.getRow(index + 1)
            val cell: Cell = row.createCell(0)
            cell.setCellValue(value)
        }
    }

    private fun fillData(sheet: Sheet) {
        schedule.semester.list.mapIndexed { index, day ->
            day.mapIndexed{ i, subject ->
                val row: Row = sheet.getRow(i + 2)
                val cell: Cell = row.createCell(index+1)
                cell.setCellValue(subject.acronym)
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
}

