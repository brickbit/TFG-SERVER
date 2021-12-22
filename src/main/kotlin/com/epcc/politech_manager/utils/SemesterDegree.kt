package com.epcc.politech_manager.utils

import com.epcc.politech_manager.subject.Subject

data class SemesterDegree(
        val num: Int,
        val subjectsInSemester: List<List<List<Subject?>>>,
        val id: Long = -1)
