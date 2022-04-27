package com.epcc.politech_manager.calendar

import com.epcc.politech_manager.exam.ExamBO
import com.epcc.politech_manager.exam.ExamEntityDTO
import com.epcc.politech_manager.exam.toDTO
import com.epcc.politech_manager.user.UserEntityDAO
import com.google.gson.Gson

fun CalendarEntityDTO.toDAO(user: UserEntityDAO): CalendarEntityDAO {
    val listExams = this.exams.map { it }
    val listString = listExams.joinToString(";")
    return CalendarEntityDAO(
            exams = listString,
            degree = this.degree,
            year = this.year,
            startDate = this.startDate,
            endDate = this.endDate,
            call = this.call,
            id = this.id,
            userEntity = user
    )
}

fun CalendarEntityDAO.toDTO(): CalendarEntityDTO{
    val examString = this.exams.split(";")
    val examsParsed = examString.map { Gson().fromJson(it,ExamEntityDTO::class.java) }
    return CalendarEntityDTO(
            exams = examsParsed,
            degree = this.degree,
            year = this.year,
            startDate = this.startDate,
            endDate = this.endDate,
            call = this.call,
            id = this.id)
}

fun CalendarBO.toDTO(): CalendarEntityDTO {
    return CalendarEntityDTO(
            exams = this.exams.map { it.toDTO() },
            degree = this.degree,
            year = this.year,
            startDate = this.startDate,
            endDate = this.endDate,
            call = this.call,
            id = this.id
    )
}

fun CalendarEntityDTO.toBO(): CalendarBO {
    val exams: MutableList<String> = this.exams.map { "$it;" }.toMutableList()
    exams.removeLast()
    val examsBO: List<ExamBO> = exams.map {
        Gson().fromJson(it, ExamBO::class.java)
    }
    return CalendarBO(
            exams = examsBO,
            degree = this.degree,
            year = this.year,
            startDate = this.startDate,
            endDate = this.endDate,
            call = this.call,
            id = this.id
    )
}