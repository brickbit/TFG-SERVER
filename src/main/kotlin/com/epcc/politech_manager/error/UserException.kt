package com.epcc.politech_manager.error

import java.lang.RuntimeException

class UserException(exception: ExceptionUserModel) : RuntimeException(exception.name)