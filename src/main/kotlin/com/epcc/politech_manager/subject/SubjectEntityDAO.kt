package com.epcc.politech_manager.subject

import com.epcc.politech_manager.classroom.ClassroomEntityDTO
import com.epcc.politech_manager.degree.DegreeEntityDAO
import com.epcc.politech_manager.degree.DegreeEntityDTO
import com.epcc.politech_manager.department.DepartmentEntityDTO
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
        @Column
        val days: String,
        @Column
        val hours: String,
        @Column
        val turns: String,
        @OnDelete(action = OnDeleteAction.CASCADE)
        @JoinColumn(name = "classroom_id")
        @OneToOne(cascade = [CascadeType.PERSIST, CascadeType.MERGE,CascadeType.REFRESH])
        val classroom: ClassroomEntityDTO,
        @OnDelete(action = OnDeleteAction.CASCADE)
        @JoinColumn(name = "department_id")
        @OneToOne(cascade = [CascadeType.PERSIST, CascadeType.MERGE,CascadeType.REFRESH])
        val department: DepartmentEntityDTO,
        @OnDelete(action = OnDeleteAction.CASCADE)
        @JoinColumn(name = "degree_id")
        @OneToOne(cascade = [CascadeType.PERSIST, CascadeType.MERGE,CascadeType.REFRESH])
        val degree: DegreeEntityDAO,
        @Column
        val color: Int,
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name="subject_id")
        val id: Long = -1,
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id")
        val user: UserEntityDAO
)