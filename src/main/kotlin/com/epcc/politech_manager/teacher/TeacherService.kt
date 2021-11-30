package com.epcc.politech_manager.teacher

import org.springframework.stereotype.Service

@Service
class TeacherService(val db: TeacherRepository) {

    fun getAllTeachers(): List<TeachersBO> = db.getAllTeachers()

    fun post(teacher: Teachers) {
        db.save(teacher)
    }

    fun getTeacher(id: String): TeachersBO {
        return db.getTeacher(id)
    }

    fun deleteTeacher(id: String) {
        db.deleteTeacher(id)
    }

    fun updateTeacher(teacher: Teachers, id: String) {
        db.updateTeacher(teacher.name, teacher.id_department, id)
    }
}