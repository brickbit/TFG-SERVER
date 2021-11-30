package com.epcc.politech_manager.classroom

import org.springframework.data.jdbc.repository.query.Modifying
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param

interface ClassroomRepository: CrudRepository<Classrooms, String> {
    @Query("select * from classrooms")
    fun getAllClassrooms(): List<Classrooms>

    @Query("Select * from classrooms where classrooms.id = :id")
    fun getClassrooms(@Param("id") id: String): Classrooms

    @Modifying
    @Query("delete from classrooms where classrooms.id = :id")
    fun deleteClassroom(@Param("id") id: String)

    @Modifying
    @Query("update classrooms " +
            "set classrooms.name = :name, classrooms.pavilion = :pavilion, classrooms.capacity = :capacity " +
            "where classrooms.id = :id")
    fun updateClassrooms(
            @Param("name") name: String,
            @Param("pavilion") pavilion: String,
            @Param("capacity") capacity: Int,
            @Param("id") id: String)
}