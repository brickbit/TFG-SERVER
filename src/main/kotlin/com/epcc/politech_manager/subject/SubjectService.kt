package com.epcc.politech_manager.subject

import org.springframework.stereotype.Service

@Service
class SubjectService(val db: SubjectRepository) {

    fun getAllSubject(): List<Subject> = db.findAll().toList()

    fun post(subject: Subject) {
        db.save(subject)
    }

    fun getSubject(id: Long): Subject? {
        return db.findById(id).orElse(null)
    }

    fun deleteSubject(id: Long) {
        db.deleteById(id)
    }

    fun updateSubject(subject: Subject) {
        db.save(subject)
    }
}