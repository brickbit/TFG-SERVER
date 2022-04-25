package com.epcc.politech_manager.exam

import com.epcc.politech_manager.error.DataException
import com.epcc.politech_manager.error.ExceptionDataModel
import org.springframework.stereotype.Service

@Service
class ExamService(val db: ExamRepository) {

    fun getAllExams(): List<ExamEntityDAO> = db.findAll().toList()

    fun post(exam: ExamEntityDAO) {
        db.save(exam)
    }

    fun  getExam(id: String): ExamEntityDAO? {
        return db.findById(id).orElse(null)
    }

    fun  deleteExam(id: String) {
        try {
            db.deleteById(id)
        } catch (e: Exception) {
            throw DataException(ExceptionDataModel.EXAM_NOT_EXIST)
        }
    }

    fun updateExam(exam: ExamEntityDAO) {
        if(db.existsById(exam.id)) {
            db.save(exam)
        } else {
            throw DataException(ExceptionDataModel.EXAM_NOT_EXIST)
        }
    }
}