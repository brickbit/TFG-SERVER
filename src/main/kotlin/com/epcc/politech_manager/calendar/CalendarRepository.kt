package com.epcc.politech_manager.calendar

import org.springframework.data.jpa.repository.JpaRepository

interface CalendarRepository: JpaRepository<CalendarEntityDAO, String>