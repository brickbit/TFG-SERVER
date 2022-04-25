package com.epcc.politech_manager.classroom

data class ClassroomBO(
        val name: String,
        val pavilion: Pavilion,
        val acronym: String,
        val id: String) {
    override fun toString(): String = """{
            "name": "$name",
            "pavilion": "$pavilion",
            "acronym": "$acronym",
            "id": $id
        }""".trimMargin()
}

