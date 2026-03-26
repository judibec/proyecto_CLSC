package com.financialLab.financedevapp.controllers;

import com.financialLab.financedevapp.dto.FinanceDTO;
import com.financialLab.financedevapp.dto.responses.ResponseDTO;
import com.financialLab.financedevapp.dto.UserDTO;
import com.financialLab.financedevapp.dto.requests.NewUserRequestDTO;
import com.financialLab.financedevapp.exception.ErrorCode;
import com.financialLab.financedevapp.exception.FinancialAppException;
import com.financialLab.financedevapp.models.Finance;
import com.financialLab.financedevapp.models.User;
import com.financialLab.financedevapp.services.FinanceService;
import com.financialLab.financedevapp.services.SecurityService;
import com.financialLab.financedevapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("v1/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private FinanceService financeService;

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ResponseDTO<List<UserDTO>>> getAllUsers() {
        return ResponseEntity.ok(ResponseDTO.ofSuccess(userService.getAllUsers()));
    }

    @GetMapping("/{userExternalId}")
    public ResponseEntity<ResponseDTO<UserDTO>> getUser(@PathVariable("userExternalId") String userExternalId) {
        try {
            return ResponseEntity.ok(ResponseDTO.ofSuccess(userService.getUser(userExternalId)));
        } catch (Exception e) {
            throw FinancialAppException.objectNotFound("User not found with external ID: " + userExternalId);
        }
    }

    @DeleteMapping("{userExternalId}")
    public ResponseEntity<ResponseDTO<UserDTO>> deleteUser(@PathVariable("userExternalId") String userExternalId){
        try{
            return ResponseEntity.ok(ResponseDTO.ofSuccess(UserDTO.of(userService.deleteByExternalId(userExternalId))));
        }catch (Exception e){
            throw FinancialAppException.objectNotFound("User not found with external ID: " + userExternalId);
        }
    }

    @PutMapping("{userExternalId}")
    public ResponseEntity<ResponseDTO<UserDTO>> updateUser(@PathVariable("userExternalId") String userExternalId, @RequestBody UserDTO userDTO){
        try{
            User user = UserDTO.toModel(userDTO);
            User updatedUser = userService.updateByExternalId(userExternalId, user);
            return ResponseEntity.ok(ResponseDTO.ofSuccess(UserDTO.of(updatedUser)));
        }catch (Exception e){
            throw FinancialAppException.objectNotFound("User not found with external ID: " + userExternalId);
        }
    }

    @PutMapping("/my-update")
    public ResponseEntity<ResponseDTO<UserDTO>> updateUser(@RequestBody UserDTO userDTO){
        User usertoUpdate = SecurityService.getLoggedUser();
        try{
            User user = UserDTO.toModel(userDTO);
            User updatedUser = userService.updateByExternalId(usertoUpdate.getExternalId(), user);
            return ResponseEntity.ok(ResponseDTO.ofSuccess(UserDTO.of(updatedUser)));
        }catch (Exception e){
            throw FinancialAppException.objectNotFound("User not found with external ID: " + usertoUpdate.getExternalId());
        }
    }

    @PostMapping
    public ResponseEntity<ResponseDTO<String>> test(@RequestBody String credentials) {
        String[] testList = credentials.split(" ");
        userService.test(testList[0], testList[1]);
        return ResponseEntity.ok(ResponseDTO.ofSuccess("Ok"));
    }
}
