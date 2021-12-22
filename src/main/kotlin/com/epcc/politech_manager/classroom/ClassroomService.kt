package com.epcc.politech_manager.classroom


import org.springframework.stereotype.Service

@Service
class ClassroomService(val db: ClassroomRepository) {

    fun getAllClassrooms(): List<ClassroomEntity> = db.findAll().toList()

    fun post(classrooms: ClassroomEntity) {
        db.save(classrooms)
    }

    fun getClassroom(id: Long): ClassroomEntity? {
        return db.findById(id).orElse(null)
    }

    fun deleteClassroom(id: Long) {
        db.deleteById(id)
    }

    fun updateClassroom(classroom: ClassroomEntity) {
        if(db.existsById(classroom.id)) {
            db.save(classroom)
        }
    }
}