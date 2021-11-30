package com.epcc.politech_manager.schedule

data class SchedulesBO(
        val id: String,
        val start_hour: String,
        val end_hour: String,
        val id_subject: String,
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
        val num_semesters: Int)