package com.epcc.politech_manager.department

import com.epcc.politech_manager.user.UserEntityDAO


fun DepartmentEntityDTO.toBO() = DepartmentBO(
        name = this.name,
        acronym = this.acronym,
        id = this.id)

fun DepartmentEntityDAO.toBO() = DepartmentBO(
        name = this.name,
        acronym = this.acronym,
        id = this.id)

fun DepartmentBO.toDTO() = DepartmentEntityDTO(
        name = this.name,
        acronym = this.acronym,
        id = this.id)

fun DepartmentEntityDTO.toDAO(user: UserEntityDAO) = DepartmentEntityDAO(
        name = this.name,
        acronym = this.acronym,
        id = this.id,
        user = user
)

fun DepartmentEntityDAO.toDTO() = DepartmentEntityDTO(
        name = this.name,
        acronym = this.acronym,
        id = this.id
)