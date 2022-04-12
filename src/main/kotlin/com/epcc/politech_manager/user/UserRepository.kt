package com.epcc.politech_manager.user

import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<UserEntity, Long>