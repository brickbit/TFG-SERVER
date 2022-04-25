package com.epcc.politech_manager.user

import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<UserEntityDAO, String> {
    fun findByEmail(email: String?): UserEntityDAO?

    fun findByTokenForgotPassword(tokenForgotPassword: String?): UserEntityDAO?
}