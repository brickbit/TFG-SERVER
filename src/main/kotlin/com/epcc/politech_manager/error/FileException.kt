package com.epcc.politech_manager.error

import java.lang.RuntimeException

class FileException(exception: ExceptionUserModel) : RuntimeException(exception.name)