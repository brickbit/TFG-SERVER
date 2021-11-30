package com.epcc.politech_manager.degree

import org.springframework.data.jdbc.repository.query.Modifying
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param

interface DegreeRepository: CrudRepository<Degrees, String> {
    @Query("select * from degrees")
    fun getAllDegrees(): List<Degrees>

    @Query("Select * from degrees where degrees.id = :id")
    fun getDegree(@Param("id") id: String): Degrees

    @Modifying
    @Query("delete from degrees where degrees.id = :id")
    fun deleteDegree(@Param("id") id: String)

    @Modifying
    @Query("update degrees " +
            "set degrees.name = :name, degrees.num_semesters = :num_semesters " +
            "where degrees.id = :id")
    fun updateDegrees(
            @Param("name") name: String,
            @Param("num_semesters") capacity: Int,
            @Param("id") id: String)
}