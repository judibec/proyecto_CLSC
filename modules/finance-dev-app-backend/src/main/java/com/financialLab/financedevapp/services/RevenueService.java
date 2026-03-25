package com.financialLab.financedevapp.services;

import com.financialLab.financedevapp.dto.RevenueDTO;
import com.financialLab.financedevapp.exception.FinancialAppException;
import com.financialLab.financedevapp.models.*;
import com.financialLab.financedevapp.repository.RevenueRepository;
import com.financialLab.financedevapp.types.FrequencyType;
import com.financialLab.financedevapp.types.RevenueType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class RevenueService extends SimpleCrudService<Revenue, RevenueRepository> {

    @Autowired
    private FinanceService financeService;

    public RevenueService(RevenueRepository repository) {
        super(repository);
    }

    @Override
    protected void updateData(Revenue existingObject, Revenue updatedObject) {
        if (updatedObject.getAmount() != null) {
            existingObject.setAmount(updatedObject.getAmount());
        }
    }

    @Transactional
    public Revenue create(RevenueDTO revenueDTO, User loggedUser) {
        try{
            Revenue revenue = RevenueDTO.toModel(revenueDTO);
            revenue.setFinance(loggedUser.getFinance());
            Revenue savedRevenue = this.save(revenue);
            calculateSaving(loggedUser);
            return savedRevenue;
        }catch (Exception e) {
            throw FinancialAppException.serverException("Error creando el ingreso " + e.getMessage(), true);
        }
    }

    @Transactional
    public List<Revenue> createAll(List<RevenueDTO> revenueDTOS, User loggedUser) {
        try{
            List<Revenue> castList = revenueDTOS.stream().map(RevenueDTO::toModel).toList();
            castList.forEach(revenue -> revenue.setFinance(loggedUser.getFinance()));

            Iterable<Revenue> response = this.saveAll(castList);
            List<Revenue> result = new ArrayList<>();
            response.forEach(result::add);
            calculateSaving(loggedUser);

            return result;
        }catch (Exception e) {
            throw FinancialAppException.serverException("Error creando los ingresos " + e.getMessage(), true);
        }
    }

    @Transactional
    public void calculateSaving(User loggedUser){
        Finance finance = loggedUser.getFinance();
        List<Expenses> expenses = finance.getExpenses();
        List<Debt> debts = finance.getDebts();
        List<Revenue> revenues = finance.getRevenues();
        Double revenuesAmount = revenues.stream().filter(revenue -> !RevenueType.SAVINGS.equals(revenue.getType()))
                .mapToDouble(Revenue::getAmount)
                .sum();
        Double expensesAmount = expenses.stream()
                .mapToDouble(Expenses::getAmount)
                .sum();
        Double monthlyDebts = debts.stream().filter(debt -> FrequencyType.MONTHLY.equals(debt.getFrequency()))
                .mapToDouble(Debt::getAmount)
                .sum();
        double savings = revenuesAmount - (expensesAmount+monthlyDebts);
        Optional<Revenue> savingsRevenue = revenues.stream()
                .filter(revenue -> RevenueType.SAVINGS.equals(revenue.getType()))
                .findFirst();
        if (savingsRevenue.isPresent()) {
            Revenue existingRevenue = savingsRevenue.get();
            existingRevenue.setAmount((long) savings);
        }else {
            RevenueDTO dto = new RevenueDTO();
            dto.setType(RevenueType.valueOf("SAVINGS"));
            dto.setDescription("ahorros");
            dto.setAmount((long) savings);
            Revenue savingRevenue = RevenueDTO.toModel(dto);
            savingRevenue.setFinance(loggedUser.getFinance());
            this.save(savingRevenue);
        }
    }
}
