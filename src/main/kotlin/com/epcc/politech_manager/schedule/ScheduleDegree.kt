package com.epcc.politech_manager.schedule

import com.epcc.politech_manager.degree.Degree
import com.epcc.politech_manager.semester.SemesterDegree
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class ScheduleDegree(
        val degree: Degree?,
        val year: String,
        val semester: SemesterDegree?, @Id @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long = -1) {
    private constructor() : this(null,"",null)
}