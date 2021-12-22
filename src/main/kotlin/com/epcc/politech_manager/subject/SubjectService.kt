package com.epcc.politech_manager.subject

import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class SubjectService(val db: SubjectRepository) {

    fun getAllSubject(): List<SubjectEntity> = db.findAll().toList()

    fun post(subject: SubjectEntity?) {
        db.save(subject)
    }

    fun getSubject(id: Long): SubjectEntity? {
        return db.findById(id).orElse(null)
    }

    @Transactional
    fun deleteSubject(id: Long) {
        db.deleteById(id)
    }

    fun updateSubject(subject: SubjectEntity) {
        if(db.existsById(subject.id)) {
            db.save(subject)
        }
    }
}
