package com.epcc.politech_manager.degree

data class DegreeEntityDTO(
        val name: String,
        val num_semesters:Int,
        val year: String,
        val id: Long = -1) {
    override fun toString(): String = """{
           "name": "$name",
           "num_semesters": $num_semesters,
           "year": "$year",
           "id": $id
    }""".trimMargin()
}
