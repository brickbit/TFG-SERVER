package com.epcc.politech_manager.user

import org.hibernate.annotations.DynamicUpdate
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

data class UserEntityDTO(
        val name: String,
        val email: String,
        var password: String,
        var token: String?,
        var tokenForgotPassword: String?,
        var tokenForgotPasswordCreationDate: LocalDateTime?,
        val id: String = UUID.randomUUID().toString()
)