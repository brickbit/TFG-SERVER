package com.epcc.politech_manager.subject

import org.springframework.stereotype.Service

@Service
class SubjectService(val db: SubjectRepository) {

    fun getAllSubject(): List<SubjectsBO> = db.getAllSubjects()

    fun post(subject: Subjects) {
        db.save(subject)
    }

    fun getSubject(id: String): SubjectsBO {
        return db.getSubject(id)
    }

    fun deleteSubject(id: String) {
        db.deleteSubject(id)
    }

    fun updateSubject(subject: SubjectsBO, id: String) {
        db.updateSubject(
                subject.name,
                subject.semester,
                subject.laboratory,
                subject.conference,
                subject.time_subject,
                subject.id_classroom,
                subject.id_department,
                subject.id_degree,
                id)
    }
}