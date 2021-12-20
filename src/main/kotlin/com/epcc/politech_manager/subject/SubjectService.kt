package com.epcc.politech_manager.subject

import org.springframework.stereotype.Service

@Service
class SubjectService(val db: SubjectRepository) {

    fun getAllSubject(): List<Subject> = db.findAll().map{ it.toBO() }.toList()

    fun post(subject: Subject) {
        db.save(subject.toEntity())
    }

    fun getSubject(id: Long): Subject? {
        return db.findById(id).orElse(null).toBO()
    }

    fun deleteSubject(id: Long) {
        db.deleteById(id)
    }

    fun updateSubject(subject: Subject) {
        if(db.existsById(subject.id)) {
            db.save(subject.toEntity())
        }
    }
}
