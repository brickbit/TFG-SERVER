package com.epcc.politech_manager.degree

import javax.persistence.*

@Entity
@Table(name = "degrees")
data class Degree(val name: String,
                  val num_semesters:Int,
                  @javax.persistence.Id @GeneratedValue(strategy = GenerationType.AUTO)
                  @Column(name = "degree_id")
                   val id: Long = -1) {
    private constructor() : this("",0)
}