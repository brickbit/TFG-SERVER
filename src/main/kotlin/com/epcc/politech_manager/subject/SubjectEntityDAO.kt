package com.epcc.politech_manager.subject

import com.epcc.politech_manager.classroom.ClassroomEntityDAO
import com.epcc.politech_manager.degree.DegreeEntityDAO
import com.epcc.politech_manager.department.DepartmentEntityDAO
import com.epcc.politech_manager.teacher.TeacherEntityDAO
import com.epcc.politech_manager.user.UserEntityDAO
import org.hibernate.annotations.DynamicUpdate
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import javax.persistence.*

@Entity
@Table(name = "subject")
@DynamicUpdate
data class SubjectEntityDAO(
        @Column
        val name: String,
        @Column
        val acronym: String,
        @Column
        val classGroup: String,
        @Column
        val seminary: Boolean,
        @Column
        val laboratory: Boolean,
        @Column
        val english: Boolean,
        @Column
        val time: Int,
        @Column
        val semester: Int,
        @OnDelete(action = OnDeleteAction.CASCADE)
        @JoinColumn(name = "classroom_id")
        @OneToOne(cascade = [CascadeType.PERSIST, CascadeType.MERGE,CascadeType.REFRESH])
        val classroom: ClassroomEntityDAO,
        @OnDelete(action = OnDeleteAction.CASCADE)
        @JoinColumn(name = "department_id")
        @OneToOne(cascade = [CascadeType.PERSIST, CascadeType.MERGE,CascadeType.REFRESH])
        val department: DepartmentEntityDAO,
        @OnDelete(action = OnDeleteAction.CASCADE)
        @JoinColumn(name = "teacher_id")
        @OneToOne(cascade = [CascadeType.PERSIST, CascadeType.MERGE,CascadeType.REFRESH])
        val teacher: TeacherEntityDAO?,
        @OnDelete(action = OnDeleteAction.CASCADE)
        @JoinColumn(name = "degree_id")
        @OneToOne(cascade = [CascadeType.PERSIST, CascadeType.MERGE,CascadeType.REFRESH])
        val degree: DegreeEntityDAO,
        @Column
        val color: Int,
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name="subject_id")
        val id: Long,
        @OnDelete(action = OnDeleteAction.CASCADE)
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "uid")
        val userEntity: UserEntityDAO
)