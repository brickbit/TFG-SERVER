package com.epcc.politech_manager.user

import com.epcc.politech_manager.calendar.CalendarEntityDAO
import com.epcc.politech_manager.classroom.ClassroomEntityDAO
import com.epcc.politech_manager.degree.DegreeEntityDAO
import com.epcc.politech_manager.department.DepartmentEntityDAO
import com.epcc.politech_manager.exam.ExamEntityDAO
import com.epcc.politech_manager.schedule.ScheduleEntityDAO
import com.epcc.politech_manager.subject.SubjectEntityDAO
import org.hibernate.annotations.DynamicUpdate
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "userEntity")
@DynamicUpdate
data class UserEntityDAO(
        @Column
        val name: String,
        @Column(unique = true)
        val email: String,
        @Column
        var password: String,
        @Column
        var token: String?,
        var tokenForgotPassword: String?,
        @Column(columnDefinition = "TIMESTAMP")
        var tokenForgotPasswordCreationDate: LocalDateTime?,
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name="uid")
        val id: Long,
        @OneToMany(fetch = FetchType.LAZY, mappedBy = "userEntity")
        var degrees: MutableList<DegreeEntityDAO>,
        @OneToMany(fetch = FetchType.LAZY, mappedBy = "userEntity")
        var department: MutableList<DepartmentEntityDAO>,
        @OneToMany(fetch = FetchType.LAZY, mappedBy = "userEntity")
        var classroom: MutableList<ClassroomEntityDAO>,
        @OneToMany(fetch = FetchType.LAZY, mappedBy = "userEntity")
        var subject: MutableList<SubjectEntityDAO>,
        @OneToMany(fetch = FetchType.LAZY, mappedBy = "userEntity")
        var schedule: MutableList<ScheduleEntityDAO>,
        @OneToMany(fetch = FetchType.LAZY, mappedBy = "userEntity")
        var exam: MutableList<ExamEntityDAO>,
        @OneToMany(fetch = FetchType.LAZY, mappedBy = "userEntity")
        var calendar: MutableList<CalendarEntityDAO>
)