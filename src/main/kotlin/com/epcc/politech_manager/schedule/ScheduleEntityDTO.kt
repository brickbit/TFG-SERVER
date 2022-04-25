package com.epcc.politech_manager.schedule

import java.util.*

data class ScheduleEntityDTO(
        val subjects: String,
        val scheduleType: Int,
        val fileType: Int,
        val degree: String,
        val year: String,
        val id: String = UUID.randomUUID().toString())