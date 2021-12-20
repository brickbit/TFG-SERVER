package com.epcc.politech_manager.schedule

import com.epcc.politech_manager.utils.CellColor
import com.epcc.politech_manager.utils.FileType
import com.epcc.politech_manager.utils.ScheduleType

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

fun Int.toScheduleType(): ScheduleType {
    return when (this) {
        0 -> ScheduleType.ONE_SUBJECT
        else -> ScheduleType.MULTIPLE_SUBJECT_MULTIPLE_CLASSROOM
    }
}

fun Int.toFileType(): FileType {
    return when (this) {
        0 -> FileType.SUBJECT
        1 -> FileType.DEPARTMENT
        else -> FileType.CLASSROOM
    }
}