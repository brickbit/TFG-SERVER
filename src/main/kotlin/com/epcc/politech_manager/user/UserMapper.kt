package com.epcc.politech_manager.user

import com.epcc.politech_manager.degree.DegreeEntityDAO
import com.epcc.politech_manager.department.DepartmentEntityDAO

fun UserEntityDTO.toDAO(
        degrees: MutableList<DegreeEntityDAO>,
        departments: MutableList<DepartmentEntityDAO>) = UserEntityDAO(
        name = name,
        email = email,
        password = password,
        token = token,
        tokenForgotPassword = tokenForgotPassword,
        tokenForgotPasswordCreationDate = tokenForgotPasswordCreationDate,
        id = id,
        degrees = degrees,
        department = departments
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