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
        classroom = this.classroom.toBO(),
        color = this.color,
        id = this.id,
        semester = this.semester,
        department = this.department.toBO(),
        degree = degree.toBO())

fun SubjectBO.toDTO(days:String, hours: String, turns: String) = SubjectEntityDTO(
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
        classroom = this.classroom.toDTO(),
        department = this.department.toDTO(),
        degree = this.degree.toDTO(),
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
        classroom = this.classroom.toDAO(user),
        department = this.department.toDAO(user),
        degree = this.degree.toDAO(user),
        color = this.color,
        id = this.id,
        userEntity = user
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
        classroom = this.classroom.toDTO(),
        department = this.department.toDTO(),
        degree = this.degree.toDTO(),
        color = this.color,
        id = this.id
)
