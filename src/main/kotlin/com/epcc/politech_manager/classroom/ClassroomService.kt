package com.epcc.politech_manager.classroom

import org.springframework.stereotype.Service

@Service
class ClassroomService(val db: ClassroomRepository) {

    fun getAllClassrooms(): List<Classroom> = db.findAll().toList()

    fun post(classrooms: Classroom) {
        db.save(classrooms)
    }

    fun getClassroom(id: Long): Classroom? {
        return db.findById(id).orElse(null)
    }

    fun deleteClassroom(id: Long) {
        db.deleteById(id)
    }

    fun updateClassroom(classrooms: Classroom) {
        db.save(classrooms)
    }
}