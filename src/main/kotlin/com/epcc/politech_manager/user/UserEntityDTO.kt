package com.epcc.politech_manager.user

import java.time.LocalDateTime

data class UserEntityDTO(
        val name: String,
        val email: String,
        var password: String,
        var token: String?,
        var tokenForgotPassword: String?,
        var tokenForgotPasswordCreationDate: LocalDateTime?,
        val id: Long = -1
)