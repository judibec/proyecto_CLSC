package com.financialLab.financedevapp.dto;

import com.financialLab.financedevapp.models.Investment;
import com.financialLab.financedevapp.types.ConceptType;
import com.financialLab.financedevapp.types.InvestmentType;
import com.financialLab.financedevapp.types.RevenueType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InvestmentDTO extends GenericDTO {
    private String description;
    private Long amount;
    private String financeExternalId;
    private InvestmentType type;
    private ConceptType concept;

    public static InvestmentDTO of(Investment model) {
        InvestmentDTO dto = new InvestmentDTO();

        dto.setExternalId(model.getExternalId());
        dto.setAmount(model.getAmount());
        if (!InvestmentType.OTHERS.equals(model.getType())) {
            dto.setDescription(model.getType().getValue());
        } else {
            dto.setDescription(model.getDescription());
        }
        dto.setType(model.getType());
        dto.setConcept(model.getConcept());
        if(model.getFinance() != null) {
            dto.setFinanceExternalId(model.getFinance().getExternalId());
        }
        return dto;
    }

    public static Investment toModel(InvestmentDTO dto) {
        Investment model = new Investment();

        model.setDescription(dto.getDescription());
        model.setAmount(dto.getAmount());
        model.setExternalId(dto.getExternalId());
        model.setType(dto.getType());
        model.setConcept(dto.getConcept());

        return model;
    }
}
