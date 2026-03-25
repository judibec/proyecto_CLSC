package com.financialLab.financedevapp.controllers;

import com.financialLab.financedevapp.dto.FinanceDTO;
import com.financialLab.financedevapp.dto.responses.ResponseDTO;
import com.financialLab.financedevapp.models.Finance;
import com.financialLab.financedevapp.models.User;
import com.financialLab.financedevapp.services.FinanceService;
import com.financialLab.financedevapp.services.SecurityService;
import com.financialLab.financedevapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("v1/finances")
public class FinanceController {

    @Autowired
    private FinanceService financeService;
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<ResponseDTO<List<FinanceDTO>>> getAllFinance(){
        return ResponseEntity.ok(ResponseDTO.ofSuccess(financeService.getAllFinances()));
    }

    @PostMapping
    public ResponseEntity<ResponseDTO<FinanceDTO>> create() {
        User user = SecurityService.getLoggedUser();
        Finance finance = financeService.create(user);
        return ResponseEntity.ok(ResponseDTO.ofSuccess(FinanceDTO.of(finance)));
    }

    @GetMapping("/my-finance")
    public ResponseEntity<ResponseDTO<FinanceDTO>> getFinanceByUser() {
        User user = SecurityService.getLoggedUser();
        return ResponseEntity.ok(ResponseDTO.ofSuccess(FinanceDTO.of(financeService.getByExternalId(user.getFinance().getExternalId()))));
    }

}
