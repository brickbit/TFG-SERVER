package com.epcc.politech_manager.utils

import com.epcc.politech_manager.subject.SubjectBO

data class CreateScheduleFileBO(
        val id: Long,
        val subjects: List<List<List<SubjectBO?>>>,
        val scheduleType: Int,
        val fileType: Int,
        val degree: String,
        val year: String)