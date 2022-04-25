package com.epcc.politech_manager.degree

data class DegreeBO(val name: String,
                    val num_semesters:Int,
                    val year: String,
                    val id: String) {
    override fun toString(): String = """{
           "name": "$name",
           "num_semesters": $num_semesters,
           "year": "$year",
           "id": $id
    }""".trimMargin()
}