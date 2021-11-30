package com.epcc.politech_manager.schedule

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("SCHEDULES")
data class Schedules(@Id val id: String?, val start_hour: String, val end_hour: String, val id_subject: String)