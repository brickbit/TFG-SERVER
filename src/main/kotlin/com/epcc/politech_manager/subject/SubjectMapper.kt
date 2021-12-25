package com.epcc.politech_manager.subject

import com.epcc.politech_manager.classroom.ClassroomEntity
import com.epcc.politech_manager.classroom.toEntity
import com.epcc.politech_manager.degree.DegreeEntity
import com.epcc.politech_manager.degree.toEntity
import com.epcc.politech_manager.department.DepartmentEntity
import com.epcc.politech_manager.department.toEntity
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import javax.persistence.*


/*fun SubjectEntity.toBO() = Subject(
        name = this.name,
        acronym = this.acronym,
        group = this.classGroup,
        seminary = this.seminary,
        laboratory = this.laboratory,
        english = this.english,
        time = this.time,
        classroom = this.classroom.toBO(),
        color = this.color,
        id = this.id)*/

fun Subject.toEntity(days:String, hours: String, turns: String) = SubjectEntity(
        name = this.name,
        acronym = this.acronym,
        classGroup = this.group,
        seminary = this.seminary,
        laboratory = this.laboratory,
        english = this.english,
        time = this.time,
        semester = this.semester,
        days = days,
        hours = hours,
        turns = turns,
        classroom = this.classroom.toEntity(),
        department = this.department.toEntity(),
        degree = this.degree.toEntity(),
        color = this.color,
        id = this.id)
