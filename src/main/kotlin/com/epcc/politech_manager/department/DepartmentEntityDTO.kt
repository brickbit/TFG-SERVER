package com.epcc.politech_manager.department

import java.util.*

data class DepartmentEntityDTO(
        val name: String,
        val acronym: String,
        val id: String = UUID.randomUUID().toString()) {
    override fun toString(): String = """{
        "name": "$name",
        "acronym": "$acronym",
        "id": "$id"
    }""".trimIndent()
}