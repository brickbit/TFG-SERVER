package com.epcc.politech_manager.subject

import com.epcc.politech_manager.classroom.Classroom
import com.epcc.politech_manager.department.Department
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Subject(
        val name: String,
        val acronym: String,
        val group: String,
        val seminary: Boolean,
        val laboratory: Boolean,
        val english: Boolean,
        val time: Int,
        val classrooms: Classroom?,
        val department: Department?,
        val color: Int,
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long = -1) {
    private constructor() : this("","","",false,false,false,0, null,null,0)
}