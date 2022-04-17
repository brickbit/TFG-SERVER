package com.epcc.politech_manager.subject

import com.epcc.politech_manager.error.ExceptionUserModel
import com.epcc.politech_manager.error.UserException
import com.epcc.politech_manager.user.UserEntityDAO
import com.epcc.politech_manager.user.UserService
import com.epcc.politech_manager.utils.ResponseOk
import org.springframework.web.bind.annotation.*

@RestController
class SubjectController(val service: SubjectService, val userService: UserService) {

    @GetMapping("/subject")
    fun index(@RequestHeader("Authorization") auth: String): List<SubjectEntityDTO> {
        val user: UserEntityDAO? = userService.getUserWithToken(auth)
        if (user != null) {
            return service.getAllSubjects().filter { it.user == user }.map { it.toDTO() }
        } else {
            throw UserException(ExceptionUserModel.WRONG_USER)
        }
    }

    @PostMapping("/subject")
    fun post(@RequestHeader("Authorization") auth: String,
             @RequestBody subject: SubjectEntityDTO)
    : ResponseOk {
        val user: UserEntityDAO? = userService.getUserWithToken(auth)
        if (user != null) {
            service.post(subject.toDAO(user))
            return ResponseOk(200, "Subject successfully created")
        } else {
            throw UserException(ExceptionUserModel.WRONG_USER)
        }
    }

    @GetMapping("/subject/{id}")
    fun getSubject(@RequestHeader("Authorization") auth: String,
                   @PathVariable id: Long)
    : SubjectEntityDTO? {
        val user: UserEntityDAO? = userService.getUserWithToken(auth)
        if (user != null) {
            val subject = service.getSubject(id)
            if (subject?.user == user) {
                return subject.toDTO()
            } else {
                throw UserException(ExceptionUserModel.WRONG_USER)
            }
        } else {
            throw UserException(ExceptionUserModel.WRONG_USER)
        }
    }

    @PostMapping("/subject/delete/{id}")
    fun deleteSubject(@RequestHeader("Authorization") auth: String,
                      @PathVariable id: Long)
    : ResponseOk {
        val user: UserEntityDAO? = userService.getUserWithToken(auth)
        if (user != null) {
            service.deleteSubject(id)
            return ResponseOk(200,"Subject successfully deleted")
        } else {
            throw UserException(ExceptionUserModel.WRONG_USER)
        }
    }

    @PostMapping("/subject/update")
    fun updateSubject(@RequestHeader("Authorization") auth: String,
                      @RequestBody subject: SubjectEntityDTO)
    : ResponseOk {
        val user: UserEntityDAO? = userService.getUserWithToken(auth)
        if (user != null) {
            service.updateSubject(subject.toDAO(user))
            return ResponseOk(200,"Subject successfully updated")
        } else {
            throw UserException(ExceptionUserModel.WRONG_USER)
        }
    }
}