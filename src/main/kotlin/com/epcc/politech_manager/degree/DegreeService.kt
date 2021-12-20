package com.epcc.politech_manager.degree

import com.epcc.politech_manager.classroom.toEntity
import org.springframework.stereotype.Service

@Service
class DegreeService(val db: DegreeRepository) {

    fun getAllDegrees(): List<Degree> = db.findAll().map { it.toBO() }.toList()

    fun post(degree: Degree) {
        db.save(degree.toEntity())
    }

    fun getDegree(id: Long): Degree? {
        return db.findById(id).orElse(null).toBO()
    }

    fun deleteDegree(id: Long) {
        db.deleteById(id)
    }

    fun updateDegree(degree: Degree) {
        if(db.existsById(degree.id)) {
            db.save(degree.toEntity())
        }
    }
}