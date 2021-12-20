package com.epcc.politech_manager.degree

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType

@Entity
data class Degree(val name: String,
                  val num_semesters:Int,
                  @javax.persistence.Id @GeneratedValue(strategy = GenerationType.AUTO)
                   val id: Long = -1) {
    private constructor() : this("",0)
}