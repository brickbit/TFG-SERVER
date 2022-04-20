package com.epcc.politech_manager.exam

import com.epcc.politech_manager.subject.SubjectBO

data class ExamBO(
        val subject: SubjectBO,
        val acronym: String,
        val semester: Int,
        val date: String,
        val call: String,
        val turn: String,
        val id: String) {
    override fun toString(): String = """{
        "subject": $subject,
        "acronym": $acronym,
        "semester": $semester,
        "date": "$date",
        "call": $call,
        "turn": $turn,
        "id": $id
    }""".trimIndent()
}