package com.epcc.politech_manager.classroom

import com.epcc.politech_manager.user.UserEntityDAO
import org.hibernate.annotations.Cascade
import org.hibernate.annotations.CascadeType
import org.hibernate.annotations.DynamicUpdate
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import javax.persistence.*

@Entity
@Table(name = "classroom")
@DynamicUpdate
data class ClassroomEntityDAO(
        @Column
        val name: String,
        @Column
        val pavilion: Pavilion,
        @Column
        val acronym: String,
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name="classroom_id")
        val id: Long,
        @OnDelete(action = OnDeleteAction.CASCADE)
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "uid")
        val userEntity: UserEntityDAO
)
