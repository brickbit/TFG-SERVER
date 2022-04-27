package com.epcc.politech_manager.exam

import org.springframework.data.jpa.repository.JpaRepository

interface ExamRepository: JpaRepository<ExamEntityDAO, String>