package com.epcc.politech_manager.classroom

fun ClassroomEntity.toBO() = Classroom(
        name = this.name,
        pavilion = this.pavilion,
        acronym = this.acronym,
        id = this.id)

fun Classroom.toEntity() = ClassroomEntity(
        name = this.name,
        pavilion = this.pavilion,
        acronym = this.acronym,
        subjects = emptyList(),
        id = this.id)