package com.epcc.politech_manager.classroom

import com.epcc.politech_manager.user.UserEntityDAO


fun ClassroomEntityDTO.toBO() = ClassroomBO(
        name = this.name,
        pavilion = this.pavilion,
        acronym = this.acronym,
        id = this.id)

fun ClassroomBO.toDTO() = ClassroomEntityDTO(
        name = this.name,
        pavilion = this.pavilion,
        acronym = this.acronym,
        id = this.id)

fun ClassroomEntityDTO.toDAO(user: UserEntityDAO) = ClassroomEntityDAO(
        name = this.name,
        pavilion = this.pavilion,
        acronym = this.acronym,
        id = this.id,
        user = user
)

fun ClassroomEntityDAO.toDTO() = ClassroomEntityDTO(
        name = this.name,
        pavilion = this.pavilion,
        acronym = this.acronym,
        id = this.id
)