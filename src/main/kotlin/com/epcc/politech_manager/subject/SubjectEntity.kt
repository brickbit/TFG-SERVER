package com.epcc.politech_manager.subject

import com.epcc.politech_manager.classroom.ClassroomEntity
import com.epcc.politech_manager.department.DepartmentEntity
import org.hibernate.annotations.DynamicUpdate
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
        @ManyToOne
        val classroom: ClassroomEntity,
        @ManyToOne
        val department: DepartmentEntity,
        @Column
        val color: Int,
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name="subject_id")
        val id: Long = -1)