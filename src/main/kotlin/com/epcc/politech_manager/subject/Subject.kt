package com.epcc.politech_manager.subject

import com.epcc.politech_manager.classroom.Classroom
import com.epcc.politech_manager.degree.Degree
import com.epcc.politech_manager.department.Department
import javax.persistence.*

data class Subject(
        val name: String,
        val acronym: String,
        val group: String,
        val seminary: Boolean,
        val laboratory: Boolean,
        val english: Boolean,
        val time: Int,
        val semester: Int,
        val classroom: Classroom,
        val department: Department,
        val degree: Degree,
        val color: Int,
        val id: Long = -1) {
    override fun toString() = """{
            "name": "$name", 
            "acronym": "$acronym",
            "group": "$group",
            "seminary": $seminary,
            "laboratory": $laboratory,
            "english": $english,
            "time": $time,
            "color": $color,
            "id": $id,
            "semester": $semester,
            "degree": $degree,
            "classroom": $classroom,
            "department": $department,
        }""".trimMargin()

}