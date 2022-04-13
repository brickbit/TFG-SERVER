package com.epcc.politech_manager.classroom


import org.springframework.stereotype.Service

@Service
class ClassroomService(val db: ClassroomRepository) {

    fun getAllClassrooms(): List<ClassroomEntityDAO> = db.findAll().toList()

    fun post(classrooms: ClassroomEntityDAO) {
        db.save(classrooms)
    }

    fun getClassroom(id: Long): ClassroomEntityDAO? {
        return db.findById(id).orElse(null)
    }

    fun deleteClassroom(id: Long) {
        db.deleteById(id)
    }

    fun updateClassroom(classroom: ClassroomEntityDAO) {
        if(db.existsById(classroom.id)) {
            db.save(classroom)
        }
    }
}