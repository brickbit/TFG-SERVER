package com.epcc.politech_manager.classroom

import org.springframework.stereotype.Service

@Service
class ClassroomService(val db: ClassroomRepository) {

    fun getAllClassrooms(): List<Classrooms> = db.getAllClassrooms()

    fun post(classrooms: Classrooms) {
        db.save(classrooms)
    }

    fun getClassroom(id: String): Classrooms {
        return db.getClassrooms(id)
    }

    fun deleteClassroom(id: String) {
        db.deleteClassroom(id)
    }

    fun updateClassroom(classrooms: Classrooms, id: String) {
        db.updateClassrooms(classrooms.name, classrooms.pavilion, classrooms.capacity, id)
    }
}