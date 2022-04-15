package com.epcc.politech_manager.user

import com.epcc.politech_manager.classroom.ClassroomEntityDAO
import com.epcc.politech_manager.degree.DegreeEntityDAO
import com.epcc.politech_manager.department.DepartmentEntityDAO
import com.epcc.politech_manager.subject.SubjectEntityDAO

fun UserEntityDTO.toDAO(
        degrees: MutableList<DegreeEntityDAO>,
        departments: MutableList<DepartmentEntityDAO>,
        classrooms: MutableList<ClassroomEntityDAO>,
        subjects: MutableList<SubjectEntityDAO>) = UserEntityDAO(
        name = name,
        email = email,
        password = password,
        token = token,
        tokenForgotPassword = tokenForgotPassword,
        tokenForgotPasswordCreationDate = tokenForgotPasswordCreationDate,
        id = id,
        degrees = degrees,
        department = departments,
        classroom = classrooms,
        subject = subjects
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