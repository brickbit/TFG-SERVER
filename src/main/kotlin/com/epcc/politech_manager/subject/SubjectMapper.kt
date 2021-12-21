package com.epcc.politech_manager.subject

import com.epcc.politech_manager.classroom.toBO
import com.epcc.politech_manager.classroom.toEntity
import com.epcc.politech_manager.department.toBO
import com.epcc.politech_manager.department.toEntity

fun SubjectEntity.toBO() = Subject(
        name = this.name,
        acronym = this.acronym,
        group = this.classGroup,
        seminary = this.seminary,
        laboratory = this.laboratory,
        english = this.english,
        time = this.time,
        classroom = this.classroom.toBO(),
        department = this.department.toBO(),
        color = this.color,
        id = this.id)

fun Subject.toEntity() = SubjectEntity(
        name = this.name,
        acronym = this.acronym,
        classGroup = this.group,
        seminary = this.seminary,
        laboratory = this.laboratory,
        english = this.english,
        time = this.time,
        classroom = this.classroom.toEntity(emptyList()),
        department = this.department.toEntity(emptyList()),
        color = this.color,
        id = this.id)