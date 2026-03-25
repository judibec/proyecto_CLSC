package com.financialLab.financedevapp.controllers;

import com.financialLab.financedevapp.dto.RoleDTO;
import com.financialLab.financedevapp.dto.responses.ResponseDTO;
import com.financialLab.financedevapp.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("v1/roles")
public class RoleController {

    @Autowired
    RoleService roleService;

    @GetMapping
    public ResponseEntity<ResponseDTO<List<RoleDTO>>> getAllRoles(){
        return ResponseEntity.ok(ResponseDTO.ofSuccess(roleService.getAllRoles()));
    }

    @PostMapping
    public ResponseEntity<ResponseDTO<RoleDTO>> create(@RequestBody RoleDTO roleDTO) {
        return ResponseEntity.ok(ResponseDTO.ofSuccess(roleService.create(roleDTO)));
    }

}
