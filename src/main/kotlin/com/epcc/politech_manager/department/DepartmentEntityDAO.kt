package com.epcc.politech_manager.department

import com.epcc.politech_manager.user.UserEntityDAO
import org.hibernate.annotations.DynamicUpdate
import javax.persistence.*

@Entity
@Table(name = "department")
@DynamicUpdate
data class DepartmentEntityDAO(
        @Column
        val name: String,
        @Column
        val acronym: String,
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "department_id")
        val id: Long,
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "uid")
        val userEntity: UserEntityDAO
        )