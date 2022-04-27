package com.epcc.politech_manager.department

import org.springframework.data.jpa.repository.JpaRepository

interface DepartmentRepository: JpaRepository<DepartmentEntityDAO, Long>