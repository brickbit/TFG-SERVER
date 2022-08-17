package com.epcc.politech_manager.teacher

import com.epcc.politech_manager.error.DataException
import com.epcc.politech_manager.error.ExceptionDataModel
import com.epcc.politech_manager.error.ExceptionUserModel
import com.epcc.politech_manager.error.UserException
import com.epcc.politech_manager.user.UserEntityDAO
import com.epcc.politech_manager.user.UserService
import com.epcc.politech_manager.utils.ResponseOk
import org.springframework.web.bind.annotation.*

@RestController
class TeacherController(val service: TeacherService, val userService: UserService) {

    @GetMapping("/teacher")
    @CrossOrigin(origins = ["https://politech-manager.herokuapp.com/"])
    fun index(@RequestHeader("Authorization") auth: String): List<TeacherEntityDTO> {
        val user: UserEntityDAO? = userService.getUserWithToken(auth)
        if (user != null) {
            return service.getAllTeachers().filter { it.userEntity == user }.map { it.toDTO() }
        } else {
            throw UserException(ExceptionUserModel.WRONG_USER)
        }
    }

    @PostMapping("/teacher")
    @CrossOrigin(origins = ["https://politech-manager.herokuapp.com/"])
    fun post(@RequestHeader("Authorization") auth: String,
             @RequestBody teacher: TeacherEntityDTO)
            : ResponseOk {
        val user: UserEntityDAO? = userService.getUserWithToken(auth)
        if (user != null) {
            service.post(teacher.toDAO(user))
            return ResponseOk(200, "Teacher successfully created")
        } else {
            throw UserException(ExceptionUserModel.WRONG_USER)
        }
    }

    @GetMapping("/teacher/{id}")
    @CrossOrigin(origins = ["https://politech-manager.herokuapp.com/"])
    fun getTeacher(@RequestHeader("Authorization") auth: String,
                   @PathVariable id: Long)
            : TeacherEntityDTO? {
        val user: UserEntityDAO? = userService.getUserWithToken(auth)
        if (user != null) {
            val teacher = service.getTeacher(id)
            if (teacher == null) {
                throw DataException(ExceptionDataModel.SUBJECT_NOT_EXIST)
            } else {
                if (teacher.userEntity == user) {
                    return teacher.toDTO()
                } else {
                    throw UserException(ExceptionUserModel.WRONG_USER)
                }
            }
        } else {
            throw UserException(ExceptionUserModel.WRONG_USER)
        }
    }

    @PostMapping("/teacher/delete/{id}")
    @CrossOrigin(origins = ["https://politech-manager.herokuapp.com/"])
    fun deleteTeacher(@RequestHeader("Authorization") auth: String,
                      @PathVariable id: Long)
            : ResponseOk {
        val user: UserEntityDAO? = userService.getUserWithToken(auth)
        if (user != null) {
            service.deleteTeacher(id)
            return ResponseOk(200,"Teacher successfully deleted")
        } else {
            throw UserException(ExceptionUserModel.WRONG_USER)
        }
    }

    @PostMapping("/teacher/update")
    @CrossOrigin(origins = ["https://politech-manager.herokuapp.com/"])
    fun updateTeacher(@RequestHeader("Authorization") auth: String,
                      @RequestBody teacher: TeacherEntityDTO)
            : ResponseOk {
        val user: UserEntityDAO? = userService.getUserWithToken(auth)
        if (user != null) {
            service.updateTeacher(teacher.toDAO(user))
            return ResponseOk(200,"Teacher successfully updated")
        } else {
            throw UserException(ExceptionUserModel.WRONG_USER)
        }
    }
}