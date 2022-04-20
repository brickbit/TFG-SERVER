package com.epcc.politech_manager.subject

import com.epcc.politech_manager.classroom.toBO
import com.epcc.politech_manager.classroom.toDAO
import com.epcc.politech_manager.classroom.toDTO
import com.epcc.politech_manager.degree.toBO
import com.epcc.politech_manager.degree.toDAO
import com.epcc.politech_manager.degree.toDTO
import com.epcc.politech_manager.department.toBO
import com.epcc.politech_manager.department.toDAO
import com.epcc.politech_manager.department.toDTO
import com.epcc.politech_manager.user.UserEntityDAO


fun SubjectEntityDTO.toBO() = SubjectBO(
        name = this.name,
        acronym = this.acronym,
        classGroup = this.classGroup,
        seminary = this.seminary,
        laboratory = this.laboratory,
        english = this.english,
        time = this.time,
        classroom = this.classroom.toDTO().toBO(),
        color = this.color,
        id = this.id,
        semester = this.semester,
        department = this.department.toDTO().toBO(),
        degree = degree.toDTO().toBO())

fun SubjectBO.toEntity(days:String, hours: String, turns: String) = SubjectEntityDTO(
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
        degree = this.degree,
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
        classroom = this.classroom.toDTO().toDAO(user),
        department = this.department.toDTO().toDAO(user),
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
        classroom = this.classroom.toBO(),
        department = this.department.toBO(),
        degree = this.degree.toBO(),
        color = this.color,
        id = this.id
)
