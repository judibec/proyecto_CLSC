package com.financialLab.financedevapp.services;

import com.financialLab.financedevapp.dto.DebtDTO;
import com.financialLab.financedevapp.exception.FinancialAppException;
import com.financialLab.financedevapp.models.Debt;
import com.financialLab.financedevapp.models.User;
import com.financialLab.financedevapp.repository.DebtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class DebtService extends SimpleCrudService<Debt, DebtRepository> {

    @Autowired
    private RevenueService revenueService;

    public DebtService(DebtRepository repository) {
        super(repository);
    }

    @Override
    protected void updateData(Debt existingObject, Debt updatedObject) {
        if (updatedObject.getAmount() != null) {
            existingObject.setAmount(updatedObject.getAmount());
        }
    }

    @Transactional
    public Debt create(DebtDTO debtDTO, User loggedUser){
        try{
            Debt debt = DebtDTO.toModel(debtDTO);
            debt.setFinance(loggedUser.getFinance());
            Debt savedDebt = this.save(debt);
            revenueService.calculateSaving(loggedUser);
            return savedDebt;
        }catch (Exception e) {
            throw FinancialAppException.serverException("Error creando la deuda " + e.getMessage(), true);
        }
    }

    @Transactional
    public List<Debt> createAll(List<DebtDTO> debtDTOS, User loggedUser) {
        try{
            List<Debt> castList = debtDTOS.stream().map(DebtDTO::toModel).toList();
            castList.forEach(debt -> debt.setFinance(loggedUser.getFinance()));

            Iterable<Debt> response = this.saveAll(castList);
            List<Debt> result = new ArrayList<>();
            response.forEach(result::add);
            revenueService.calculateSaving(loggedUser);

            return result;
        }catch (Exception e) {
            throw FinancialAppException.serverException("Error creando las deudas " + e.getMessage(), true);
        }
    }
}
