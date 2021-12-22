package com.epcc.politech_manager.schedule

import com.epcc.politech_manager.degree.Degree
import com.epcc.politech_manager.utils.SemesterDegree

data class ScheduleDegree(
        val degree: Degree?,
        val year: String,
        val semester: SemesterDegree?,
        val id: Long = -1)