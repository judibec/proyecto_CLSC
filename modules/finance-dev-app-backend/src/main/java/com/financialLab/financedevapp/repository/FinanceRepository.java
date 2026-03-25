package com.financialLab.financedevapp.repository;

import com.financialLab.financedevapp.models.Finance;
import org.springframework.stereotype.Repository;

@Repository
public interface FinanceRepository extends SimpleCrudRepository<Finance, Long>{
}
