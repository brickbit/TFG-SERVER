package com.epcc.politech_manager.schedule

import com.epcc.politech_manager.subject.SubjectBO

data class ScheduleFileData(val degree: String, val semester: Int, val subjects: List<List<SubjectBO?>>, val subjectsName: List<SubjectBO>, val sizeOfDays: List<Int>, val emptyMorning: Boolean, val emptyAfternoon: Boolean, val year: String, val deletedCols: List<Int>)
