package com.epcc.politech_manager.user
import com.epcc.politech_manager.error.LoginException
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.AuthorityUtils
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import java.util.*
import java.util.stream.Collectors

@RestController
class UserController(val service: UserService) {

    @PostMapping("/user/register")
    fun signIn(@RequestParam("user") username: String, @RequestParam("password") pwd: String, @RequestParam("repeatPassword") repeatPwd: String) {
        val validUser = username.contains("@unex.es")
        val samePwd = pwd == repeatPwd
        val regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~${'$'}^+=<>]).{8,20}${'$'}".toRegex()
        val correctPassword = regex.containsMatchIn(username)

        if(validUser && samePwd && correctPassword) {
            service.post(UserEntity(username,pwd))
        } else if (!validUser){
            throw LoginException(100)
        } else if (!samePwd){
            throw LoginException(200)
        } else if (!correctPassword){
            throw LoginException(300)
        }
    }

    @PostMapping("/user/login")
    fun login(@RequestParam("user") username: String, @RequestParam("password") pwd: String?): User? {
        if (service.getUser(username) != null) {
            val token = getJWTToken(username)
            return User(username, pwd, token)
        } else {
            throw LoginException(100)
        }
    }

    @PostMapping("/update")
    fun updatePassword(@RequestParam("user") username: String, @RequestParam("oldPassword") oldPwd: String?, @RequestParam("newPassword") newPwd: String?) {
        if (oldPwd != newPwd) {
            if (service.getUser(username) != null) {
                service.updatePassword(service.getUser(username)!!)
            } else {
                throw LoginException(100)
            }
        } else {
            throw LoginException(400)
        }
    }

    @PostMapping("/delete")
    fun deleteUser(@RequestParam("user") username: String) {
        if (service.getUser(username) != null) {
            service.deleteUser(service.getUser(username)!!.id)
        } else {
            throw LoginException(100)
        }
    }

    @PostMapping("/recover")
    fun recoverPwdUser(@RequestParam("user") username: String) {
        if (service.getUser(username) != null) {
            service.deleteUser(service.getUser(username)!!.id)
        } else {
            throw LoginException(100)
        }
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