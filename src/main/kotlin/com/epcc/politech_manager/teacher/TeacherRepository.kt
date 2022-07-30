package com.epcc.politech_manager.teacher

import org.springframework.data.repository.CrudRepository

interface TeacherRepository : CrudRepository<TeacherEntityDAO, Long>