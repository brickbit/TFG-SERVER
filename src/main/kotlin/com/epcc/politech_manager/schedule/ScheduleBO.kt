package com.epcc.politech_manager.schedule

import com.epcc.politech_manager.subject.SubjectBO

data class ScheduleBO(
        val id: String,
        val subjects: List<List<List<SubjectBO?>>>,
        val scheduleType: Int,
        val fileType: Int,
        val degree: String,
        val year: String)