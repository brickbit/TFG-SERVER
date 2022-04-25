package com.epcc.politech_manager.user

import com.epcc.politech_manager.calendar.CalendarEntityDAO
import com.epcc.politech_manager.classroom.ClassroomEntityDAO
import com.epcc.politech_manager.degree.DegreeEntityDAO
import com.epcc.politech_manager.department.DepartmentEntityDAO
import com.epcc.politech_manager.exam.ExamEntityDAO
import com.epcc.politech_manager.schedule.ScheduleEntityDAO
import com.epcc.politech_manager.subject.SubjectEntityDAO
import org.hibernate.annotations.DynamicUpdate
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "user")
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
        @Column(name="user_id")
        val id: String,
        @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
        var degrees: MutableList<DegreeEntityDAO>,
        @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
        var department: MutableList<DepartmentEntityDAO>,
        @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
        var classroom: MutableList<ClassroomEntityDAO>,
        @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
        var subject: MutableList<SubjectEntityDAO>,
        @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
        var schedule: MutableList<ScheduleEntityDAO>,
        @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
        var exam: MutableList<ExamEntityDAO>,
        @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
        var calendar: MutableList<CalendarEntityDAO>
)