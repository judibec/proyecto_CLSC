package com.financialLab.financedevapp.services;

import com.financialLab.financedevapp.dto.FinanceDTO;
import com.financialLab.financedevapp.dto.UserDTO;
import com.financialLab.financedevapp.models.Finance;
import com.financialLab.financedevapp.models.User;
import com.financialLab.financedevapp.repository.FinanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FinanceService extends SimpleCrudService<Finance, FinanceRepository> {

    @Autowired
    private UserService userService;

    public FinanceService(FinanceRepository repository) {
        super(repository);
    }

    @Override
    protected void updateData(Finance existingObject, Finance updatedObject) {
    }

    public List<FinanceDTO> getAllFinances() {
        List<Finance> finances = this.findAll();
        return finances.stream().map(FinanceDTO::of).collect(Collectors.toList());
    }


    @Transactional
    public Finance create(User user) {
        Finance finance = this.save(new Finance());
        if(user.getFinance() == null){
            user.setFinance(finance);
            this.userService.save(user);
        }
        return finance;
    }
}
