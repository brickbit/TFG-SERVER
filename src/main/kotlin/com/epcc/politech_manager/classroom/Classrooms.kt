package com.epcc.politech_manager.classroom

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("CLASSROOMS")
data class Classrooms(@Id val id: String?, val name: String, val pavilion:String, val capacity:Int)
