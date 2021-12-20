package com.epcc.politech_manager.semester

import com.epcc.politech_manager.subject.Subject
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class SemesterDegree(
        val num: Int,
        val subjectsInSemester: List<List<List<Subject?>>>,
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long = -1) {
    private constructor() : this(0, emptyList())
}
