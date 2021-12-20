package com.epcc.politech_manager.degree

import org.springframework.stereotype.Service

@Service
class DegreeService(val db: DegreeRepository) {

    fun getAllDegrees(): List<Degree> = db.findAll().toList()

    fun post(degree: Degree) {
        db.save(degree)
    }

    fun getDegree(id: Long): Degree? {
        return db.findById(id).orElse(null)
    }

    fun deleteDegree(id: Long) {
        db.deleteById(id)
    }

    fun updateDegree(degree: Degree) {
        db.save(degree)
    }
}