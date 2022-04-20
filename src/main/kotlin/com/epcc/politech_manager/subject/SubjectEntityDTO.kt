package com.epcc.politech_manager.subject

import com.epcc.politech_manager.classroom.ClassroomEntityDTO
import com.epcc.politech_manager.degree.DegreeEntityDTO
import com.epcc.politech_manager.department.DepartmentEntityDTO
import java.util.*

data class SubjectEntityDTO(
        val name: String,
        val acronym: String,
        val classGroup: String,
        val seminary: Boolean,
        val laboratory: Boolean,
        val english: Boolean,
        val time: Int,
        val semester: Int,
        val days: String,
        val hours: String,
        val turns: String,
        val classroom: ClassroomEntityDTO,
        val department: DepartmentEntityDTO,
        val degree: DegreeEntityDTO,
        val color: Int,
        val id: String = UUID.randomUUID().toString())