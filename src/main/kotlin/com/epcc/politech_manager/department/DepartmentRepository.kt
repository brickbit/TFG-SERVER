package com.epcc.politech_manager.department

import org.springframework.data.jdbc.repository.query.Modifying
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param

interface DepartmentRepository: CrudRepository<Departments, String> {

    @Query("select * from departments")
    fun getAllDepartments(): List<Departments>

    @Query("Select * from departments where departments.id = :id")
    fun getDepartment(@Param("id") id: String): Departments

    @Modifying
    @Query("delete from departments where departments.id = :id")
    fun deleteDepartment(@Param("id") id: String)

    @Modifying
    @Query("update departments set departments.name = :name where departments.id = :id")
    fun updateDepartment(@Param("name") name: String, @Param("id") id: String)
}