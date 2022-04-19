package com.epcc.politech_manager.degree

import java.util.*

data class DegreeEntityDTO(
        val name: String,
        val num_semesters:Int,
        val year: String,
        val id: String = UUID.randomUUID().toString())
