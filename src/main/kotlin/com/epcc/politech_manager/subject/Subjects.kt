package com.epcc.politech_manager.subject

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("SUBJECTS")
data class Subjects(
        @Id val id: String?,
        val name: String,
        val semester: Int,
        val laboratory: Boolean,
        val conference: Boolean,
        val time_subject: Int,
        val id_classroom: String,
        val id_department: String,
        val id_degree: String
)