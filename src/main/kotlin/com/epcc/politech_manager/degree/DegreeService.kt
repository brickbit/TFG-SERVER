package com.epcc.politech_manager.degree

import org.springframework.stereotype.Service

@Service
class DegreeService(val db: DegreeRepository) {

    fun getAllDegrees(): List<Degrees> = db.getAllDegrees()

    fun post(degrees: Degrees) {
        db.save(degrees)
    }

    fun getDegree(id: String): Degrees {
        return db.getDegree(id)
    }

    fun deleteDegree(id: String) {
        db.deleteDegree(id)
    }

    fun updateDegree(degrees: Degrees, id: String) {
        db.updateDegrees(degrees.name, degrees.num_semesters, id)
    }
}