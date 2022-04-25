package com.epcc.politech_manager.exam

import com.epcc.politech_manager.subject.toBO
import com.epcc.politech_manager.subject.toDAO
import com.epcc.politech_manager.subject.toDTO
import com.epcc.politech_manager.user.UserEntityDAO

fun ExamEntityDTO.toDAO(user: UserEntityDAO) = ExamEntityDAO(
        subject = this.subject.toDAO(user),
        acronym = this.acronym,
        semester = semester,
        date = date,
        call = call,
        turn = this.turn,
        id = this.id,
        user = user
)

fun ExamEntityDAO.toDTO() = ExamEntityDTO(
        subject = this.subject.toDTO(),
        acronym = this.acronym,
        semester = semester,
        date = date,
        call = call,
        turn = this.turn,
        id = this.id
)

fun ExamEntityDTO.toBO() = ExamBO(
        subject = this.subject.toBO(),
        acronym = this.acronym,
        semester = semester,
        date = date,
        call = call,
        turn = this.turn,
        id = this.id
)

fun ExamBO.toDTO() = ExamEntityDTO(
        subject = this.subject.toDTO("0","0", "0"),
        acronym = this.acronym,
        semester = semester,
        date = date,
        call = call,
        turn = this.turn,
        id = this.id
)