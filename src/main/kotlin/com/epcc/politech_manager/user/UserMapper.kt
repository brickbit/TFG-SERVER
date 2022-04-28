package com.epcc.politech_manager.user



fun UserEntityDTO.toDAO() = UserEntityDAO(
        name = name,
        email = email,
        password = password,
        token = token,
        tokenForgotPassword = tokenForgotPassword,
        tokenForgotPasswordCreationDate = tokenForgotPasswordCreationDate,
        id = id
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