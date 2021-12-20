package com.epcc.politech_manager.department

fun DepartmentEntity.toBO() = Department(
        name = this.name,
        acronym = this.acronym,
        id = this.id)

fun Department.toEntity() = DepartmentEntity(
        name = this.name,
        acronym = this.acronym,
        subjects = emptyList(),
        id = this.id)