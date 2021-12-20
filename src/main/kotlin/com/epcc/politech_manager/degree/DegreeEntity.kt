package com.epcc.politech_manager.degree

import org.hibernate.annotations.DynamicUpdate
import javax.persistence.*

@Entity
@Table(name = "degree")
@DynamicUpdate
data class DegreeEntity(
        @Column
        val name: String,
        @Column
        val num_semesters:Int,
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "degree_id")
        val id: Long = -1)
