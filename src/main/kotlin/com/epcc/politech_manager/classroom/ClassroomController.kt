package com.epcc.politech_manager.classroom

import com.epcc.politech_manager.error.DataException
import com.epcc.politech_manager.error.ExceptionDataModel
import com.epcc.politech_manager.error.ExceptionUserModel
import com.epcc.politech_manager.error.UserException
import com.epcc.politech_manager.user.UserEntityDAO
import com.epcc.politech_manager.user.UserService
import com.epcc.politech_manager.utils.ResponseOk
import org.springframework.web.bind.annotation.*

@RestController
class ClassroomController(val service: ClassroomService, val userService: UserService) {

    @GetMapping("/classroom")
    fun index(@RequestHeader("Authorization") auth: String): List<ClassroomEntityDTO> {
        val user: UserEntityDAO? = userService.getUserWithToken(auth)
        if (user != null) {
            return service.getAllClassrooms().filter { it.user == user }.map { it.toDTO() }
        } else {
            throw UserException(ExceptionUserModel.WRONG_USER)
        }
    }

    @PostMapping("/classroom")
    fun post(@RequestHeader("Authorization") auth: String,
             @RequestBody classroom: ClassroomEntityDTO)
    : ResponseOk {
        val user: UserEntityDAO? = userService.getUserWithToken(auth)
        if (user != null) {
            service.post(classroom.toDAO(user))
            return ResponseOk(200, "Classroom successfully created")
        } else {
            throw UserException(ExceptionUserModel.WRONG_USER)
        }
    }

    @GetMapping("/classroom/{id}")
    fun getClassroom(@RequestHeader("Authorization") auth: String,
                     @PathVariable id: String)
    : ClassroomEntityDTO? {
        val user: UserEntityDAO? = userService.getUserWithToken(auth)
        if (user != null) {
            val classroom = service.getClassroom(id)
            if (classroom == null) {
                throw DataException(ExceptionDataModel.CLASSROOM_NOT_EXIST)
            } else {
                if (classroom.user == user) {
                    return classroom.toDTO()
                } else {
                    throw UserException(ExceptionUserModel.WRONG_USER)
                }
            }
        } else {
            throw UserException(ExceptionUserModel.WRONG_USER)
        }
    }

    @PostMapping("/classroom/delete/{id}")
    fun deleteClassroom(@RequestHeader("Authorization") auth: String,
                        @PathVariable id: String)
    : ResponseOk {
        val user: UserEntityDAO? = userService.getUserWithToken(auth)
        if (user != null) {
            service.deleteClassroom(id)
            return ResponseOk(200,"Classroom successfully deleted")
        } else {
            throw UserException(ExceptionUserModel.WRONG_USER)
        }
    }

    @PostMapping("/classroom/update")
    fun updateClassroom(@RequestHeader("Authorization") auth: String,
                        @RequestBody classroom: ClassroomEntityDTO)
    : ResponseOk {
        val user: UserEntityDAO? = userService.getUserWithToken(auth)
        if (user != null) {
            service.updateClassroom(classroom.toDAO(user))
            return ResponseOk(200,"Degree successfully updated")
        } else {
            throw UserException(ExceptionUserModel.WRONG_USER)
        }
    }
}