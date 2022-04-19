package com.epcc.politech_manager.calendar

import com.epcc.politech_manager.exam.ExamBO
import com.epcc.politech_manager.user.UserEntityDAO
import com.google.gson.Gson

fun CalendarEntityDTO.toDAO(user: UserEntityDAO) = CalendarEntityDAO(
        exams = this.exams,
        degree = this.degree,
        year = this.year,
        startDate = this.startDate,
        endDate = this.endDate,
        call = this.call,
        id = this.id,
        user = user
)

fun CalendarEntityDAO.toDTO() = CalendarEntityDTO(
        exams = this.exams,
        degree = this.degree,
        year = this.year,
        startDate = this.startDate,
        endDate = this.endDate,
        call = this.call,
        id = this.id
)

fun CalendarBO.toDTO(): CalendarEntityDTO {
    val exams = this.exams.map { "$it;" }
    return CalendarEntityDTO(
            exams = exams.reduce { acc, string -> acc + string  },
            degree = this.degree,
            year = this.year,
            startDate = this.startDate,
            endDate = this.endDate,
            call = this.call,
            id = this.id
    )
}

fun CalendarEntityDTO.toBO(): CalendarBO {
    val exams: MutableList<String> = this.exams.split(";").toMutableList()
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