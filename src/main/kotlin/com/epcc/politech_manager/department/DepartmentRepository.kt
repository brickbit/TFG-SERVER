package com.epcc.politech_manager.department

import org.springframework.data.repository.CrudRepository

interface DepartmentRepository: CrudRepository<Department, Long> {
}