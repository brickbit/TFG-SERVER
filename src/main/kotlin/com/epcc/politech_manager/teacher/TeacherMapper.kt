package com.epcc.politech_manager.teacher

import com.epcc.politech_manager.department.toBO
import com.epcc.politech_manager.department.toDAO
import com.epcc.politech_manager.department.toDTO
import com.epcc.politech_manager.user.UserEntityDAO

fun TeacherEntityDTO.toBO() = TeacherBO(
        name = this.name,
        id = this.id,
        department = this.department.toBO(),
        )

fun TeacherBO.toDTO() = TeacherEntityDTO(
        name = this.name,
        department = this.department.toDTO(),
        id = this.id)

fun TeacherEntityDTO.toDAO(user: UserEntityDAO) = TeacherEntityDAO(
        name = this.name,
        department = this.department.toDAO(user),
        id = this.id,
        userEntity = user
)

fun TeacherEntityDAO.toDTO() = TeacherEntityDTO(
        name = this.name,
        department = this.department.toDTO(),
        id = this.id
)
