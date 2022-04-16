package com.epcc.politech_manager.exam

import com.epcc.politech_manager.subject.SubjectEntityDTO
import org.hibernate.annotations.DynamicUpdate
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import javax.persistence.*

@Entity
@Table(name = "exam")
@DynamicUpdate
data class ExamEntityDTO(
        @OnDelete(action = OnDeleteAction.CASCADE)
        @JoinColumn(name = "subject_id")
        @OneToOne(cascade = [CascadeType.PERSIST, CascadeType.MERGE,CascadeType.REFRESH])
        val subject: SubjectEntityDTO,
        @Column
        val acronym: String,
        @Column
        val semester: Int,
        @Column
        val date: String,
        @Column
        val call: String,
        @Column
        val turn: String,
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "exam_id")
        val id: Long = -1)