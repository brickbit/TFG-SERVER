package com.epcc.politech_manager.error

import java.lang.RuntimeException

class LoginException(code: Int) : RuntimeException(code.toString())