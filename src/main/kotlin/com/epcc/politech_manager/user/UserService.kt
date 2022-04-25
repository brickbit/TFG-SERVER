package com.epcc.politech_manager.user

import com.epcc.politech_manager.error.ExceptionUserModel
import com.epcc.politech_manager.error.UserException
import org.springframework.stereotype.Service
import java.time.Duration
import java.time.LocalDateTime
import java.util.*
import javax.transaction.Transactional

@Service
class UserService(val db: UserRepository) {

    fun post(user: UserEntityDAO?) {
        if(getUser(user!!.name) == null) {
            db.save(user)
        } else {
            throw UserException(ExceptionUserModel.INCORRECT_USER)
        }
    }

    fun getUser(email: String): UserEntityDAO? {
        return db.findAll().firstOrNull{ it.email == email }
    }

    fun getUserWithToken(token: String): UserEntityDAO? {
        return db.findAll().firstOrNull {
            it.token == token
        }
    }

    @Transactional
    fun deleteUser(id: String) {
        try {
            db.deleteById(id)
        } catch (e: Exception) {
            throw UserException(ExceptionUserModel.USER_NOT_EXIST)
        }
    }

    fun assignToken(user: UserEntityDAO, token: String?) {
        user.token = token
        db.save(user)
    }

    fun forgotPassword(email: String?): String? {
        var user: UserEntityDAO? = db.findByEmail(email) ?: return "Invalid email id."
        user!!.tokenForgotPassword = generateToken()
        user.tokenForgotPasswordCreationDate = LocalDateTime.now()
        user = db.save(user)
        return user.tokenForgotPassword
    }

    fun resetPassword(token: String?, password: String): String {
        val user = db.findByTokenForgotPassword(token) ?: return "Invalid token."
        val tokenCreationDate: LocalDateTime = user.tokenForgotPasswordCreationDate!!
        if (isTokenExpired(tokenCreationDate)) {
            return "Token expired."
        }
        db.delete(user)
        user.password = password
        user.tokenForgotPassword = null
        user.tokenForgotPasswordCreationDate = null
        db.save(user)
        return "Your password successfully updated."
    }

    fun resetPasswordWithoutToken(email: String, password: String): String? {
        val user = db.findByEmail(email) ?: return "Invalid token."
        db.delete(user)
        user.password = password
        db.save(user)
        return "Your password successfully updated."
    }

    private fun generateToken(): String {
        val token = StringBuilder()
        return token.append(UUID.randomUUID().toString())
                .append(UUID.randomUUID().toString()).toString()
    }

    private fun isTokenExpired(tokenCreationDate: LocalDateTime): Boolean {
        val now: LocalDateTime = LocalDateTime.now()
        val diff: Duration = Duration.between(tokenCreationDate, now)
        return diff.toMinutes() >= EXPIRE_TOKEN_AFTER_MINUTES
    }

    companion object {
        private const val EXPIRE_TOKEN_AFTER_MINUTES: Long = 30
    }

}