package com.epcc.politech_manager.schedule

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