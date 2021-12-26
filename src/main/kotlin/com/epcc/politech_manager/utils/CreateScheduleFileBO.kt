package com.epcc.politech_manager.utils

import com.epcc.politech_manager.subject.Subject

data class CreateScheduleFileBO(val subjects: List<List<List<Subject?>>>, val scheduleType: Int, val fileType: Int, val degree: String, val year: String)