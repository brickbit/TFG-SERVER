package com.epcc.politech_manager.user

import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class UserService(val db: UserRepository) {

    fun post(user: UserEntity?) {
        if(getUser(user!!.username) == null) {
            db.save(user)
        }
    }

    fun getUser(id: Long): UserEntity? {
        return db.findById(id).orElse(null)
    }

    fun getUser(username: String): UserEntity? {
        return db.findAll().firstOrNull{ it.username == username }
    }

    @Transactional
    fun deleteUser(id: Long) {
        db.deleteById(id)
    }

    fun updatePassword(user: UserEntity) {
        if(db.existsById(user.id)) {
            db.save(user)
        }
    }

    fun recoverPassword(user: UserEntity): String {
        return db.findById(user.id).orElse(null).password
    }


}