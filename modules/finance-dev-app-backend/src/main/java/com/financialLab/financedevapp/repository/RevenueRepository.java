package com.financialLab.financedevapp.repository;

import com.financialLab.financedevapp.models.Revenue;
import org.springframework.stereotype.Repository;

@Repository
public interface RevenueRepository extends SimpleCrudRepository<Revenue, Long>{
}
