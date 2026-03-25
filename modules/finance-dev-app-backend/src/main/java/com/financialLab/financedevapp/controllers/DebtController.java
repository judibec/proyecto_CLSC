package com.financialLab.financedevapp.controllers;

import com.financialLab.financedevapp.dto.AssetDTO;
import com.financialLab.financedevapp.dto.DebtDTO;
import com.financialLab.financedevapp.dto.RevenueDTO;
import com.financialLab.financedevapp.dto.responses.ResponseDTO;
import com.financialLab.financedevapp.models.*;
import com.financialLab.financedevapp.services.DebtService;
import com.financialLab.financedevapp.services.RevenueService;
import com.financialLab.financedevapp.services.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("v1/debts")
public class DebtController {
    @Autowired
    private DebtService debtService;
    @Autowired
    private RevenueService revenueService;

    @GetMapping("/{debtExtenalId}")
    public ResponseEntity<ResponseDTO<DebtDTO>> getDebt(@PathVariable("debtExtenalId") String debtExternalId){
        SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity.ok(ResponseDTO.ofSuccess(DebtDTO.of(debtService.getByExternalId(debtExternalId))));

    }

    @PostMapping
    public ResponseEntity<ResponseDTO<DebtDTO>> create(@RequestBody DebtDTO debtDTO) {
        User loggedUser = SecurityService.getLoggedUser();
        return ResponseEntity.ok(ResponseDTO.ofSuccess(DebtDTO.of(debtService.create(debtDTO, loggedUser))));
    }

    @PostMapping("/add-list")
    public ResponseEntity<ResponseDTO<List<DebtDTO>>> createAll(@RequestBody List<DebtDTO> debtDTOS) {
        User loggedUser = SecurityService.getLoggedUser();
        List<Debt> response = debtService.createAll(debtDTOS, loggedUser);
        return ResponseEntity.ok(ResponseDTO.ofSuccess(response.stream().map(DebtDTO::of).toList()));
    }

    @DeleteMapping("/{externalId}")
    public ResponseEntity<ResponseDTO<DebtDTO>> deleteDebtById(@PathVariable("externalId") String externalId){
        SecurityContextHolder.getContext().getAuthentication().getName();
        Debt deletedEntity = debtService.deleteByExternalId(externalId);
        revenueService.calculateSaving(SecurityService.getLoggedUser());
        return ResponseEntity.ok(ResponseDTO.ofSuccess(DebtDTO.of(deletedEntity)));
    }

    @PutMapping("/{externalId}")
    public ResponseEntity<ResponseDTO<DebtDTO>> updateDebt(@PathVariable("externalId") String externalId, @RequestBody DebtDTO dto){
        SecurityContextHolder.getContext().getAuthentication().getName();
        Debt newEntity = DebtDTO.toModel(dto);
        Debt updatedEntity = debtService.updateByExternalId(externalId, newEntity);
        revenueService.calculateSaving(SecurityService.getLoggedUser());
        return ResponseEntity.ok(ResponseDTO.ofSuccess(DebtDTO.of(updatedEntity)));
    }

}
