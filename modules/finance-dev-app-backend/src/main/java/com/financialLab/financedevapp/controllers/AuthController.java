package com.financialLab.financedevapp.controllers;

import com.financialLab.financedevapp.dto.UserDTO;
import com.financialLab.financedevapp.dto.requests.AuthRequestDTO;
import com.financialLab.financedevapp.dto.requests.NewUserRequestDTO;
import com.financialLab.financedevapp.dto.responses.AuthenticationResponseDTO;
import com.financialLab.financedevapp.dto.responses.ResponseDTO;
import com.financialLab.financedevapp.models.Role;
import com.financialLab.financedevapp.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private MyUserDetailsService myUserDetailsService;
    @Autowired
    private AuthService authService;


    @PostMapping("/register")
    public ResponseEntity<ResponseDTO<UserDTO>> create(@RequestBody NewUserRequestDTO userDTO) {
        Role defaultRole = roleService.getUserRole();
        return ResponseEntity.ok(ResponseDTO.ofSuccess(UserDTO.of(userService.create(userDTO, defaultRole))));
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO<AuthenticationResponseDTO>> loginAndGetToken(@RequestBody AuthRequestDTO authRequestDTO) throws Exception {
        AuthenticationResponseDTO response = authService.login(authRequestDTO);
        return ResponseEntity.ok(ResponseDTO.ofSuccess(response));
    }

    @GetMapping("/login")
    public ResponseEntity<ResponseDTO<?>> loginAndGetToken() throws Exception {
        return ResponseEntity.ok(ResponseDTO.ofSuccess("Hello world"));
    }

}