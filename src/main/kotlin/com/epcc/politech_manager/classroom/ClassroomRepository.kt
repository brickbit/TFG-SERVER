package com.epcc.politech_manager.classroom

import org.springframework.data.jpa.repository.JpaRepository

interface ClassroomRepository: JpaRepository<ClassroomEntityDAO, String>