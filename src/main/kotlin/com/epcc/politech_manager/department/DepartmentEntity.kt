package com.epcc.politech_manager.department

import org.hibernate.annotations.DynamicUpdate
import javax.persistence.*

@Entity
@Table(name = "department")
@DynamicUpdate
data class DepartmentEntity(
        @Column
        val name: String,
        @Column
        val acronym: String,
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "department_id")
        val id: Long = -1)