package com.financialLab.financedevapp.controllers;

import com.financialLab.financedevapp.dto.ExpensesDTO;
import com.financialLab.financedevapp.dto.InvestmentDTO;
import com.financialLab.financedevapp.dto.RevenueDTO;
import com.financialLab.financedevapp.dto.responses.ResponseDTO;
import com.financialLab.financedevapp.exception.FinancialAppException;
import com.financialLab.financedevapp.models.Expenses;
import com.financialLab.financedevapp.models.Investment;
import com.financialLab.financedevapp.models.Revenue;
import com.financialLab.financedevapp.models.User;
import com.financialLab.financedevapp.services.FinanceService;
import com.financialLab.financedevapp.services.InvestmentService;
import com.financialLab.financedevapp.services.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("v1/investments")
public class InvestmentController {

    @Autowired
    private InvestmentService investmentService;
    @Autowired
    private FinanceService financeService;

    @GetMapping("/{investmentExternalId}")
    public ResponseEntity<ResponseDTO<InvestmentDTO>> getInvestment(@PathVariable("investmentExternalId") String investmentExternalId){
        SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity.ok(ResponseDTO.ofSuccess(InvestmentDTO.of(investmentService.getByExternalId(investmentExternalId))));
    }

    @PostMapping
    public ResponseEntity<ResponseDTO<InvestmentDTO>> create(@RequestBody InvestmentDTO investmentDTO){
        User loggedUser = SecurityService.getLoggedUser();
        return ResponseEntity.ok(ResponseDTO.ofSuccess(InvestmentDTO.of(investmentService.create(investmentDTO, loggedUser))));
    }


    @PostMapping("/add-list")
    public ResponseEntity<ResponseDTO<List<InvestmentDTO>>> createAll(@RequestBody List<InvestmentDTO> investmentDTO) {
        User loggedUser = SecurityService.getLoggedUser();
        List<Investment> response = investmentService.createAll(investmentDTO, loggedUser);
        return ResponseEntity.ok(ResponseDTO.ofSuccess(response.stream().map(InvestmentDTO::of).toList()));
    }

    @DeleteMapping("/{externalId}")
    public ResponseEntity<ResponseDTO<InvestmentDTO>> deleteInvestmentById(@PathVariable("externalId") String externalId){
        SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity.ok(ResponseDTO.ofSuccess(InvestmentDTO.of(investmentService.deleteByExternalId(externalId))));
    }

    @PutMapping("/{externalId}")
    public ResponseEntity<ResponseDTO<InvestmentDTO>> updateInvestment(@PathVariable("externalId") String externalId, @RequestBody InvestmentDTO dto){
        SecurityContextHolder.getContext().getAuthentication().getName();
        Investment newEntity = InvestmentDTO.toModel(dto);
        Investment updatedEntity = investmentService.updateByExternalId(externalId, newEntity);
        return ResponseEntity.ok(ResponseDTO.ofSuccess(InvestmentDTO.of(updatedEntity)));
    }
}
