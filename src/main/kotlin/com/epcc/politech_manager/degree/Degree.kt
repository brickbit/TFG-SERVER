package com.epcc.politech_manager.degree

data class Degree(val name: String,
                  val num_semesters:Int,
                  val year: String,
                  val id: Long = -1)