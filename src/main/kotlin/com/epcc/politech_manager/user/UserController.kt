package com.epcc.politech_manager.user

import com.epcc.politech_manager.error.ExceptionUserModel
import com.epcc.politech_manager.error.UserException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.*
import java.util.stream.Collectors


@RestController
class UserController(val service: UserService) {

    @PostMapping("/user/register")
    fun signIn(@RequestParam("user") name: String,
               @RequestParam("email") email: String,
               @RequestParam("password") pwd: String,
               @RequestParam("repeatPassword") repeatPwd: String)
    : UserResponseOk {
        val validUser = email.contains("@unex.es") && service.getUser(email) == null
        val samePwd = pwd == repeatPwd
        val regex = """^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&_()–[{}]:;',?/*~${'$'}^+=<>]).{8,20}${'$'}""".toRegex()
        val correctPassword = regex.containsMatchIn(pwd)

        if (validUser && samePwd && correctPassword) {
            service.post(UserEntity(name, email, pwd, null, null))
            return UserResponseOk(200,"Registration completed successfully")
        } else if (!validUser) {
            throw UserException(ExceptionUserModel.WRONG_USER)
        } else if (!samePwd) {
            throw UserException(ExceptionUserModel.PASSWORD_NOT_MATCH)
        } else if (!correctPassword) {
            throw UserException(ExceptionUserModel.INCORRECT_PASSWORD)
        } else {
            throw  UserException(ExceptionUserModel.UNKNOWN_ERROR)
        }

    }

    @PostMapping("/user/login")
    fun login(@RequestParam("email") email: String,
              @RequestParam("password") pwd: String?)
    : UserTokenResponseOk {
        val user = service.getUser(email)
        if (user != null && user.password == pwd) {
            return UserTokenResponseOk(200, "Login successful", getJWTToken(email))
        } else {
            throw UserException(ExceptionUserModel.WRONG_USER)
        }
    }

    @PostMapping("/update")
    fun updatePassword(@RequestParam("email") email: String,
                       @RequestParam("oldPassword") oldPwd: String,
                       @RequestParam("newPassword") newPwd: String)
    : UserResponseOk {
        if (oldPwd != newPwd) {
            if (service.getUser(email) != null) {
                service.resetPasswordWithoutToken(email, newPwd)
                return UserResponseOk(200, "Password updated successfully")
            } else {
                throw UserException(ExceptionUserModel.WRONG_USER)
            }
        } else {
            throw UserException(ExceptionUserModel.SAME_PASSWORD_THAN_OLDER)
        }
    }

    @PostMapping("/delete")
    fun deleteUser(@RequestParam("user") username: String): UserResponseOk {
        if (service.getUser(username) != null) {
            service.deleteUser(service.getUser(username)!!.id)
        } else {
            throw UserException(ExceptionUserModel.WRONG_USER)
        }
        return UserResponseOk(200, "User deleted successfully")
    }

    @PostMapping("user/forgot-password")
    fun forgotPassword(@RequestParam email: String?): UserResponseOk {
        var response: String = service.forgotPassword(email)!!
        var code = 400
        if (!response.startsWith("Invalid")) {
            response = "http://localhost:6000/user/reset-password?token=$response"
            code = 200
        }
        return UserResponseOk(code,response)
    }

    @PutMapping("user/reset-password")
    fun resetPassword(@RequestParam token: String,
                      @RequestParam password: String): UserResponseOk {
        val message = service.resetPassword(token, password)
        val code = when(message) {
            "Your password successfully updated." -> 200
            else -> 400
        }
        return UserResponseOk(code, message)
    }

    private fun getJWTToken(username: String): String {
        val secretKey = "mySecretKey"
        val grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER")
        val token = Jwts
                .builder()
                .setId("softtekJWT")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map { obj: GrantedAuthority -> obj.authority }
                                .collect(Collectors.toList()))
                .setIssuedAt(Date(System.currentTimeMillis()))
                .setExpiration(Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.toByteArray()).compact()
        return "Bearer $token"
    }
}