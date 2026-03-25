package com.financialLab.financedevapp.dto;

import com.financialLab.financedevapp.models.Asset;
import com.financialLab.financedevapp.types.AssetType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AssetDTO extends GenericDTO{
    private String description;
    private Long amount;
    private String financeExternalId;
    private AssetType type;

    public static AssetDTO of(Asset model){
        AssetDTO dto = new AssetDTO();
        dto.setExternalId(model.getExternalId());
        dto.setAmount(model.getAmount());
        dto.setType(model.getType());
        if (!AssetType.OTHERS.equals(model.getType())){
            dto.setDescription(model.getType().getValue());
        }else{
            dto.setDescription(model.getDescription());
        }
        if(model.getFinance() != null) {
            dto.setFinanceExternalId(model.getFinance().getExternalId());
        }
        return dto;
    }

    public static Asset toModel(AssetDTO dto){
        Asset model = new Asset();
        model.setExternalId(dto.getExternalId());
        model.setDescription(dto.getDescription());
        model.setAmount(dto.getAmount());
        model.setType(dto.getType());
        return model;
    }
}
