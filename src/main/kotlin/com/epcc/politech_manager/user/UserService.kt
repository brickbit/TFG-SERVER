package com.epcc.politech_manager.user

import org.springframework.stereotype.Service
import java.time.Duration
import java.time.LocalDateTime
import java.util.*
import javax.transaction.Transactional

@Service
class UserService(val db: UserRepository) {

    fun post(user: UserEntity?) {
        if(getUser(user!!.name) == null) {
            db.save(user)
        }
    }

    fun getUser(id: Long): UserEntity? {
        return db.findById(id).orElse(null)
    }

    fun getUser(email: String): UserEntity? {
        return db.findAll().firstOrNull{ it.email == email }
    }

    @Transactional
    fun deleteUser(id: Long) {
        db.deleteById(id)
    }

    fun forgotPassword(email: String?): String? {
        var user: UserEntity? = db.findByEmail(email) ?: return "Invalid email id."
        user!!.token = generateToken()
        user.tokenCreationDate = LocalDateTime.now()
        user = db.save(user)
        return user.token
    }

    fun resetPassword(token: String?, password: String): String {
        val user = db.findByToken(token) ?: return "Invalid token."
        val tokenCreationDate: LocalDateTime = user.tokenCreationDate!!
        if (isTokenExpired(tokenCreationDate)) {
            return "Token expired."
        }
        db.delete(user)
        user.password = password
        user.token = null
        user.tokenCreationDate = null
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

    /**
     * Generate unique token. You may add multiple parameters to create a strong
     * token.
     *
     * @return unique token
     */
    private fun generateToken(): String {
        val token = StringBuilder()
        return token.append(UUID.randomUUID().toString())
                .append(UUID.randomUUID().toString()).toString()
    }

    /**
     * Check whether the created token expired or not.
     *
     * @param tokenCreationDate
     * @return true or false
     */
    private fun isTokenExpired(tokenCreationDate: LocalDateTime): Boolean {
        val now: LocalDateTime = LocalDateTime.now()
        val diff: Duration = Duration.between(tokenCreationDate, now)
        return diff.toMinutes() >= EXPIRE_TOKEN_AFTER_MINUTES
    }

    companion object {
        private val EXPIRE_TOKEN_AFTER_MINUTES: Long = 30
    }

}