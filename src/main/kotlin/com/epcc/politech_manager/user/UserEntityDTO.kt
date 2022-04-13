package com.epcc.politech_manager.user

import org.hibernate.annotations.DynamicUpdate
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "userDTO")
@DynamicUpdate
data class UserEntityDTO(
        @Column
        val name: String,
        @Column(unique = true)
        val email: String,
        @Column
        var password: String,
        @Column
        var token: String?,
        var tokenForgotPassword: String?,
        @Column(columnDefinition = "TIMESTAMP")
        var tokenForgotPasswordCreationDate: LocalDateTime?,
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name="user_id")
        val id: Long = -1
)