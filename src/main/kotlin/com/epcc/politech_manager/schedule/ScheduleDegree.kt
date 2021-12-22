package com.epcc.politech_manager.schedule

import com.epcc.politech_manager.degree.Degree
import com.epcc.politech_manager.utils.SemesterDegree

//@Entity
data class ScheduleDegree(
        val degree: Degree?,
        val year: String,
        val semester: SemesterDegree?, //@Id @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long = -1) {
    private constructor() : this(null,"",null)
}