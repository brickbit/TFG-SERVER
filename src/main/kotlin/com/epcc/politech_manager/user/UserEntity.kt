package com.epcc.politech_manager.user

import org.hibernate.annotations.DynamicUpdate
import javax.persistence.*

@Entity
@Table(name = "user")
@DynamicUpdate
data class UserEntity(
        @Column
        val username: String,
        @Column
        val password: String,
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name="user_id")
        val id: Long = -1
)