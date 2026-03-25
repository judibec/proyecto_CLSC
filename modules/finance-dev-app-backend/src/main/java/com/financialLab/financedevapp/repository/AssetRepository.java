package com.financialLab.financedevapp.repository;

import com.financialLab.financedevapp.models.Asset;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetRepository extends SimpleCrudRepository<Asset, Long> {
}
