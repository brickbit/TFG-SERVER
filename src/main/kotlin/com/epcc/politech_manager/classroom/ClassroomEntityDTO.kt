package com.epcc.politech_manager.classroom

import java.util.*

data class ClassroomEntityDTO(
        val name: String,
        val pavilion: Pavilion,
        val acronym: String,
        val id: String = UUID.randomUUID().toString())
