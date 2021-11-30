package com.epcc.politech_manager.schedule

import org.springframework.data.jdbc.repository.query.Modifying
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param

interface ScheduleRepository : CrudRepository<Schedules, String> {

    @Query("select schedules.id, schedules.start_hour, schedules.end_hour, " +
            "subjects.id as id_subject, subjects.name, subjects.semester, subjects.laboratory, subjects.conference, subjects.time_subject, " +
            "classrooms.id as id_classroom, classrooms.name as name_classroom, classrooms.pavilion, classrooms.capacity, " +
            "departments.id as id_department, departments.name as name_department, " +
            "degrees.id as id_degree, degrees.name as name_degree, degrees.num_semesters " +
            "from schedules " +
            "inner join subjects on schedules.id_subject = subjects.id " +
            "inner join classrooms on subjects.id_classroom = classrooms.id " +
            "inner join departments on subjects.id_department = departments.id " +
            "inner join degrees on subjects.id_degree = degrees.id ")
    fun getAllSchedules(): List<SchedulesBO>

    @Query("select schedules.id, schedules.start_hour, schedules.end_hour, " +
            "subjects.id as id_subject, subjects.name, subjects.semester, subjects.laboratory, subjects.conference, subjects.time_subject, " +
            "classrooms.id as id_classroom, classrooms.name as name_classroom, classrooms.pavilion, classrooms.capacity, " +
            "departments.id as id_department, departments.name as name_department, " +
            "degrees.id as id_degree, degrees.name as name_degree, degrees.num_semesters " +
            "from schedules " +
            "inner join subjects on schedules.id_subject = subjects.id " +
            "inner join classrooms on subjects.id_classroom = classrooms.id " +
            "inner join departments on subjects.id_department = departments.id " +
            "inner join degrees on subjects.id_degree = degrees.id " +
            "where schedules.id = :id")
    fun getSchedule(@Param("id") id: String): SchedulesBO

    @Modifying
    @Query("delete from schedules where schedules.id = :id")
    fun deleteSchedule(@Param("id") id: String)

    @Modifying
    @Query("update schedules " +
            "set schedules.start_hour = :start_hour, schedules.end_hour = :end_hour, schedules.id_subject = :id_subject " +
            "where schedules.id = :id")
    fun updateSchedules(
            @Param("start_hour") start_hour: String,
            @Param("end_hour") end_hour: String,
            @Param("id_subject") id_subject: String,
            @Param("id") id: String
    )
}