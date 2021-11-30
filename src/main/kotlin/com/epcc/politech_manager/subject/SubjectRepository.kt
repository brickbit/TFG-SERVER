package com.epcc.politech_manager.subject

import org.springframework.data.jdbc.repository.query.Modifying
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param

interface SubjectRepository : CrudRepository<Subjects, String> {

    @Query("select subjects.id, subjects.name, subjects.semester, subjects.laboratory, subjects.conference, subjects.time_subject, " +
            "classrooms.id as id_classroom, classrooms.name as name_classroom, classrooms.pavilion, classrooms.capacity, " +
            "departments.id as id_department, departments.name as name_department, " +
            "degrees.id as id_degree, degrees.name as name_degree, degrees.num_semesters " +
            "from subjects " +
            "inner join classrooms on subjects.id_classroom = classrooms.id " +
            "inner join departments on subjects.id_department = departments.id " +
            "inner join degrees on subjects.id_degree = degrees.id ")
    fun getAllSubjects(): List<SubjectsBO>

    @Query("select subjects.id, subjects.name, subjects.semester, subjects.laboratory, subjects.conference, subjects.time_subject, " +
            "classrooms.id as id_classroom, classrooms.name as name_classroom, classrooms.pavilion, classrooms.capacity, " +
            "departments.id as id_department, departments.name as name_department, " +
            "degrees.id as id_degree, degrees.name as name_degree, degrees.num_semesters " +
            "from subjects " +
            "inner join classrooms on subjects.id_classroom = classrooms.id " +
            "inner join departments on subjects.id_department = departments.id " +
            "inner join degrees on subjects.id_degree = degrees.id " +
            "where subjects.id = :id")
    fun getSubject(@Param("id") id: String): SubjectsBO

    @Modifying
    @Query("delete from subjects where subjects.id = :id")
    fun deleteSubject(@Param("id") id: String)

    @Modifying
    @Query("update subjects " +
            "set subjects.name = :name, subjects.semester = :semester, subjects.laboratory = :laboratory, " +
            "subjects.conference = :conference, subjects.time_subject = :time_subject, " +
            "subjects.id_classroom = :id_classroom, " +
            "subjects.id_department = :id_department, " +
            "subjects.id_degree = :id_degree " +
            "where subjects.id = :id")
    fun updateSubject(
            @Param("name") name: String,
            @Param("semester") semester: Int,
            @Param("laboratory") laboratory: Boolean,
            @Param("conference") conference: Boolean,
            @Param("time_subject") time_subject: Int,
            @Param("id_classroom") id_classroom: String,
            @Param("id_department") id_department: String,
            @Param("id_degree") id_degree: String,
            @Param("id") id: String
    )
}