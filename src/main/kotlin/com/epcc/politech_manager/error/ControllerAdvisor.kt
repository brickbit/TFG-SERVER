package com.epcc.politech_manager.error

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.stream.Collectors.*


@ControllerAdvice
class ControllerAdvisor: ResponseEntityExceptionHandler() {

    @ExceptionHandler(LoginException::class)
    fun handleIncorrectDataException(
            ex: LoginException?, request: WebRequest?): ResponseEntity<Any>? {
        val body: MutableMap<String, Any> = LinkedHashMap()
        body["timestamp"] = LocalDateTime.now()
        body["code"] = ex!!.message!!
        body["message"] = when (ex.message) {
            "100" -> "User not found"
            "200" -> "Passwords do not match"
            "300" -> "The password must contain at least one uppercase letter, one lowercase letter, a number and one of the special characters \"! @ # & ( )\", it must have a length greater than 8 and less than 20"
            "400" -> "The new password must be different from the old one"
            else -> "Unknown error"
        }

        return ResponseEntity(body, HttpStatus.NOT_FOUND)
    }

    override fun handleMethodArgumentNotValid(
            ex: MethodArgumentNotValidException, headers: HttpHeaders?,
            status: HttpStatus, request: WebRequest?): ResponseEntity<Any>? {
        val body: MutableMap<String, Any> = LinkedHashMap()
        body["timestamp"] = LocalDate.now()
        body["status"] = status.value()
        val errors: List<String> = ex.bindingResult
                .fieldErrors
                .stream()
                .map { x: FieldError -> x.defaultMessage }
                .collect(toList())
        body["errors"] = errors
        return ResponseEntity(body, HttpStatus.BAD_REQUEST)
    }
}