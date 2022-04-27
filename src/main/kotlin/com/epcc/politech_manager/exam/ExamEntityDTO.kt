package com.epcc.politech_manager.exam

import com.epcc.politech_manager.subject.SubjectEntityDTO

data class ExamEntityDTO(
        val subject: SubjectEntityDTO,
        val acronym: String,
        val semester: Int,
        val date: String,
        val call: String,
        val turn: String,
        val id: Long = -1) {
    override fun toString(): String = """{
        "subject": $subject,
        "acronym": "$acronym",
        "semester": $semester,
        "date": "$date",
        "call": "$call",
        "turn": "$turn",
        "id": $id
    }""".trimIndent()
}