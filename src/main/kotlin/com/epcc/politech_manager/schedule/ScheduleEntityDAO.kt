package com.epcc.politech_manager.schedule

import com.epcc.politech_manager.user.UserEntityDAO
import org.hibernate.annotations.DynamicUpdate
import javax.persistence.*

@Entity
@Table(name = "schedule")
@DynamicUpdate
data class ScheduleEntityDAO(
        @Column(columnDefinition="Text")
        val subjects: String,
        @Column
        val scheduleType: Int,
        @Column
        val fileType: Int,
        @Column
        val degree: String,
        @Column
        val year: String,
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name="schedule_id")
        val id: String,
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id")
        val user: UserEntityDAO
)