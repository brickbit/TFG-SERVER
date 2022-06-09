package com.epcc.politech_manager.calendar

import com.epcc.politech_manager.user.UserEntityDAO
import org.hibernate.annotations.DynamicUpdate
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import javax.persistence.*

@Entity
@Table(name = "calendar")
@DynamicUpdate
data class CalendarEntityDAO(
        @Column(columnDefinition="Text")
        val exams: String,
        @Column
        val degree: String,
        @Column
        val year: String,
        @Column
        val startDate: String,
        @Column
        val endDate: String,
        @Column
        val call: String,
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "calendar_id")
        val id: Long,
        @OnDelete(action = OnDeleteAction.CASCADE)
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "uid")
        val userEntity: UserEntityDAO
)