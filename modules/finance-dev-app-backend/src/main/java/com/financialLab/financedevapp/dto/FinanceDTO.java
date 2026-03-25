package com.financialLab.financedevapp.dto;

import com.financialLab.financedevapp.models.Finance;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class FinanceDTO extends GenericDTO{

    private List<RevenueDTO> revenues;
    private List<ExpensesDTO> expenses;
    private List<InvestmentDTO> investments;
    private List<DebtDTO> debts;
    private List<AssetDTO> assets;


    public static FinanceDTO of(Finance model) {
        FinanceDTO dto = new FinanceDTO();
        dto.setExternalId(model.getExternalId());
        if(model.getExpenses() != null) {
            dto.setExpenses(model.getExpenses().stream().map(ExpensesDTO::of).toList());
        }
        if(model.getRevenues() != null) {
            dto.setRevenues(model.getRevenues().stream().map(RevenueDTO::of).toList());
        }
        if(model.getInvestments() != null) {
            dto.setInvestments(model.getInvestments().stream().map(InvestmentDTO::of).toList());
        }
        if(model.getDebts() != null) {
            dto.setDebts(model.getDebts().stream().map(DebtDTO::of).toList());
        }
        if(model.getAssets() != null) {
            dto.setAssets(model.getAssets().stream().map(AssetDTO::of).toList());
        }
        return dto;
    }



    public static Finance toModel(FinanceDTO financeDTO) {
        Finance finance = new Finance();
        finance.setExternalId(financeDTO.getExternalId());
        finance.setRevenues(financeDTO.getRevenues().stream().map(RevenueDTO::toModel).toList());
        finance.setExpenses(financeDTO.getExpenses().stream().map(ExpensesDTO::toModel).toList());
        finance.setInvestments(financeDTO.getInvestments().stream().map(InvestmentDTO::toModel).toList());
        finance.setDebts(financeDTO.getDebts().stream().map(DebtDTO::toModel).toList());
        finance.setAssets(financeDTO.getAssets().stream().map(AssetDTO::toModel).toList());


        return finance;
    }
}
