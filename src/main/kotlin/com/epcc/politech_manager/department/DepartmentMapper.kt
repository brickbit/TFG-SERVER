package com.epcc.politech_manager.department

import com.epcc.politech_manager.subject.SubjectEntity

fun DepartmentEntity.toBO() = Department(
        name = this.name,
        acronym = this.acronym,
        id = this.id)

fun Department.toEntity(subjects: List<SubjectEntity>) = DepartmentEntity(
        name = this.name,
        acronym = this.acronym,
        subjects = subjects,
        id = this.id)