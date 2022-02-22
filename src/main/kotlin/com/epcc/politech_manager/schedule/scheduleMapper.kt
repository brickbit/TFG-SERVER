package com.epcc.politech_manager.schedule

import com.epcc.politech_manager.subject.Subject
import com.epcc.politech_manager.subject.SubjectEntity
import com.epcc.politech_manager.subject.toBO
import com.epcc.politech_manager.subject.toEntity
import com.epcc.politech_manager.utils.CreateScheduleFileBO
import com.epcc.politech_manager.utils.ScheduleType
import com.epcc.politech_manager.utils.toScheduleType

fun ScheduleEntity.toScheduleFileBO(): CreateScheduleFileBO {
    return CreateScheduleFileBO(expandMatrix(this.subjects, this.scheduleType.toScheduleType()),this.scheduleType,this.fileType,this.degree,this.year)
}

fun expandMatrix(subjects: List<SubjectEntity>, scheduletype: ScheduleType): List<List<List<Subject?>>> {
    val matrix: Array<Array<Array<Subject?>>> = when (scheduletype) {
        ScheduleType.ONE_SUBJECT -> {
            Array(5){ Array(24) {Array(1) {null} } }
        }
        ScheduleType.MULTIPLE_SUBJECT_MULTIPLE_CLASSROOM -> {
            Array(5){ Array(24) {Array(15) {null} } }
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

fun flatMatrix(matrix: List<List<List<Subject?>>>): List<SubjectEntity> {
    val listSubject = mutableListOf<SubjectEntity>()
    matrix.mapIndexed { i, hour ->
        hour.mapIndexed { j, turn ->
            turn.mapIndexed { k, subject ->
                subject?.let {
                    listSubject.add(it.toEntity("$i","$j","$k"))
                }
            }
        }
    }
    val groupedList = listSubject.groupBy {
        it.id
    }
    val finalList = mutableListOf<SubjectEntity>()
    groupedList.values.map { groupedSubjects ->
        var days = ""
        var hours = ""
        var turns = ""
        var subjectToCopy: SubjectEntity? = null
        groupedSubjects.map {
            days = days + it.days + ","
            hours = hours + it.hours + ","
            turns = turns + it.turns + ","
            subjectToCopy = it
        }
        subjectToCopy?.let {
            finalList.add(SubjectEntity(
                    it.name,
                    it.acronym,
                    it.classGroup,
                    it.seminary,
                    it.laboratory,
                    it.english,
                    it.time,
                    it.semester,
                    days,
                    hours,
                    turns,
                    it.classroom,
                    it.department,
                    it.degree,
                    it.color,
                    it.id
            )
            )
        }

    }
    return finalList
}