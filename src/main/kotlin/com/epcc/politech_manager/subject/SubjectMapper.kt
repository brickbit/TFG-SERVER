package com.epcc.politech_manager.subject

import com.epcc.politech_manager.classroom.toBO
import com.epcc.politech_manager.classroom.toEntity
import com.epcc.politech_manager.degree.toBO
import com.epcc.politech_manager.degree.toDAO
import com.epcc.politech_manager.degree.toDTO
import com.epcc.politech_manager.department.toBO
import com.epcc.politech_manager.department.toEntity
import com.epcc.politech_manager.user.UserEntityDAO


fun SubjectEntityDTO.toBO() = SubjectBO(
        name = this.name,
        acronym = this.acronym,
        classGroup = this.classGroup,
        seminary = this.seminary,
        laboratory = this.laboratory,
        english = this.english,
        time = this.time,
        classroom = this.classroom.toBO(),
        color = this.color,
        id = this.id,
        semester = this.semester,
        department = this.department.toBO(),
        degree = degree.toDTO().toBO())

fun SubjectBO.toEntity(days:String, hours: String, turns: String, user: UserEntityDAO) = SubjectEntityDTO(
        name = this.name,
        acronym = this.acronym,
        classGroup = this.classGroup,
        seminary = this.seminary,
        laboratory = this.laboratory,
        english = this.english,
        time = this.time,
        semester = this.semester,
        days = days,
        hours = hours,
        turns = turns,
        classroom = this.classroom.toEntity(),
        department = this.department.toEntity(),
        degree = this.degree.toDTO().toDAO(user),
        color = this.color,
        id = this.id)

fun SubjectEntityDTO.toDAO(user: UserEntityDAO) = SubjectEntityDAO(
        name = this.name,
        acronym = this.acronym,
        classGroup = this.classGroup,
        seminary = this.seminary,
        laboratory = this.laboratory,
        english = this.english,
        time = this.time,
        semester = this.semester,
        days = days,
        hours = hours,
        turns = turns,
        classroom = this.classroom,
        department = this.department,
        degree = this.degree.toDTO().toDAO(user),
        color = this.color,
        id = this.id,
        user = user
)

fun SubjectEntityDAO.toDTO() = SubjectEntityDTO(
        name = this.name,
        acronym = this.acronym,
        classGroup = this.classGroup,
        seminary = this.seminary,
        laboratory = this.laboratory,
        english = this.english,
        time = this.time,
        semester = this.semester,
        days = days,
        hours = hours,
        turns = turns,
        classroom = this.classroom,
        department = this.department,
        degree = this.degree.toDTO().toDAO(user),
        color = this.color,
        id = this.id
)
