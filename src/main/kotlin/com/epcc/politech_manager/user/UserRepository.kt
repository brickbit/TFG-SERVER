package com.epcc.politech_manager.user

import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<UserEntityDAO, String> {
    fun findByEmail(email: String?): UserEntityDAO?

    fun findByTokenForgotPassword(tokenForgotPassword: String?): UserEntityDAO?
}