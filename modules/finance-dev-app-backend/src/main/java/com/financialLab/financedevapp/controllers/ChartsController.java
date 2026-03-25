package com.financialLab.financedevapp.controllers;

import com.financialLab.financedevapp.dto.charts.ChartsGaugePercentageDTO;
import com.financialLab.financedevapp.dto.responses.ResponseDTO;
import com.financialLab.financedevapp.models.User;
import com.financialLab.financedevapp.services.ChartsService;
import com.financialLab.financedevapp.services.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("v1/charts")
public class ChartsController {

    @Autowired
    public ChartsService chartsService;

    @GetMapping("/debtOverIncome")
    public ResponseEntity<ResponseDTO<ChartsGaugePercentageDTO>> getDebtOverMonthlyIncome(){
        User loggedUser = SecurityService.getLoggedUser();
        return ResponseEntity.ok(ResponseDTO.ofSuccess(chartsService.getDebtOverMonthlyIncome(loggedUser)));
    }

    @GetMapping("/assetsVSDebt")
    public ResponseEntity<ResponseDTO<ChartsGaugePercentageDTO>> getAssetsVSDebts(){
        User loggedUser = SecurityService.getLoggedUser();
        return ResponseEntity.ok(ResponseDTO.ofSuccess(chartsService.getAssetsVSDebts(loggedUser)));
    }

    @GetMapping("/revenuesVSExpenses")
    public ResponseEntity<ResponseDTO<ChartsGaugePercentageDTO>> getRevenuesVSExpenses(){
        User loggedUser = SecurityService.getLoggedUser();
        return ResponseEntity.ok(ResponseDTO.ofSuccess(chartsService.getRevenuesVSExpenses(loggedUser)));
    }

    @GetMapping("/revenuesVSSavings")
    public ResponseEntity<ResponseDTO<ChartsGaugePercentageDTO>> getRevenuesVSSavings(){
        User loggedUser = SecurityService.getLoggedUser();
        return ResponseEntity.ok(ResponseDTO.ofSuccess(chartsService.getRevenuesVSSavings(loggedUser)));
    }

    @GetMapping("/finantialFreedom")
    public ResponseEntity<ResponseDTO<ChartsGaugePercentageDTO>> getFinantialFreedom(){
        User loggedUser = SecurityService.getLoggedUser();
        return ResponseEntity.ok(ResponseDTO.ofSuccess(chartsService.getFinantialFreedom(loggedUser)));
    }

}
