package com.epcc.politech_manager.schedule

import org.springframework.data.repository.CrudRepository

interface ScheduleRepository : CrudRepository<ScheduleDegree, Long>
