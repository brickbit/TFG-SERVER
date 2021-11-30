package com.epcc.politech_manager.degree

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("DEGREES")
data class Degrees(@Id val id: String?, val name: String, val num_semesters:Int)
