package com.epcc.politech_manager.classroom

import org.hibernate.annotations.DynamicUpdate
import javax.persistence.*

@Entity
@Table(name = "classroom")
@DynamicUpdate
data class ClassroomEntityDTO(
        @Column
        val name: String,
        @Column
        val pavilion: Pavilion,
        @Column
        val acronym: String,
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name="classroom_id")
        val id: Long = -1)