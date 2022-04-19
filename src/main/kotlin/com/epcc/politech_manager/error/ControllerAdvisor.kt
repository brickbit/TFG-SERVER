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

    @ExceptionHandler(UserException::class)
    fun handleIncorrectDataException(
            ex: UserException?, request: WebRequest?): ResponseEntity<Any>? {
        val body: MutableMap<String, Any> = LinkedHashMap()
        body["timestamp"] = LocalDateTime.now()
        body["message"] = ex!!.message!!
        return ResponseEntity(body, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(FileException::class)
    fun handleFileException(
            ex: UserException?, request: WebRequest?): ResponseEntity<Any>? {
        val body: MutableMap<String, Any> = LinkedHashMap()
        body["timestamp"] = LocalDateTime.now()
        body["message"] = ex!!.message!!
        return ResponseEntity(body, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(DataException::class)
    fun handleDataException(
            ex: UserException?, request: WebRequest?): ResponseEntity<Any>? {
        val body: MutableMap<String, Any> = LinkedHashMap()
        body["timestamp"] = LocalDateTime.now()
        body["message"] = ex!!.message!!
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