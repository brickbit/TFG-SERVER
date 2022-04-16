package com.epcc.politech_manager.exam

import org.springframework.stereotype.Service

@Service
class ExamService(val db: ExamRepository) {

    fun getAllExams(): List<ExamEntityDAO> = db.findAll().toList()

    fun post(exam: ExamEntityDAO) {
        db.save(exam)
    }

    fun  getExam(id: Long): ExamEntityDAO {
        return db.findById(id).orElse(null)
    }

    fun  deleteExam(id: Long) {
        db.deleteById(id)
    }

    fun updateExam(exam: ExamEntityDAO) {
        if(db.existsById(exam.id)) {
            db.save(exam)
        }
    }
}