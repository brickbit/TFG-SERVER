package com.epcc.politech_manager.subject

import com.epcc.politech_manager.error.DataException
import com.epcc.politech_manager.error.ExceptionDataModel
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class SubjectService(val db: SubjectRepository) {

    fun getAllSubjects(): List<SubjectEntityDAO> = db.findAll().toList()

    fun post(subject: SubjectEntityDAO) {
        db.save(subject)
    }

    fun getSubject(id: String): SubjectEntityDAO? {
        return db.findById(id).orElse(null)
    }

    @Transactional
    fun deleteSubject(id: String) {
        try {
            db.deleteById(id)
        } catch (e: Exception) {
            throw DataException(ExceptionDataModel.SUBJECT_NOT_EXIST)
        }
    }

    fun updateSubject(subject: SubjectEntityDAO) {
        if(db.existsById(subject.id)) {
            db.save(subject)
        } else {
            throw DataException(ExceptionDataModel.SUBJECT_NOT_EXIST)
        }
    }
}
