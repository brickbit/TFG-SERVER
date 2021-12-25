package com.epcc.politech_manager.degree

fun DegreeEntity.toBO() = Degree(
        name = this.name,
        num_semesters = this.num_semesters,
        year = this.year,
        id = this.id)

fun Degree.toEntity() = DegreeEntity(
        name = this.name,
        num_semesters = this.num_semesters,
        year = this.year,
        id = this.id)