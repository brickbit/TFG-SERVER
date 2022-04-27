package com.epcc.politech_manager.classroom


import com.epcc.politech_manager.error.DataException
import com.epcc.politech_manager.error.ExceptionDataModel
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
        try {
            db.deleteById(id)
        } catch (e: Exception) {
            throw DataException(ExceptionDataModel.CLASSROOM_NOT_EXIST)
        }
    }

    fun updateClassroom(classroom: ClassroomEntityDAO) {
        if(db.existsById(classroom.id)) {
            db.save(classroom)
        } else {
            throw DataException(ExceptionDataModel.CLASSROOM_NOT_EXIST)
        }
    }
}