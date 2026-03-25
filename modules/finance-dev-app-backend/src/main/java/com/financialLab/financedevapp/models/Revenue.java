package com.financialLab.financedevapp.models;

import com.financialLab.financedevapp.types.RevenueType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Revenue extends GenericModel<Revenue>{

    private String description;
    private Long amount;
    @ManyToOne
    @JoinColumn(name = "finance_id")
    private Finance finance;
    @Enumerated(EnumType.STRING)
    private RevenueType type;
}
