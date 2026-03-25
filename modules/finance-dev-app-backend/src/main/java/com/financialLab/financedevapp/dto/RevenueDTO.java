package com.financialLab.financedevapp.dto;

import com.financialLab.financedevapp.models.Revenue;
import com.financialLab.financedevapp.types.RevenueType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RevenueDTO extends GenericDTO{
    private String description;
    private Long amount;
    private String financeExternalId;
    private RevenueType type;

    public static RevenueDTO of(Revenue model) {
        RevenueDTO dto = new RevenueDTO();

        dto.setExternalId(model.getExternalId());
        dto.setAmount(model.getAmount());
        dto.setType(model.getType());
        if (!RevenueType.OTHERS.equals(model.getType())) {
            dto.setDescription(model.getType().getValue());
        } else {
            dto.setDescription(model.getDescription());
        }
        if(model.getFinance() != null) {
            dto.setFinanceExternalId(model.getFinance().getExternalId());
        }
        return dto;
    }

    public static Revenue toModel(RevenueDTO dto) {
        Revenue model = new Revenue();
        model.setDescription(dto.getDescription());
        model.setAmount(dto.getAmount());
        model.setExternalId(dto.getExternalId());
        model.setType(dto.getType());

        return model;
    }
}
