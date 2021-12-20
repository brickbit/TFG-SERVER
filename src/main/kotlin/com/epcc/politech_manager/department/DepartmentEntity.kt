package com.epcc.politech_manager.department

import com.epcc.politech_manager.subject.SubjectEntity
import org.hibernate.annotations.DynamicUpdate
import javax.persistence.*

@Entity
@Table(name = "department")
@DynamicUpdate
data class DepartmentEntity(
        @Column
        val name: String,
        @Column
        val acronym: String,
        @Column
        @OneToMany(cascade = [CascadeType.ALL],mappedBy = "department")
        val subjects: List<SubjectEntity>,
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "department_id")
        val id: Long = -1)