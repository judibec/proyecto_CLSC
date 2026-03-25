package com.financialLab.financedevapp.services;

import com.financialLab.financedevapp.dto.ExpensesDTO;
import com.financialLab.financedevapp.exception.FinancialAppException;
import com.financialLab.financedevapp.models.Expenses;
import com.financialLab.financedevapp.models.Finance;
import com.financialLab.financedevapp.models.User;
import com.financialLab.financedevapp.repository.ExpensesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ExpensesService extends SimpleCrudService<Expenses, ExpensesRepository>{

    @Autowired
    private RevenueService revenueService;

    public ExpensesService(ExpensesRepository repository) {
        super(repository);
    }

    @Override
    protected void updateData(Expenses existingObject, Expenses updatedObject) {
        if (updatedObject.getAmount() != null) {
            existingObject.setAmount(updatedObject.getAmount());
        }

    }

    @Transactional
    public Expenses create(ExpensesDTO expensesDTO, User loggedUser){
        try {
            Expenses expenses = ExpensesDTO.toModel(expensesDTO);
            expenses.setFinance(loggedUser.getFinance());
            Expenses savedExpense = this.save(expenses);
            revenueService.calculateSaving(loggedUser);
            return savedExpense;
        }catch (Exception e) {
            throw FinancialAppException.serverException("Error creando el gasto " + e.getMessage(), true);
        }
    }

    @Transactional
    public List<Expenses> createAll(List<ExpensesDTO> expensesDTOS, User loggedUser) {
        try {
            List<Expenses> castList = expensesDTOS.stream().map(ExpensesDTO::toModel).toList();
            castList.forEach(expenses -> expenses.setFinance(loggedUser.getFinance()));

            Iterable<Expenses> response = this.saveAll(castList);
            List<Expenses> result = new ArrayList<>();
            response.forEach(result::add);
            revenueService.calculateSaving(loggedUser);

            return result;
        }catch (Exception e) {
            throw FinancialAppException.serverException("Error creando los gastos " + e.getMessage(), true);
        }
    }
}
