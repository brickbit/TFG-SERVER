package com.epcc.politech_manager.degree

import com.epcc.politech_manager.user.UserEntityDAO
import org.hibernate.annotations.DynamicUpdate
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import javax.persistence.*

@Entity
@Table(name = "degree")
@DynamicUpdate
data class DegreeEntityDAO(
        @Column
        val name: String,
        @Column
        val num_semesters:Int,
        @Column
        val year: String,
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "degree_id")
        val id: Long,
        @OnDelete(action = OnDeleteAction.CASCADE)
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "uid")
        val userEntity: UserEntityDAO
)


