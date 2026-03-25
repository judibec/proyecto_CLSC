package com.financialLab.financedevapp.models;

import com.financialLab.financedevapp.types.DebtType;
import com.financialLab.financedevapp.types.FrequencyType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Debt extends GenericModel<Debt>{
    private String description;
    private Long amount;
    @ManyToOne
    @JoinColumn(name = "finance_id")
    private Finance finance;
    @Enumerated(EnumType.STRING)
    private DebtType type;
    @Enumerated(EnumType.STRING)
    private FrequencyType frequency;
}
