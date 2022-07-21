package com.epcc.politech_manager.schedule

import com.epcc.politech_manager.error.DataException
import com.epcc.politech_manager.error.ExceptionDataModel
import com.epcc.politech_manager.subject.SubjectBO
import com.epcc.politech_manager.utils.*
import org.apache.poi.ss.usermodel.*
import org.apache.poi.ss.util.CellRangeAddress
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.springframework.stereotype.Service
import java.io.File
import java.io.FileOutputStream
import java.util.*
import javax.transaction.Transactional
import kotlin.math.ceil

@Service
class ScheduleService(

        val db: ScheduleRepository
) {
    var scheduleData: List<SubjectBO?> = emptyList()
    var fileType: FileType = FileType.SUBJECT
    var scheduleType: ScheduleType = ScheduleType.ONE_SUBJECT
    var degree: String = ""
    var year: String = ""

    fun post(schedule: ScheduleEntityDAO?) {
        db.save(schedule)
    }

    fun getAllSchedules(): List<ScheduleEntityDAO> = db.findAll().toList()

    fun getSchedule(id: Long): ScheduleEntityDAO? {
        return db.findById(id).orElse(null)
    }

    @Transactional
    fun deleteSchedule(id: Long) {
        try {
            db.deleteById(id)
        } catch (e: Exception) {
            throw DataException(ExceptionDataModel.SCHEDULE_NOT_EXIST)
        }
    }

    fun updateSchedule(schedule: ScheduleEntityDAO) {
        if(db.existsById(schedule.id)) {
            db.save(schedule)
        } else {
            throw DataException(ExceptionDataModel.SCHEDULE_NOT_EXIST)
        }
    }

    fun initData(scheduleData: List<SubjectBO?>,
                 fileType: FileType,
                 scheduleType: ScheduleType,
                 degree: String,
                 year: String) {
        this.scheduleData = scheduleData
        this.fileType = fileType
        this.scheduleType = scheduleType
        this.degree = degree
        this.year = year
    }

    fun createFile(): String {
        val workbook: Workbook = XSSFWorkbook()
        val sheet: Sheet = workbook.createSheet(degree)

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

    private fun paint(scheduleFileData: List<SubjectBO?>, sheet: Sheet, workbook: Workbook) {
        when (scheduleType) {
            ScheduleType.ONE_SUBJECT -> { createRows(scheduleFileData,sheet, 5500) }
            ScheduleType.MULTIPLE_SUBJECT_MULTIPLE_CLASSROOM -> { createRows(scheduleFileData,sheet, 1500) }
        }
        createTitle(sheet,workbook)
        when (scheduleType) {
            ScheduleType.ONE_SUBJECT -> {
                createHeaderOneSubject(sheet.getRow(headerOffset), workbook)
            }
            ScheduleType.MULTIPLE_SUBJECT_MULTIPLE_CLASSROOM -> {
                createHeaderMultipleSubject(sheet.getRow(headerOffset), workbook, sheet)
            }
        }
        createHoursRow(scheduleFileData,sheet,workbook)
        createSubjectHeader(scheduleFileData,sheet.getRow(headerOffset + 1), workbook)
        when (scheduleType) {
            ScheduleType.ONE_SUBJECT -> {
                fillData(scheduleFileData,sheet,workbook)
            }
            ScheduleType.MULTIPLE_SUBJECT_MULTIPLE_CLASSROOM -> {
                fillMultipleData(scheduleFileData,sheet,workbook)
                //eraseEmptyColumn(sheet)
            }
        }
        //mergeCells(scheduleFileData,sheet)
    }
    private fun eraseEmptyColumn(sheet: Sheet) {
        val emptyCells = Array(75) { IntArray(27) }
        for (i in (headerOffset + headerSize) until (headerOffset + headerSize + 23)) {
            val row = sheet.getRow(i)
            for (j in 1 until 75 ) {
                val cell = row.getCell(j)
                if(cell.stringCellValue == "") {
                    emptyCells[j][i] = -1
                } else {
                    emptyCells[j][i] = 1
                }

            }
        }
        val removeCellList = mutableListOf<Int>()
        for (i in emptyCells.indices) {
            if (emptyCells[i].count { it == -1} == 23) {
                removeCellList.add(i)
            }
        }

        for (i in 0 until 23 + headerOffset+ headerSize) {
            val row = sheet.getRow(i)
            for (j in 1 until 76) {
                if(removeCellList.contains(j)) {
                    row.createCell(j)
                }
            }
        }

        removeCellList.mapIndexed { index, _ ->
            if(index>0) {
                sheet.shiftRows(index, index, 1)
            }
        }
    }

    private fun createRows(scheduleFileData: List<SubjectBO?>, sheet: Sheet, cellWidth: Int) {
        val numRows = scheduleFileData.size + headerOffset + headerSize
        for (i in 0..numRows) {
            sheet.createRow(i)
            sheet.setColumnWidth(i, cellWidth)
        }
    }

    private fun createTitle( sheet: Sheet, workbook: Workbook) {
        val row = sheet.createRow(0)
        var cell = row.createCell(0)
        cell.setCellValue("Curso")
        cell.cellStyle = setStyle(workbook,11, CellColor.WHITE,true)
        cell = row.createCell(1)
        cell.setCellValue(ceil(((this.scheduleData[0]?.semester ?: 0)+1)/2.0).toInt().toString())
        cell.cellStyle = setStyle(workbook,11, CellColor.WHITE,true)
        cell = row.createCell(2)
        cell.setCellValue(degree.uppercase(Locale.getDefault()))
        cell.cellStyle = setStyle(workbook,11, CellColor.WHITE,true)
        if(scheduleType == ScheduleType.MULTIPLE_SUBJECT_MULTIPLE_CLASSROOM) {
            sheet.addMergedRegion(CellRangeAddress(0, 0, 2, 73))
            cell = row.createCell(74)
            sheet.addMergedRegion(CellRangeAddress(0, 0, 74, 75))

        } else {
            sheet.addMergedRegion(CellRangeAddress(0, 0, 2, 4))
            cell = row.createCell(5)
        }
        cell.setCellValue("${year.toInt()-1} - $year")
        cell.cellStyle = setStyle(workbook,11, CellColor.WHITE,true)
    }

    private fun createHeaderMultipleSubject(row: Row, workbook: Workbook, sheet: Sheet){
        val days = listOf("Lunes","Martes","Miércoles","Jueves","Viernes")

        val daysOfWeek = fillDaysOfWeek(days)
        daysOfWeek.add(0,"")
        daysOfWeek.mapIndexed {  index, value ->
            for (i in 1..daysOfWeek.size) {
                val headerCell: Cell = row.createCell(index)
                headerCell.setCellValue(value)
                headerCell.cellStyle = setStyle(workbook,10, CellColor.WHITE, true)
            }
        }
        sheet.addMergedRegion(CellRangeAddress(row.rowNum, row.rowNum, 1, 15))
        sheet.addMergedRegion(CellRangeAddress(row.rowNum, row.rowNum, 16, 30))
        sheet.addMergedRegion(CellRangeAddress(row.rowNum, row.rowNum, 31, 45))
        sheet.addMergedRegion(CellRangeAddress(row.rowNum, row.rowNum, 46, 60))
        sheet.addMergedRegion(CellRangeAddress(row.rowNum, row.rowNum, 61, 75))
    }

    private fun createHeaderOneSubject( row: Row, workbook: Workbook){
        val days = mutableListOf("Lunes","Martes","Miércoles","Jueves","Viernes")

        days.add(0,"")
        days.mapIndexed {  index, value ->
            for (i in 1..days.size) {
                val headerCell: Cell = row.createCell(index)
                headerCell.setCellValue(value)
                headerCell.cellStyle = setStyle(workbook,10, CellColor.WHITE, true)
            }
        }
    }

    private fun fillDaysOfWeek(days: List<String>): MutableList<String> {
        val newList = mutableListOf<String>()
        days.map {
            for (i in 0..14) {
                newList.add(it)
            }
        }
        return newList
    }

    private fun createHoursRow(scheduleFileData: List<SubjectBO?>, sheet: Sheet, workbook: Workbook) {
        val style = setStyle(workbook,8, CellColor.WHITE,false)

        val hours = listOf("","HORA","8:30\n","9:00\n","9:30\n","10:00\n","10:30\n","11:00\n","11:30\n","12:00\n","12:30\n","13:00\n","13:30\n","14:00\n","15:30\n","16:00\n","16:30\n","17:00\n","17:30\n","18:00\n","18:30\n","19:00\n","19:30\n","20:00\n","20:30\n","21:00\n")
        val size = scheduleFileData.size
        hours.mapIndexed { index, value ->
            if(index < size+ headerOffset) {
                val row: Row = sheet.getRow(index + headerOffset)
                val cell: Cell = row.createCell(0)
                cell.setCellValue(value)
                cell.cellStyle = style
            }
        }
    }

    private fun createSubjectHeader(scheduleFileData: List<SubjectBO?>, row: Row, workbook: Workbook) {
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
                val subjectsOfWeek = scheduleFileData.filterNotNull().filter { !it.laboratory && !it.seminary && !it.english }.distinctBy { it.acronym }.sortedBy { it.acronym }
                subjectsOfWeek.size
                val dayList = mutableListOf<SubjectBO>()
                subjectsOfWeek.map {
                    dayList.add(it)
                    dayList.add(it)
                    dayList.add(it)
                }
                val weekList = dayList + dayList + dayList + dayList + dayList
                for (i in 1..weekList.size) {
                    val cell = row.createCell(i)
                    cell.setCellValue(weekList[i-1].acronym)
                    cell.cellStyle = setStyle(workbook,10,weekList[i-1].color.toCellColor(), true)
                }
            }
        }
    }

    private fun fillData(list: List<SubjectBO?>, sheet: Sheet, workbook: Workbook) {
        //Create cells
        for (i in (headerSize+ headerOffset) until list.size/5 + headerSize+ headerOffset) {
            val row = sheet.getRow(i)
            for(j in 1..list.size/24) {
                val cell = row.createCell(j)

                val pos = list.size/24*(i-headerSize - headerOffset)+j-1
                val subject = list[pos]
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

    fun <T> partition(list: List<T>, n: Int): Array<MutableList<T>?> {
        val size = list.size
        var m = size / n
        if (size % n != 0) m++
        val partition: Array<MutableList<T>?> = arrayOfNulls(m)
        for (i in 0 until m) {
            val fromIndex = i * n
            val toIndex = if (i * n + n < size) i * n + n else size

            partition[i] = list.subList(fromIndex, toIndex).toMutableList()
        }
        return partition
    }

    private fun combineGroupsOfDay(list1: List<SubjectBO?>, list2: List<SubjectBO?>, list3: List<SubjectBO?>): List<SubjectBO?> {
        val newList = mutableListOf<SubjectBO?>()
        for (i in list1.indices) {
            newList.add(list1[i])
            newList.add(list2[i])
            newList.add(list3[i])
        }
        return newList
    }

    private fun paintDay(list: List<SubjectBO?>, day:Int, sheet: Sheet, workbook: Workbook) {
        for(i in 0 until 24) {
            val hoursDay = list.subList((list.size/24)*i,(list.size/24) * (i+1))
            hoursDay.mapIndexed { index, subject ->
                val row = sheet.getRow(headerOffset+headerSize+i)
                val cell = row.createCell(index+15*day+1)
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

    private fun fillMultipleData(list: List<SubjectBO?>, sheet: Sheet, workbook: Workbook) {
        val firstGroup = list.subList(0,list.size/3)
        val secondGroup = list.subList(list.size/3,(list.size/3)*2)
        val thirdGroup = list.subList((list.size/3)*2,list.size)

        for (i in 0 until 5) {
            val firstGroupDay = firstGroup.subList((firstGroup.size/5)*i,(firstGroup.size/5)*(i+1))
            val secondGroupDay = secondGroup.subList((secondGroup.size/5)*i,(secondGroup.size/5)*(i+1))
            val thirdGroupDay = thirdGroup.subList((thirdGroup.size/5)*i,(thirdGroup.size/5)*(i+1))
            val dayList = combineGroupsOfDay(firstGroupDay,secondGroupDay,thirdGroupDay)
            paintDay(dayList, i, sheet, workbook)
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

    private fun closeFile(workbook: Workbook, name: String) {
        val currDir = File("./scheduleFile/")
        val path: String = currDir.absolutePath
        val fileLocation = path.substring(0, path.length) + "/" + name

        val outputStream = FileOutputStream(fileLocation)
        workbook.write(outputStream)
        workbook.close()
    }

    private fun makeTranspose(matrix: MutableList<MutableList<SubjectBO?>>): MutableList<MutableList<SubjectBO?>> {
        val row = matrix.size
        val column = matrix[0].size

        val transpose: MutableList<MutableList<SubjectBO?>> = MutableList(column) { MutableList(row) { null } }
        for (i in 0 until row) {
            for (j in 0 until column) {
                transpose[j][i] = matrix[i][j]
            }
        }
        return transpose
    }

    companion object {
        const val numHourCells = 24
        const val headerOffset = 2
        const val headerSize = 2
    }
}