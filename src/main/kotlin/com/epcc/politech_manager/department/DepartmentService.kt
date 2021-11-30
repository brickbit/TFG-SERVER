package com.epcc.politech_manager.department

import org.springframework.stereotype.Service

@Service
class DepartmentService(val db: DepartmentRepository) {

    fun findDepartments(): List<Departments> = db.getAllDepartments()

    fun post(department: Departments) {
        db.save(department)
    }

    fun  getDepartment(id: String): Departments {
        return db.getDepartment(id)
    }

    fun  deleteDepartment(id: String) {
        db.deleteDepartment(id)
    }

    fun updateDepartment(name: String, id: String) {
        db.updateDepartment(name, id)
    }
}