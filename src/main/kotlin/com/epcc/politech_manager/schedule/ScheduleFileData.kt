package com.epcc.politech_manager.schedule

data class ScheduleFileData(val degree: String, val semester: Int, val subjects: List<List<SubjectDegree?>>, val subjectsName: List<SubjectDegree>, val sizeOfDays: List<Int>, val emptyMorning: Boolean, val emptyAfternoon: Boolean, val year: String, val deletedCols: List<Int>)
