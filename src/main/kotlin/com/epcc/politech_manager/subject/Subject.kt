package com.epcc.politech_manager.subject

import com.epcc.politech_manager.classroom.Classroom
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
        val classroom: Classroom,
        val department: Department,
        val color: Int,
        val id: Long = -1)