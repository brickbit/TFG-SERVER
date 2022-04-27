package com.epcc.politech_manager.schedule

import org.springframework.data.jpa.repository.JpaRepository

interface ScheduleRepository : JpaRepository<ScheduleEntityDAO, Long>