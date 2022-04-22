package com.epcc.politech_manager.calendar

import org.springframework.data.repository.CrudRepository

interface CalendarRepository: CrudRepository<CalendarEntityDAO, String>