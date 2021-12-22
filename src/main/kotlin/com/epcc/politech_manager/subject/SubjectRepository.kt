package com.epcc.politech_manager.subject

import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param

interface SubjectRepository : CrudRepository<SubjectEntity, Long>