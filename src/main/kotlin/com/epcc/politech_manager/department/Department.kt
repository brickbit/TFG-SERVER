package com.epcc.politech_manager.department

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Department(
        val name: String,
        val acronym: String,
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long = -1) {
    private constructor() : this("","")
}