package com.epcc.politech_manager.classroom

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Classroom(
        val name: String,
        val pavilion: Pavilion,
        val acronym: String,
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long = -1) {
    private constructor() : this("",Pavilion.CENTRAL,"")
}

