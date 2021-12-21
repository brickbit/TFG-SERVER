package com.epcc.politech_manager.classroom


import org.springframework.stereotype.Service

@Service
class ClassroomService(val db: ClassroomRepository) {

    fun getAllClassrooms(): List<Classroom> = db.findAll().map { it.toBO() }.toList()

    fun post(classrooms: Classroom) {
        db.save(classrooms.toEntity(emptyList()))
    }

    fun getClassroom(id: Long): Classroom? {
        return db.findById(id).orElse(null).toBO()
    }

    fun deleteClassroom(id: Long) {
        db.deleteById(id)
    }

    fun updateClassroom(classroom: Classroom) {
        if(db.existsById(classroom.id)) {
            db.save(classroom.toEntity(db.getById(classroom.id).subjects))
        }
    }
}