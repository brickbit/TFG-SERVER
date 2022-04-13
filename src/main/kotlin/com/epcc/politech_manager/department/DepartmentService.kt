package com.epcc.politech_manager.department

import org.springframework.stereotype.Service

@Service
class DepartmentService(val db: DepartmentRepository) {

    fun getAllDepartments(): List<DepartmentEntityDAO> = db.findAll().toList()

    fun post(department: DepartmentEntityDAO) {
        db.save(department)
    }

    fun  getDepartment(id: Long): DepartmentEntityDAO {
        return db.findById(id).orElse(null)
    }

    fun  deleteDepartment(id: Long) {
        db.deleteById(id)
    }

    fun updateDepartment(department: DepartmentEntityDAO) {
        if(db.existsById(department.id)) {
            db.save(department)
        }
    }
}