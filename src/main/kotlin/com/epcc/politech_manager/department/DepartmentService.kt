package com.epcc.politech_manager.department

import org.springframework.stereotype.Service

@Service
class DepartmentService(val db: DepartmentRepository) {

    fun findDepartments(): List<DepartmentEntity> = db.findAll().toList()

    fun post(department: DepartmentEntity) {
        db.save(department)
    }

    fun  getDepartment(id: Long): DepartmentEntity {
        return db.findById(id).orElse(null)
    }

    fun  deleteDepartment(id: Long) {
        db.deleteById(id)
    }

    fun updateDepartment(department: DepartmentEntity) {
        if(db.existsById(department.id)) {
            db.save(department)
        }
    }
}