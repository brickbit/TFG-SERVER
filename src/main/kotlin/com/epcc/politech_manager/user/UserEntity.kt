package com.epcc.politech_manager.user

import org.hibernate.annotations.DynamicUpdate
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "user")
@DynamicUpdate
data class UserEntity(
        @Column
        val name: String,
        @Column(unique = true)
        val email: String,
        @Column
        var password: String,
        var token: String?,
        @Column(columnDefinition = "TIMESTAMP")
        var tokenCreationDate: LocalDateTime?,
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name="user_id")
        val id: Long = -1
)