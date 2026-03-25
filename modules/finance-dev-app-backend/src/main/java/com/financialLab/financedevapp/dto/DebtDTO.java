package com.financialLab.financedevapp.dto;

import com.financialLab.financedevapp.models.Debt;
import com.financialLab.financedevapp.models.GenericModel;
import com.financialLab.financedevapp.types.DebtType;
import com.financialLab.financedevapp.types.FrequencyType;
import com.financialLab.financedevapp.types.RevenueType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DebtDTO extends GenericDTO {
    private String description;
    private Long amount;
    private String financeExternalId;
    private DebtType type;
    private FrequencyType frequency;

    public static DebtDTO of(Debt model) {
        DebtDTO dto = new DebtDTO();

        dto.setExternalId(model.getExternalId());
        dto.setAmount(model.getAmount());
        dto.setType(model.getType());
        dto.setFrequency(model.getFrequency());
        if (!DebtType.OTHERS.equals(model.getType())) {
            dto.setDescription(model.getType().getValue());
        } else {
            dto.setDescription(model.getDescription());
        }
        if(model.getFinance() != null) {
            dto.setFinanceExternalId(model.getFinance().getExternalId());
        }
        return dto;
    }

    public static Debt toModel(DebtDTO dto){
        Debt model = new Debt();
        model.setDescription(dto.getDescription());
        model.setAmount(dto.getAmount());
        model.setExternalId(dto.getExternalId());
        model.setType(dto.getType());
        model.setFrequency(dto.getFrequency());

        return model;
    }
}
