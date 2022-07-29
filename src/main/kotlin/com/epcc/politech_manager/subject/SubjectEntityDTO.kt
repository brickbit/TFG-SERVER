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
        val classroom: ClassroomEntityDTO,
        val department: DepartmentEntityDTO,
        val degree: DegreeEntityDTO,
        val color: Int,
        val id: Long =-1) {
    override fun toString() = """{
            "name": "$name", 
            "acronym": "$acronym",
            "classGroup": "$classGroup",
            "seminary": $seminary,
            "laboratory": $laboratory,
            "english": $english,
            "time": $time,
            "color": $color,
            "id": $id,
            "semester": $semester,
            "degree": $degree,
            "classroom": $classroom,
            "department": $department
        }""".trimMargin()
}