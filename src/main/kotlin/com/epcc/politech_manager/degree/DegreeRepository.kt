package com.epcc.politech_manager.degree

import org.springframework.data.jdbc.repository.query.Modifying
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param

interface DegreeRepository: CrudRepository<Degree, Long>