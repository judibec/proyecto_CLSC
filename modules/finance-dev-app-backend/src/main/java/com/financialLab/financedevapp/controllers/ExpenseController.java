package com.financialLab.financedevapp.controllers;

import com.financialLab.financedevapp.dto.DebtDTO;
import com.financialLab.financedevapp.dto.ExpensesDTO;
import com.financialLab.financedevapp.dto.responses.ResponseDTO;
import com.financialLab.financedevapp.exception.FinancialAppException;
import com.financialLab.financedevapp.models.Debt;
import com.financialLab.financedevapp.models.Expenses;
import com.financialLab.financedevapp.models.Revenue;
import com.financialLab.financedevapp.models.User;
import com.financialLab.financedevapp.services.ExpensesService;
import com.financialLab.financedevapp.services.FinanceService;
import com.financialLab.financedevapp.services.RevenueService;
import com.financialLab.financedevapp.services.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("v1/expenses")
public class ExpenseController {
    @Autowired
    private ExpensesService expensesService;
    @Autowired
    private RevenueService revenueService;
    @Autowired
    private FinanceService financeService;

    @GetMapping("/{expensesExternalId}")
    public ResponseEntity<ResponseDTO<ExpensesDTO>> getExpenses(@PathVariable("expensesExternalId") String expensesExternalId) {
        SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity.ok(ResponseDTO.ofSuccess(ExpensesDTO.of(expensesService.getByExternalId(expensesExternalId))));
    }

    @GetMapping
    //ENDPOINT FOR TEST FAIL MESSAGE ON SERVER SIDE
    public ResponseEntity<ResponseDTO<List<ExpensesDTO>>> getAll() {
        SecurityContextHolder.getContext().getAuthentication().getName();
        String test = null;
        test.equals("hla");
        return ResponseEntity.ok(ResponseDTO.ofSuccess(new ArrayList<ExpensesDTO>()));
    }

    @PostMapping
    public ResponseEntity<ResponseDTO<ExpensesDTO>> create(@RequestBody ExpensesDTO expensesDTO){
        User loggedUser = SecurityService.getLoggedUser();
        return ResponseEntity.ok(ResponseDTO.ofSuccess(ExpensesDTO.of(expensesService.create(expensesDTO, loggedUser))));
    }


    @PostMapping("/add-list")
    public ResponseEntity<ResponseDTO<List<ExpensesDTO>>> createAll(@RequestBody List<ExpensesDTO> expensesDTOS) {
        User loggedUser = SecurityService.getLoggedUser();
        List<Expenses> response = expensesService.createAll(expensesDTOS, loggedUser);
        return ResponseEntity.ok(ResponseDTO.ofSuccess(response.stream().map(ExpensesDTO::of).toList()));
    }

    @DeleteMapping("/{externalId}")
    public ResponseEntity<ResponseDTO<ExpensesDTO>> deleteExpenseById(@PathVariable("externalId") String externalId){
        SecurityContextHolder.getContext().getAuthentication().getName();
        Expenses deletedEntity = expensesService.deleteByExternalId(externalId);
        revenueService.calculateSaving(SecurityService.getLoggedUser());
        return ResponseEntity.ok(ResponseDTO.ofSuccess(ExpensesDTO.of(deletedEntity)));
    }

    @PutMapping("/{externalId}")
    public ResponseEntity<ResponseDTO<ExpensesDTO>> updateExpense(@PathVariable("externalId") String externalId, @RequestBody ExpensesDTO dto){
        SecurityContextHolder.getContext().getAuthentication().getName();
        Expenses newEntity = ExpensesDTO.toModel(dto);
        Expenses updatedEntity = expensesService.updateByExternalId(externalId, newEntity);
        revenueService.calculateSaving(SecurityService.getLoggedUser());
        return ResponseEntity.ok(ResponseDTO.ofSuccess(ExpensesDTO.of(updatedEntity)));
    }
}
