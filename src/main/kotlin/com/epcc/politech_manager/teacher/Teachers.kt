package com.epcc.politech_manager.teacher

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("TEACHERS")
data class Teachers(@Id val id: String?, val name: String, val id_department: String)