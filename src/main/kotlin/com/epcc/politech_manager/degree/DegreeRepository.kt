package com.epcc.politech_manager.degree

import org.springframework.data.jpa.repository.JpaRepository

interface DegreeRepository: JpaRepository<DegreeEntityDAO, String>