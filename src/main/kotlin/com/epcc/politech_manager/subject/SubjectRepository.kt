package com.epcc.politech_manager.subject

import org.springframework.data.jpa.repository.JpaRepository

interface SubjectRepository : JpaRepository<SubjectEntityDAO, String>