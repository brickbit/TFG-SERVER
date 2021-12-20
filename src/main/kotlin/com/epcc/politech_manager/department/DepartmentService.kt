package com.epcc.politech_manager.department

import org.springframework.stereotype.Service

@Service
class DepartmentService(val db: DepartmentRepository) {

    fun findDepartments(): List<Department> = db.findAll().toList()

    fun post(department: Department) {
        db.save(department)
    }

    fun  getDepartment(id: Long): Department {
        return db.findById(id).orElse(null)
    }

    fun  deleteDepartment(id: Long) {
        db.deleteById(id)
    }

    fun updateDepartment(department: Department) {
        db.save(department)
    }
}