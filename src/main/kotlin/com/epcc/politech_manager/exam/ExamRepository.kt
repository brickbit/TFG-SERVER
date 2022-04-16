package com.epcc.politech_manager.exam

import org.springframework.data.repository.CrudRepository

interface ExamRepository: CrudRepository<ExamEntityDAO, Long>