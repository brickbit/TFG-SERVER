package com.epcc.politech_manager.department

import com.epcc.politech_manager.classroom.toEntity
import org.springframework.stereotype.Service

@Service
class DepartmentService(val db: DepartmentRepository) {

    fun findDepartments(): List<Department> = db.findAll().map { it.toBO() }.toList()

    fun post(department: Department) {
        db.save(department.toEntity())
    }

    fun  getDepartment(id: Long): Department {
        return db.findById(id).orElse(null).toBO()
    }

    fun  deleteDepartment(id: Long) {
        db.deleteById(id)
    }

    fun updateDepartment(department: Department) {
        if(db.existsById(department.id)) {
            db.save(department.toEntity())
        }
    }
}