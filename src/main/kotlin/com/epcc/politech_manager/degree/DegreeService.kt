package com.epcc.politech_manager.degree

import org.springframework.stereotype.Service

@Service
class DegreeService(val db: DegreeRepository) {

    fun getAllDegrees(): List<DegreeEntity> = db.findAll().toList()

    fun post(degree: DegreeEntity) {
        db.save(degree)
    }

    fun getDegree(id: Long): DegreeEntity? {
        return db.findById(id).orElse(null)
    }

    fun deleteDegree(id: Long) {
        db.deleteById(id)
    }

    fun updateDegree(degree: DegreeEntity) {
        if(db.existsById(degree.id)) {
            db.save(degree)
        }
    }
}