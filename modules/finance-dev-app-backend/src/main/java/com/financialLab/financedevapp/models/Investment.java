package com.financialLab.financedevapp.models;

import com.financialLab.financedevapp.types.ConceptType;
import com.financialLab.financedevapp.types.InvestmentType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Investment extends GenericModel<Investment>{

    private String description;
    private Long amount;
    @ManyToOne
    @JoinColumn(name = "finance_id")
    private Finance finance;
    @Enumerated(EnumType.STRING)
    private InvestmentType type;
    @Enumerated(EnumType.STRING)
    private ConceptType concept;
}
