package com.epcc.politech_manager.teacher

import com.epcc.politech_manager.department.DepartmentEntityDAO
import com.epcc.politech_manager.user.UserEntityDAO
import org.hibernate.annotations.DynamicUpdate
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import javax.persistence.*


@Entity
@Table(name = "teacher")
@DynamicUpdate
data class TeacherEntityDAO(
        @Column
        val name: String,
        @OnDelete(action = OnDeleteAction.CASCADE)
        @JoinColumn(name = "department_id")
        @OneToOne(cascade = [CascadeType.PERSIST, CascadeType.REFRESH])
        val department: DepartmentEntityDAO,
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name="teacher_id")
        val id: Long,
        @OnDelete(action = OnDeleteAction.CASCADE)
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "uid")
        val userEntity: UserEntityDAO
)