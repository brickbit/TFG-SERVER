package com.epcc.politech_manager.schedule

import com.epcc.politech_manager.subject.SubjectBO
import com.epcc.politech_manager.user.UserEntityDAO
import com.google.gson.Gson

fun ScheduleEntityDTO.toDAO(user: UserEntityDAO) = ScheduleEntityDAO(
        subjects = this.subjects,
        scheduleType = this.scheduleType,
        fileType = this.fileType,
        degree = this.degree,
        semester = this.semester,
        year = this.year,
        id = this.id,
        userEntity = user
)

fun ScheduleEntityDAO.toDTO() = ScheduleEntityDTO(
        subjects = this.subjects,
        scheduleType = this.scheduleType,
        fileType = this.fileType,
        degree = this.degree,
        semester = this.semester,
        year = this.year,
        id = this.id
)

fun ScheduleEntityDAO.toBO(): ScheduleBO {
    val subjectsList: List<String> = this.subjects.split(Regex(";"))
    val list: List<SubjectBO?> = subjectsList.map {
        Gson().fromJson(it, SubjectBO::class.java)
    }
    return ScheduleBO(
            subjects = list,
            scheduleType = this.scheduleType,
            fileType = this.fileType,
            degree = this.degree,
            semester = this.semester,
            year = this.year,
            id = this.id
    )
}

