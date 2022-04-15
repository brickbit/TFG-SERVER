package com.epcc.politech_manager.subject

import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class SubjectService(val db: SubjectRepository) {

    fun getAllSubject(): List<SubjectEntityDAO> = db.findAll().toList()

    fun post(subject: SubjectEntityDAO) {
        db.save(subject)
    }

    fun getSubject(id: Long): SubjectEntityDAO? {
        return db.findById(id).orElse(null)
    }

    @Transactional
    fun deleteSubject(id: Long) {
        db.deleteById(id)
    }

    fun updateSubject(subject: SubjectEntityDAO) {
        if(db.existsById(subject.id)) {
            db.save(subject)
        }
    }
}
