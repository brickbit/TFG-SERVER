package com.epcc.politech_manager.teacher

import com.epcc.politech_manager.department.DepartmentBO


data class TeacherBO(val name: String,
                    val department: DepartmentBO,
                    val id: Long) {
    override fun toString(): String = """{
           "name": "$name",
           "department": $department,
           "id": $id
    }""".trimMargin()
}