package com.epcc.politech_manager.classroom

import org.springframework.data.repository.CrudRepository

interface ClassroomRepository: CrudRepository<ClassroomEntityDAO, String>