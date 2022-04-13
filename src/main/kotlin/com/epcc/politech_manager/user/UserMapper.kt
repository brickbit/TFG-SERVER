package com.epcc.politech_manager.user

import com.epcc.politech_manager.degree.DegreeEntityDAO

fun UserEntityDTO.toDAO(degrees: MutableList<DegreeEntityDAO>) = UserEntityDAO(
        name = name,
        email = email,
        password = password,
        token = token,
        tokenForgotPassword = tokenForgotPassword,
        tokenForgotPasswordCreationDate = tokenForgotPasswordCreationDate,
        id = id,
        degrees = degrees
)

fun UserEntityDAO.toDTO() = UserEntityDTO(
        name = name,
        email = email,
        password = password,
        token = token,
        tokenForgotPassword = tokenForgotPassword,
        tokenForgotPasswordCreationDate = tokenForgotPasswordCreationDate,
        id = id
)