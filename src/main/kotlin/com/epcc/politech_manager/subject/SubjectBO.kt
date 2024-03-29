package com.epcc.politech_manager.subject

import com.epcc.politech_manager.classroom.ClassroomBO
import com.epcc.politech_manager.degree.DegreeBO
import com.epcc.politech_manager.department.DepartmentBO
import com.epcc.politech_manager.teacher.TeacherBO
import java.util.*

data class SubjectBO(
        val name: String,
        val acronym: String,
        val classGroup: String,
        val seminary: Boolean,
        val laboratory: Boolean,
        val english: Boolean,
        val time: Int,
        val semester: Int,
        val classroom: ClassroomBO,
        val department: DepartmentBO,
        val teacher: TeacherBO?,
        val degree: DegreeBO,
        val color: Int,
        val id: Long = -1) {
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
            "department": $department,
            "teacher": $teacher
        }""".trimMargin()

}