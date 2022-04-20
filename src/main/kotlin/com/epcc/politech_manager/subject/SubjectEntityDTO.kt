package com.epcc.politech_manager.subject

import com.epcc.politech_manager.classroom.ClassroomBO
import com.epcc.politech_manager.classroom.ClassroomEntityDAO
import com.epcc.politech_manager.degree.DegreeBO
import com.epcc.politech_manager.degree.DegreeEntityDAO
import com.epcc.politech_manager.department.DepartmentBO
import com.epcc.politech_manager.department.DepartmentEntityDAO
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import java.util.*
import javax.persistence.*

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
        val classroom: ClassroomBO,
        val department: DepartmentBO,
        val degree: DegreeBO,
        val color: Int,
        val id: String = UUID.randomUUID().toString())