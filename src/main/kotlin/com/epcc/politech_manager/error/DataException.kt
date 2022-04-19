package com.epcc.politech_manager.error

import java.lang.RuntimeException

class DataException(exception: ExceptionDataModel) : RuntimeException(exception.name)