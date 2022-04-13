package com.epcc.politech_manager.degree

import org.hibernate.annotations.DynamicUpdate
import javax.persistence.*

@Entity
@Table(name = "degreeDTO")
@DynamicUpdate
data class DegreeEntityDTO(
        @Column
        val name: String,
        @Column
        val num_semesters:Int,
        @Column
        val year: String,
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "degree_id")
        val id: Long = -1)
