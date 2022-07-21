package com.epcc.politech_manager.schedule

import com.epcc.politech_manager.subject.SubjectBO

data class ScheduleBO(
        val id: Long,
        val subjects: List<SubjectBO?>,
        val scheduleType: Int,
        val fileType: Int,
        val degree: String,
        val year: String)