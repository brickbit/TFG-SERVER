package com.epcc.politech_manager.department

data class DepartmentBO(
        val name: String,
        val acronym: String,
        val id: Long) {
    override fun toString(): String = """{
        "name": "$name",
        "acronym": "$acronym",
        "id": $id
    }""".trimIndent()
}