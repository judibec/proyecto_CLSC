package com.financialLab.financedevapp.services;

import com.financialLab.financedevapp.dto.InvestmentDTO;
import com.financialLab.financedevapp.exception.FinancialAppException;
import com.financialLab.financedevapp.models.Finance;
import com.financialLab.financedevapp.models.Investment;
import com.financialLab.financedevapp.models.User;
import com.financialLab.financedevapp.repository.InvestmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class InvestmentService extends SimpleCrudService<Investment, InvestmentRepository> {

    @Autowired
    private FinanceService financeService;

    public InvestmentService(InvestmentRepository repository) {
        super(repository);
    }

    @Override
    protected void updateData(Investment existingObject, Investment updatedObject) {
        if (updatedObject.getAmount() != null) {
            existingObject.setAmount(updatedObject.getAmount());
        }


    }

    @Transactional
    public Investment create(InvestmentDTO investmentDTO, User loggedUser){
        try{
            Investment investment = InvestmentDTO.toModel(investmentDTO);
            investment.setFinance(loggedUser.getFinance());
            return this.save(investment);
        }catch (Exception e) {
            throw FinancialAppException.serverException("Error creando el inversion " + e.getMessage(), true);
        }
    }

    @Transactional
    public List<Investment> createAll(List<InvestmentDTO> investmentDTOS, User loggedUser) {
        try{
            List<Investment> castList = investmentDTOS.stream().map(InvestmentDTO::toModel).toList();
            castList.forEach(investment -> investment.setFinance(loggedUser.getFinance()));

            Iterable<Investment> response = this.saveAll(castList);
            List<Investment> result = new ArrayList<>();
            response.forEach(result::add);

            return result;
        }catch (Exception e) {
            throw FinancialAppException.serverException("Error creando las inversiones " + e.getMessage(), true);
        }
    }
}
