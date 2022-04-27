package com.epcc.politech_manager.schedule

data class ScheduleEntityDTO(
        val subjects: String,
        val scheduleType: Int,
        val fileType: Int,
        val degree: String,
        val year: String,
        val id: Long = -1)