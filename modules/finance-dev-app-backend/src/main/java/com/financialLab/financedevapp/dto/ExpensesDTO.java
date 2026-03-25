package com.financialLab.financedevapp.dto;

import com.financialLab.financedevapp.models.Expenses;
import com.financialLab.financedevapp.types.ExpenseType;
import com.financialLab.financedevapp.types.RevenueType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ExpensesDTO extends GenericDTO {
    private String description;
    private Long amount;
    private String financeExternalId;
    private ExpenseType type;

    public static ExpensesDTO of(Expenses model) {
        ExpensesDTO dto = new ExpensesDTO();
        dto.setExternalId(model.getExternalId());
        if (!ExpenseType.OTHERS.equals(model.getType())) {
            dto.setDescription(model.getType().getValue());
        } else {
            dto.setDescription(model.getDescription());
        }
        dto.setType(model.getType());
        dto.setAmount(model.getAmount());
        if(model.getFinance() != null) {
            dto.setFinanceExternalId(model.getFinance().getExternalId());
        }

        return dto;
    }

    public static Expenses toModel(ExpensesDTO expensesDTO) {
        Expenses expenses = new Expenses();
        expenses.setAmount(expensesDTO.getAmount());
        expenses.setDescription(expensesDTO.getDescription());
        expenses.setExternalId(expensesDTO.getExternalId());
        expenses.setType(expensesDTO.getType());
        return expenses;
    }
}
