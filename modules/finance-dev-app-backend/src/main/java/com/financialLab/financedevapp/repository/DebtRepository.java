package com.financialLab.financedevapp.repository;

import com.financialLab.financedevapp.models.Debt;
import org.springframework.stereotype.Repository;

@Repository
public interface DebtRepository extends SimpleCrudRepository<Debt, Long> {
}
