package com.epcc.politech_manager.schedule

import com.epcc.politech_manager.subject.SubjectEntity
import org.hibernate.annotations.DynamicUpdate
import javax.persistence.*

@Entity
@Table(name = "schedule")
@DynamicUpdate
data class ScheduleEntity(
        @ElementCollection
        val subjects: List<SubjectEntity>,
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
        val id: Long = -1)