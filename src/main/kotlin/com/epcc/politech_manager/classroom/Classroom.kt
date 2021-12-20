package com.epcc.politech_manager.classroom

data class Classroom(
        val name: String,
        val pavilion: Pavilion,
        val acronym: String,
        val id: Long = -1)

