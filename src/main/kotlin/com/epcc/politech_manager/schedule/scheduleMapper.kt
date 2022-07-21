package com.epcc.politech_manager.schedule

import com.epcc.politech_manager.subject.SubjectBO
import com.epcc.politech_manager.subject.SubjectEntityDTO
import com.epcc.politech_manager.subject.toBO
import com.epcc.politech_manager.subject.toDTO
import com.epcc.politech_manager.user.UserEntityDAO
import com.epcc.politech_manager.utils.ScheduleType
import com.epcc.politech_manager.utils.toScheduleType
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

fun ScheduleEntityDTO.toDAO(user: UserEntityDAO) = ScheduleEntityDAO(
        subjects = this.subjects,
        scheduleType = this.scheduleType,
        fileType = this.fileType,
        degree = this.degree,
        year = this.year,
        id = this.id,
        userEntity = user
)

fun ScheduleEntityDAO.toDTO() = ScheduleEntityDTO(
        subjects = this.subjects,
        scheduleType = this.scheduleType,
        fileType = this.fileType,
        degree = this.degree,
        year = this.year,
        id = this.id
)

fun ScheduleEntityDAO.toBO(): ScheduleBO {
    val subjectsList: List<String> = this.subjects.split(";")
    val list: List<SubjectBO?> = subjectsList.map {
        Gson().fromJson(it,SubjectBO::class.java)
    }
    return ScheduleBO(
            subjects = list,
            scheduleType = this.scheduleType,
            fileType = this.fileType,
            degree = this.degree,
            year = this.year,
            id = this.id
    )
}

fun expandMatrix(subjects: List<SubjectEntityDTO>, scheduletype: ScheduleType): List<List<List<SubjectBO?>>> {
    val matrix: Array<Array<Array<SubjectBO?>>> = when (scheduletype) {
        ScheduleType.ONE_SUBJECT -> {
            Array(5){ Array(24) {Array(1) {null} } }
        }
        ScheduleType.MULTIPLE_SUBJECT_MULTIPLE_CLASSROOM -> {
            Array(15){ Array(24) {Array(1) {null} } }
        }
    }
    val groupedList = subjects.groupBy { it.id }
    groupedList.map { entries ->
        entries.value.map { subject ->
            val days = subject.days.split(",").map { num ->
                if (num != "") num.toInt() else 0
            }
            val hours = subject.hours.split(",").map { num ->
                if (num != "") num.toInt() else 0
            }
            val turns = subject.turns.split(",").map { num ->
                if (num != "") num.toInt() else 0
            }

            days.mapIndexed { pos, value ->
                matrix[value][hours[pos]][turns[pos]] = subject.toBO()
            }
        }
    }
    return matrix.map { hours -> hours.map { it.toList() }.toList() }.toList()
}

