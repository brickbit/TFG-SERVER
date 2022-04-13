package com.epcc.politech_manager.subject

import com.epcc.politech_manager.classroom.ClassroomEntity
import com.epcc.politech_manager.degree.DegreeEntityDTO
import com.epcc.politech_manager.department.DepartmentEntity
import org.hibernate.annotations.DynamicUpdate
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import javax.persistence.*

@Entity
@Table(name = "subject")
@DynamicUpdate
data class SubjectEntity(
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
        val classroom: ClassroomEntity,
        @OnDelete(action = OnDeleteAction.CASCADE)
        @JoinColumn(name = "department_id")
        @OneToOne(cascade = [CascadeType.PERSIST, CascadeType.MERGE,CascadeType.REFRESH])
        val department: DepartmentEntity,
        @OnDelete(action = OnDeleteAction.CASCADE)
        @JoinColumn(name = "degree_id")
        @OneToOne(cascade = [CascadeType.PERSIST, CascadeType.MERGE,CascadeType.REFRESH])
        val degree: DegreeEntityDTO,
        @Column
        val color: Int,
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name="subject_id")
        val id: Long = -1
)