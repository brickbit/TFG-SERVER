package com.epcc.politech_manager.department

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("DEPARTMENTS")
data class Departments(@Id val id: String?, val name: String)