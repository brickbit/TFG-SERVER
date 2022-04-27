package com.epcc.politech_manager.calendar

import com.epcc.politech_manager.exam.ExamEntityDTO

data class CalendarEntityDTO(
        val exams: List<ExamEntityDTO>,
        val degree: String,
        val year: String,
        val startDate: String,
        val endDate: String,
        val call: String,
        val id: Long = -1) {
    override fun toString(): String = """{
        "exams": ${exams.map { toString() }},
        "degree": $degree,
        "year": $year,
        "startDate": "$startDate",
        "endDate": $endDate,
        "call": $call,
        "id": $id
    }""".trimIndent()
}