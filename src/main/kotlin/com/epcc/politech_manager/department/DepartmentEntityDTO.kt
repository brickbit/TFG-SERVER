package com.epcc.politech_manager.department

data class DepartmentEntityDTO(
        val name: String,
        val acronym: String,
        val id: Long = -1) {
    override fun toString(): String = """{
        "name": "$name",
        "acronym": "$acronym",
        "id": $id
    }""".trimIndent()
}