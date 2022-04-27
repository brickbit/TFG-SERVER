package com.epcc.politech_manager.exam

import com.epcc.politech_manager.subject.SubjectEntityDAO
import com.epcc.politech_manager.user.UserEntityDAO
import org.hibernate.annotations.DynamicUpdate
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import javax.persistence.*

@Entity
@Table(name = "exam")
@DynamicUpdate
data class ExamEntityDAO(
        @OnDelete(action = OnDeleteAction.CASCADE)
        @JoinColumn(name = "subject_id")
        @OneToOne(cascade = [CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH])
        val subject: SubjectEntityDAO,
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
        val id: String,
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "uid")
        val userEntity: UserEntityDAO)