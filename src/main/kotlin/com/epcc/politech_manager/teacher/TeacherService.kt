package com.epcc.politech_manager.teacher

import com.epcc.politech_manager.error.DataException
import com.epcc.politech_manager.error.ExceptionDataModel
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class TeacherService(val db: TeacherRepository) {

    fun getAllTeachers(): List<TeacherEntityDAO> = db.findAll().toList()

    fun post(teacher: TeacherEntityDAO) {
        db.save(teacher)
    }

    fun getTeacher(id: Long): TeacherEntityDAO? {
        return db.findById(id).orElse(null)
    }

    @Transactional
    fun deleteTeacher(id: Long) {
        try {
            db.deleteById(id)
        } catch (e: Exception) {
            throw DataException(ExceptionDataModel.TEACHER_NOT_EXIST)
        }
    }

    fun updateTeacher(teacher: TeacherEntityDAO) {
        if(db.existsById(teacher.id)) {
            db.save(teacher)
        } else {
            throw DataException(ExceptionDataModel.TEACHER_NOT_EXIST)
        }
    }
}
