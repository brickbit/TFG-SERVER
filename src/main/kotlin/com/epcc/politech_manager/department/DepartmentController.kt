package com.epcc.politech_manager.department

import com.epcc.politech_manager.error.DataException
import com.epcc.politech_manager.error.ExceptionDataModel
import com.epcc.politech_manager.error.ExceptionUserModel
import com.epcc.politech_manager.error.UserException
import com.epcc.politech_manager.user.UserEntityDAO
import com.epcc.politech_manager.user.UserService
import com.epcc.politech_manager.utils.ResponseOk
import org.springframework.web.bind.annotation.*

@RestController
class DepartmentController(val service: DepartmentService, val userService: UserService) {

    @GetMapping("/department")
    fun index(@RequestHeader("Authorization") auth: String): List<DepartmentEntityDTO> {
        val user: UserEntityDAO? = userService.getUserWithToken(auth)
        if (user != null) {
            return service.getAllDepartments().filter { it.user == user }.map { it.toDTO() }
        } else {
            throw UserException(ExceptionUserModel.WRONG_USER)
        }
    }

    @PostMapping("/department")
    fun post(@RequestHeader("Authorization") auth: String,
             @RequestBody department: DepartmentEntityDTO)
    : ResponseOk {
        val user: UserEntityDAO? = userService.getUserWithToken(auth)
        if (user != null) {
            service.post(department.toDAO(user))
            return ResponseOk(200, "Department successfully created")
        } else {
            throw UserException(ExceptionUserModel.WRONG_USER)
        }
    }

    @GetMapping("/department/{id}")
    fun getDepartment(@RequestHeader("Authorization") auth: String,
                      @PathVariable id: String)
    : DepartmentEntityDTO? {
        val user: UserEntityDAO? = userService.getUserWithToken(auth)
        if (user != null) {
            val department: DepartmentEntityDAO? = service.getDepartment(id)
            if (department == null) {
                throw DataException(ExceptionDataModel.DEPARTMENT_NOT_EXIST)
            } else {
                if (department.user == user) {
                    return department.toDTO()
                } else {
                    throw UserException(ExceptionUserModel.WRONG_USER)
                }
            }
        } else {
            throw UserException(ExceptionUserModel.WRONG_USER)
        }
    }

    @PostMapping("/department/delete/{id}")
    fun deleteDepartment(@RequestHeader("Authorization") auth: String,
                         @PathVariable id: String)
    : ResponseOk {
        val user: UserEntityDAO? = userService.getUserWithToken(auth)
        if (user != null) {
            service.deleteDepartment(id)
            return ResponseOk(200,"Department successfully deleted")
        } else {
            throw UserException(ExceptionUserModel.WRONG_USER)
        }
    }

    @PostMapping("/department/update")
    fun updateDepartment(@RequestHeader("Authorization") auth: String,
                         @RequestBody department: DepartmentEntityDTO)
    : ResponseOk {
        val user: UserEntityDAO? = userService.getUserWithToken(auth)
        if (user != null) {
            service.updateDepartment(department.toDAO(user))
            return ResponseOk(200,"Department successfully updated")
        } else {
            throw UserException(ExceptionUserModel.WRONG_USER)
        }
    }
}