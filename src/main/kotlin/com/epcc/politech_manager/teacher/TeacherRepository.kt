package com.epcc.politech_manager.teacher

import org.springframework.data.jdbc.repository.query.Modifying
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param

interface TeacherRepository : CrudRepository<Teachers, String> {

    @Query("select teachers.id, teachers.name, departments.id as id_department, departments.name as name_department " +
            "from teachers " +
            "inner join departments " +
            "where teachers.id_department = departments.id")
    fun getAllTeachers(): List<TeachersBO>

    @Query("Select teachers.id, teachers.name, departments.id as id_department, departments.name as name_department " +
            "from teachers " +
            "inner join departments " +
            "where teachers.id_department = departments.id and teachers.id = :id")
    fun getTeacher(@Param("id") id: String): TeachersBO

    @Modifying
    @Query("delete from teachers where teachers.id = :id")
    fun deleteTeacher(@Param("id") id: String)

    @Modifying
    @Query("update teachers " +
            "set teachers.name = :name, teachers.id_department = :id_department " +
            "where teachers.id = :id")
    fun updateTeacher(
            @Param("name") name: String,
            @Param("id_department") id_department: String,
            @Param("id") id: String
    )
}

