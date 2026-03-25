package com.financialLab.financedevapp.repository;

import com.financialLab.financedevapp.models.Expenses;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpensesRepository extends SimpleCrudRepository<Expenses, Long>{
}
