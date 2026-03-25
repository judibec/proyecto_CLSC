package com.financialLab.financedevapp.services;

import com.financialLab.financedevapp.dto.AssetDTO;
import com.financialLab.financedevapp.exception.FinancialAppException;
import com.financialLab.financedevapp.models.Asset;
import com.financialLab.financedevapp.models.User;
import com.financialLab.financedevapp.repository.AssetRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class AssetService extends SimpleCrudService<Asset, AssetRepository> {

    public AssetService(AssetRepository repository) {
        super(repository);
    }

    @Override
    protected void updateData(Asset existingObject, Asset updatedObject) {
        if (updatedObject.getAmount() != null) {
            existingObject.setAmount(updatedObject.getAmount());
        }
    }

    @Transactional
    public Asset create(AssetDTO assetDTO, User loggedUser){
        try {
            Asset asset = AssetDTO.toModel(assetDTO);
            asset.setFinance(loggedUser.getFinance());
            return this.save(asset);
        }catch (Exception e){
            throw FinancialAppException.serverException("Error creando el bien " + e.getMessage(), true);
        }
    }

    @Transactional
    public List<Asset> createAll(List<AssetDTO> assetDTOS, User loggedUser) {
        try {
            List<Asset> castList = assetDTOS.stream().map(AssetDTO::toModel).toList();
            castList.forEach(asset -> asset.setFinance(loggedUser.getFinance()));

            Iterable<Asset> response = this.saveAll(castList);
            List<Asset> result = new ArrayList<>();
            response.forEach(result::add);

            return result;
        }catch (Exception e){
            throw FinancialAppException.serverException("Error creando los bienes " + e.getMessage(), true);
        }
    }
}
