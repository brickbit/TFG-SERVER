package com.epcc.politech_manager.degree

import com.epcc.politech_manager.user.UserEntityDAO

fun DegreeEntityDTO.toBO() = DegreeBO(
        name = this.name,
        num_semesters = this.num_semesters,
        year = this.year,
        id = this.id)

fun DegreeEntityDAO.toBO() = DegreeBO(
        name = this.name,
        num_semesters = this.num_semesters,
        year = this.year,
        id = this.id)

fun DegreeBO.toDTO() = DegreeEntityDTO(
        name = this.name,
        num_semesters = this.num_semesters,
        year = this.year,
        id = this.id)

fun DegreeEntityDTO.toDAO(user: UserEntityDAO) = DegreeEntityDAO(
        name = this.name,
        num_semesters = this.num_semesters,
        year = this.year,
        id = this.id,
        userEntity = user
)

fun DegreeEntityDAO.toDTO() = DegreeEntityDTO(
        name = this.name,
        num_semesters = this.num_semesters,
        year = this.year,
        id = this.id
)