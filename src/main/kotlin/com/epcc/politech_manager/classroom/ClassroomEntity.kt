package com.epcc.politech_manager.classroom

import com.epcc.politech_manager.subject.SubjectEntity
import org.hibernate.annotations.DynamicUpdate
import javax.persistence.*

@Entity
@Table(name = "classroom")
@DynamicUpdate
data class ClassroomEntity(
        @Column
        val name: String,
        @Column
        val pavilion: Pavilion,
        @Column
        val acronym: String,
        @Column
        @OneToMany(cascade = [CascadeType.PERSIST],mappedBy = "classroom")
        val subjects: List<SubjectEntity>,
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name="classroom_id")
        val id: Long = -1)
