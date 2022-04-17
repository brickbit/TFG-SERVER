package com.epcc.politech_manager.calendar

import com.epcc.politech_manager.exam.ExamBO

data class CalendarBO(
        val exams: List<ExamBO>,
        val degree: String,
        val year: String,
        val startDate: String,
        val endDate: String,
        val call: String,
        val id: Long
    )