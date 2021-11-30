package com.epcc.politech_manager.subject

data class SubjectsBO(
        val id: String,
        val name: String,
        val semester: Int,
        val laboratory: Boolean,
        val conference: Boolean,
        val time_subject: Int,
        val id_classroom: String,
        val name_classroom: String,
        val pavilion:String,
        val capacity:Int,
        val id_department: String,
        val name_department: String,
        val id_degree: String,
        val name_degree: String,
        val num_semesters: Int
)