package com.epcc.politech_manager.teacher

import com.epcc.politech_manager.department.DepartmentEntityDTO

data class TeacherEntityDTO(
        val name: String,
        val department: DepartmentEntityDTO,
        val id: Long =-1) {
    override fun toString() = """{
            "name": "$name", 
            "id": $id,
            "department": $department
        }""".trimMargin()
}