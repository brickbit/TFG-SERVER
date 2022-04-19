package com.epcc.politech_manager.degree

import com.epcc.politech_manager.error.DataException
import com.epcc.politech_manager.error.ExceptionDataModel
import org.springframework.stereotype.Service

@Service
class DegreeService(val db: DegreeRepository) {

    fun getAllDegrees(): List<DegreeEntityDAO> = db.findAll().toList()

    fun post(degree: DegreeEntityDAO) {
        db.save(degree)
    }

    fun getDegree(id: String): DegreeEntityDAO? {
        return db.findById(id).orElse(null)
    }

    fun deleteDegree(id: String) {
        try {
            db.deleteById(id)
        } catch (e: Exception) {
            throw DataException(ExceptionDataModel.DEGREE_NOT_EXIST)
        }
    }

    fun updateDegree(degree: DegreeEntityDAO) {
        if(db.existsById(degree.id)) {
            db.save(degree)
        } else {
            throw DataException(ExceptionDataModel.DEGREE_NOT_EXIST)
        }
    }
}