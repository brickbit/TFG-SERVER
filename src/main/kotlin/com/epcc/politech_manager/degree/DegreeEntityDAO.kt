package com.epcc.politech_manager.degree

import com.epcc.politech_manager.user.UserEntityDAO
import org.hibernate.annotations.DynamicUpdate
import javax.persistence.*

@Entity
@Table(name = "degreeDAO")
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
        val id: String,
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id")
        val user: UserEntityDAO
)


