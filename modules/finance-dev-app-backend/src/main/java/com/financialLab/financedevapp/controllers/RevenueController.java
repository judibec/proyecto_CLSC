package com.financialLab.financedevapp.controllers;

import com.financialLab.financedevapp.dto.ExpensesDTO;
import com.financialLab.financedevapp.dto.RevenueDTO;
import com.financialLab.financedevapp.dto.responses.ResponseDTO;
import com.financialLab.financedevapp.exception.FinancialAppException;
import com.financialLab.financedevapp.models.Expenses;
import com.financialLab.financedevapp.models.Revenue;
import com.financialLab.financedevapp.models.User;
import com.financialLab.financedevapp.services.FinanceService;
import com.financialLab.financedevapp.services.RevenueService;
import com.financialLab.financedevapp.services.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("v1/revenues")
public class RevenueController {

    @Autowired
    private RevenueService revenueService;
    @Autowired
    private FinanceService financeService;

    @GetMapping("/{revenueExternalId}")
    public ResponseEntity<ResponseDTO<RevenueDTO>> getRevenue(@PathVariable("revenueExternalId") String revenueExternalId){
        SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity.ok(ResponseDTO.ofSuccess(RevenueDTO.of(revenueService.getByExternalId(revenueExternalId))));
    }

    @PostMapping
    public ResponseEntity<ResponseDTO<RevenueDTO>> create(@RequestBody RevenueDTO revenueDTO) {
        User loggedUser = SecurityService.getLoggedUser();
        return ResponseEntity.ok(ResponseDTO.ofSuccess(RevenueDTO.of(revenueService.create(revenueDTO, loggedUser))));
    }

    @PostMapping("/add-list")
    public ResponseEntity<ResponseDTO<List<RevenueDTO>>> createAll(@RequestBody List<RevenueDTO> revenueDTO) {
        User loggedUser = SecurityService.getLoggedUser();
        List<Revenue> response = revenueService.createAll(revenueDTO, loggedUser);
        return ResponseEntity.ok(ResponseDTO.ofSuccess(response.stream().map(RevenueDTO::of).toList()));
    }

    @DeleteMapping("/{externalId}")
    public ResponseEntity<ResponseDTO<RevenueDTO>> deleteRevenueById(@PathVariable("externalId") String externalId){
        SecurityContextHolder.getContext().getAuthentication().getName();
        Revenue deletedEntity = revenueService.deleteByExternalId(externalId);
        revenueService.calculateSaving(SecurityService.getLoggedUser());
        return ResponseEntity.ok(ResponseDTO.ofSuccess(RevenueDTO.of(deletedEntity)));
    }

    @PutMapping("/{externalId}")
    public ResponseEntity<ResponseDTO<RevenueDTO>> updateRevenue(@PathVariable("externalId") String externalId, @RequestBody RevenueDTO dto){
        SecurityContextHolder.getContext().getAuthentication().getName();
        Revenue newEntity = RevenueDTO.toModel(dto);
        Revenue updatedEntity = revenueService.updateByExternalId(externalId, newEntity);
        revenueService.calculateSaving(SecurityService.getLoggedUser());
        return ResponseEntity.ok(ResponseDTO.ofSuccess(RevenueDTO.of(updatedEntity)));
    }
}
