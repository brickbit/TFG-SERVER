package com.epcc.politech_manager.department

import com.epcc.politech_manager.error.DataException
import com.epcc.politech_manager.error.ExceptionDataModel
import org.springframework.stereotype.Service

@Service
class DepartmentService(val db: DepartmentRepository) {

    fun getAllDepartments(): List<DepartmentEntityDAO> = db.findAll().toList()

    fun post(department: DepartmentEntityDAO) {
        db.save(department)
    }

    fun  getDepartment(id: String): DepartmentEntityDAO? {
        return db.findById(id).orElse(null)
    }

    fun  deleteDepartment(id: String) {
        try {
            db.deleteById(id)
        } catch (e: Exception) {
            throw DataException(ExceptionDataModel.DEPARTMENT_NOT_EXIST)
        }
    }

    fun updateDepartment(department: DepartmentEntityDAO) {
        if(db.existsById(department.id)) {
            db.save(department)
        } else {
            throw DataException(ExceptionDataModel.DEPARTMENT_NOT_EXIST)
        }
    }
}