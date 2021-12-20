package com.epcc.politech_manager.schedule

import com.epcc.politech_manager.subject.Subject

data class ScheduleFileData(val degree: String, val semester: Int, val subjects: List<List<Subject?>>, val subjectsName: List<Subject>, val sizeOfDays: List<Int>, val emptyMorning: Boolean, val emptyAfternoon: Boolean, val year: String, val deletedCols: List<Int>)
