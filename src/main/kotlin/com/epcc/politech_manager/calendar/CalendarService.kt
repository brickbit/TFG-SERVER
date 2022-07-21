package com.epcc.politech_manager.calendar

import com.epcc.politech_manager.error.DataException
import com.epcc.politech_manager.error.ExceptionDataModel
import com.epcc.politech_manager.exam.ExamEntityDTO
import com.epcc.politech_manager.utils.CellColor
import org.apache.poi.ss.usermodel.*
import org.apache.poi.ss.util.CellRangeAddress
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.springframework.stereotype.Service
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.math.abs

@Service
class CalendarService(val db: CalendarRepository) {

    var exams = emptyList<ExamEntityDTO?>()

    var degree: String = ""

    var year: String = ""

    var call: String = ""

    var startDate: Date = Calendar.getInstance().time

    var endDate: Date = Calendar.getInstance().time

    fun getAllCalendars(): List<CalendarEntityDAO> = db.findAll().toList()

    fun post(calendar: CalendarEntityDAO) {
        db.save(calendar)
    }

    fun getCalendar(id: Long): CalendarEntityDAO? {
        return db.findById(id).orElse(null)
    }

    fun deleteCalendar(id: Long) {
        try {
            db.deleteById(id)
        } catch (e: Exception) {
            throw DataException(ExceptionDataModel.CALENDAR_NOT_EXIST)
        }
    }

    fun updateCalendar(calendar: CalendarEntityDAO) {
        if(db.existsById(calendar.id)) {
            db.save(calendar)
        } else {
            throw DataException(ExceptionDataModel.CALENDAR_NOT_EXIST)
        }
    }

    fun initData(exams: List<ExamEntityDTO?>,
                 degree: String,
                 year: String,
                 call: String,
                 startDate: String,
                 endDate:String
    ) {
        this.exams = exams
        this.degree = degree
        this.year = year
        this.call = call
        val dateFormat = SimpleDateFormat("dd-MM-yyyy")
        this.startDate = dateFormat.parse(startDate)
        this.endDate = dateFormat.parse(endDate)
    }

    fun createFile(): String {
        val workbook: Workbook = XSSFWorkbook()
        val sheet: Sheet = workbook.createSheet(degree)
        paint(sheet, workbook)
        closeFile(workbook)
        return "calendar-$degree-$year.xlsx"
    }

    private fun closeFile(workbook: Workbook) {
        val currDir = File("./calendarFile/")
        val path: String = currDir.absolutePath
        val fileLocation = path.substring(0, path.length) + "/" + "calendar-$degree-$year.xlsx"

        val outputStream = FileOutputStream(fileLocation)
        workbook.write(outputStream)
        workbook.close()
    }

    private fun paint(sheet: Sheet, workbook: Workbook) {
        createRows(sheet)
        createTitle(sheet,workbook)
        createHeader(sheet,workbook)
        createDays(sheet,workbook,3)
        createDays(sheet,workbook,6)
        createDays(sheet,workbook,9)
        createDays(sheet,workbook,12)
        fillData(sheet,workbook)
        hideWeekend(sheet,workbook)
    }

    private fun createRows(sheet: Sheet) {
        val diffInMillis: Long = abs(endDate.time - startDate.time)
        val diff: Long = TimeUnit.DAYS.convert(diffInMillis, TimeUnit.MILLISECONDS)
        val numRows: Int = diff.toInt()
        for (i in 0..14) {
            val row = sheet.createRow(i)
            sheet.setColumnWidth(i, 2000)
            for (j in 0..numRows) {
                row.createCell(j)
            }
        }
    }

    private fun createTitle(sheet: Sheet, workbook: Workbook) {
        val diffInMillis: Long = abs(endDate.time - startDate.time)
        val diff: Long = TimeUnit.DAYS.convert(diffInMillis, TimeUnit.MILLISECONDS)
        val numRows: Int = diff.toInt()

        val row = sheet.createRow(0)
        for (i in 0..numRows) {
            row.createCell(i)
        }
        var cell = row.createCell(0)
        cell.setCellValue(call)
        cell.cellStyle = setStyle(workbook,11, CellColor.WHITE,true)
        cell = row.createCell(if (numRows<=8) numRows else numRows - 8)
        cell.setCellValue(degree.uppercase(Locale.getDefault()))
        cell.cellStyle = setStyle(workbook,11, CellColor.WHITE,true)
        cell = row.createCell(numRows)
        cell.cellStyle = setStyle(workbook,11, CellColor.WHITE,true)
        sheet.addMergedRegion(CellRangeAddress(0, 0, 0, 2))
        if (numRows > 8) {
            sheet.addMergedRegion(CellRangeAddress(0, 0, numRows - 8, numRows))
        }
    }

    private fun createHeader(sheet: Sheet, workbook: Workbook) {
        val diffInMillis: Long = abs(endDate.time - startDate.time)
        val diff: Long = TimeUnit.DAYS.convert(diffInMillis, TimeUnit.MILLISECONDS)
        val numRows: Int = diff.toInt()

        val row = sheet.createRow(2)
        for (i in 0..numRows) {
            val cell = row.createCell(i)
            cell.setCellValue(getDayCharacter(startDate, i))
            if (getDayCharacter(startDate, i) == "S") {
                cell.cellStyle = setStyle(workbook, 11, CellColor.GOLD, true)
            } else if (getDayCharacter(startDate, i) == "D") {
                cell.cellStyle = setStyle(workbook, 11, CellColor.RED, true)
            } else {
                cell.cellStyle = setStyle(workbook, 11, CellColor.YELLOW, true)
            }
        }
    }

    private fun createDays(sheet: Sheet, workbook: Workbook,rowNum: Int) {
        val diffInMillis: Long = abs(endDate.time - startDate.time)
        val diff: Long = TimeUnit.DAYS.convert(diffInMillis, TimeUnit.MILLISECONDS)
        val numRows: Int = diff.toInt()

        val row = sheet.createRow(rowNum)
        for (i in 0..numRows) {
            val cell = row.createCell(i)
            val calendar = Calendar.getInstance()
            calendar.time = startDate
            calendar.add(Calendar.DATE, i)
            cell.setCellValue(calendar.get(Calendar.DATE).toString())
            if (getDayCharacter(startDate, i) == "S") {
                cell.cellStyle = setStyle(workbook, 11, CellColor.GOLD, true)
            } else if (getDayCharacter(startDate, i) == "D") {
                cell.cellStyle = setStyle(workbook, 11, CellColor.RED, true)
            } else {
                cell.cellStyle = setStyle(workbook, 11, CellColor.WHITE, true)
            }
        }
    }

    private fun fillData(sheet: Sheet, workbook: Workbook) {
        exams.map { exam ->
            if(exam != null) {
                val row = sheet.getRow(getRowForSemester(exam.semester, exam.turn))
                val dateFormat = SimpleDateFormat("dd-MM-yyyy")
                val date = dateFormat.parse(exam.date)
                val diffInMillis: Long = abs(date.time - startDate.time)
                val diff: Long = TimeUnit.DAYS.convert(diffInMillis, TimeUnit.MILLISECONDS)
                val pos: Int = diff.toInt()
                val cell = row.getCell(pos)
                cell.setCellValue(exam.subject.acronym)
                cell.cellStyle = setStyle(workbook, 11, CellColor.WHITE, true)
            }
        }
    }

    private fun hideWeekend(sheet: Sheet, workbook: Workbook) {
        val diffInMillis: Long = abs(endDate.time - startDate.time)
        val diff: Long = TimeUnit.DAYS.convert(diffInMillis, TimeUnit.MILLISECONDS)
        val numRows: Int = diff.toInt()

        var row = sheet.getRow(4)
        hideCell(row,numRows,workbook,true)
        row = sheet.getRow(5)
        hideCell(row,numRows,workbook,false)
        row = sheet.getRow(7)
        hideCell(row,numRows,workbook,true)
        row = sheet.getRow(8)
        hideCell(row,numRows,workbook,false)
        row = sheet.getRow(10)
        hideCell(row,numRows,workbook,true)
        row = sheet.getRow(11)
        hideCell(row,numRows,workbook,false)
        row = sheet.getRow(13)
        hideCell(row,numRows,workbook,true)
        row = sheet.getRow(14)
        hideCell(row,numRows,workbook,false)
    }

    private fun hideCell(row: Row, numRows: Int, workbook: Workbook, saturday: Boolean) {
        for (i in 0..numRows) {
            val cell = row.getCell(i)
            if (getDayCharacter(startDate, i) == "D") {
                cell.cellStyle = setStyle(workbook, 11, CellColor.BLACK, true)
            } else if(getDayCharacter(startDate, i) == "S") {
                cell.cellStyle = setStyle(workbook, 11, if (saturday) CellColor.YELLOW else CellColor.BLACK, true)
            }
        }
    }

    private fun getRowForSemester(semester: Int, turn : String): Int {
        return when (semester) {
            1,2 -> if (turn == "MORNING") 4 else 5
            3,4 -> if (turn == "MORNING") 7 else 8
            5,6 -> if (turn == "MORNING") 10 else 11
            7,8 -> if (turn == "MORNING") 13 else 14
            else -> -1
        }
    }

    private fun getDayCharacter(date: Date, days: Int): String {
        val calendar = Calendar.getInstance()
        calendar.time = date
        calendar.add(Calendar.DATE, days)
        return when (calendar.get(Calendar.DAY_OF_WEEK)) {
            1 -> "D"
            2 -> "L"
            3 -> "M"
            4 -> "X"
            5 -> "J"
            6 -> "V"
            7 -> "S"
            else -> ""
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
            CellColor.BLACK -> style.fillForegroundColor = IndexedColors.BLACK.getIndex()
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
}