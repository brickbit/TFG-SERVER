package com.epcc.politech_manager.degree

import com.epcc.politech_manager.error.DataException
import com.epcc.politech_manager.error.ExceptionDataModel
import com.epcc.politech_manager.error.ExceptionUserModel
import com.epcc.politech_manager.error.UserException
import com.epcc.politech_manager.user.UserEntityDAO
import com.epcc.politech_manager.user.UserService
import com.epcc.politech_manager.utils.ResponseOk
import org.springframework.web.bind.annotation.*

@RestController
class DegreeController(val service: DegreeService, val userService: UserService) {

    @GetMapping("/degree")
    fun index(@RequestHeader("Authorization") auth: String): List<DegreeEntityDTO> {
        val user: UserEntityDAO? = userService.getUserWithToken(auth)
        if (user != null) {
            return service.getAllDegrees().filter { it.userEntity == user }.map { it.toDTO() }
        } else {
            throw UserException(ExceptionUserModel.WRONG_USER)
        }
    }

    @PostMapping("/degree")
    fun post(@RequestHeader("Authorization") auth: String,
             @RequestBody degree: DegreeEntityDTO)
    : ResponseOk {
        val user: UserEntityDAO? = userService.getUserWithToken(auth)
        if (user != null) {
            service.post(degree.toDAO(user))
            return ResponseOk(200, "Degree successfully created")
        } else {
            throw UserException(ExceptionUserModel.WRONG_USER)
        }
    }

    @GetMapping("/degree/{id}")
    fun getDegree(@RequestHeader("Authorization") auth: String,
                  @PathVariable id: String)
    : DegreeEntityDTO {
        val user: UserEntityDAO? = userService.getUserWithToken(auth)
        if (user != null) {
            val degree: DegreeEntityDAO? = service.getDegree(id)
            if (degree == null) {
                throw DataException(ExceptionDataModel.DEGREE_NOT_EXIST)
            } else {
                if (degree.userEntity == user) {
                    return degree.toDTO()
                } else {
                    throw UserException(ExceptionUserModel.WRONG_USER)
                }
            }
        } else {
            throw UserException(ExceptionUserModel.WRONG_USER)
        }
    }

    @PostMapping("/degree/delete/{id}")
    fun deleteDegree(@RequestHeader("Authorization") auth: String,
                     @PathVariable id: String)
    : ResponseOk {
        val user: UserEntityDAO? = userService.getUserWithToken(auth)
        if (user != null) {
            service.deleteDegree(id)
            return ResponseOk(200,"Degree successfully deleted")
        } else {
            throw UserException(ExceptionUserModel.WRONG_USER)
        }
    }

    @PostMapping("/degree/update")
    fun updateDegree(@RequestHeader("Authorization") auth: String,
                     @RequestBody degree: DegreeEntityDTO)
    : ResponseOk {
        val user: UserEntityDAO? = userService.getUserWithToken(auth)
        if (user != null) {
            service.updateDegree(degree.toDAO(user))
            return ResponseOk(200,"Degree successfully updated")
        } else {
            throw UserException(ExceptionUserModel.WRONG_USER)
        }
    }
}