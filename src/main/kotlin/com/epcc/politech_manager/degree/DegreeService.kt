package com.epcc.politech_manager.degree

import org.springframework.stereotype.Service

@Service
class DegreeService(val db: DegreeRepository) {

    fun getAllDegrees(): List<DegreeEntityDAO> = db.findAll().toList()

    fun post(degree: DegreeEntityDAO) {
        db.save(degree)
    }

    fun getDegree(id: Long): DegreeEntityDAO? {
        return db.findById(id).orElse(null)
    }

    fun deleteDegree(id: Long) {
        db.deleteById(id)
    }

    fun updateDegree(degree: DegreeEntityDAO) {
        if(db.existsById(degree.id)) {
            db.save(degree)
        }
    }
}