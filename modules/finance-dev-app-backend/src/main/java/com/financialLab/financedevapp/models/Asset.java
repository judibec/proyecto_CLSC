package com.financialLab.financedevapp.models;

import com.financialLab.financedevapp.types.AssetType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Asset extends GenericModel<Asset> {
    private String description;
    private Long amount;
    @ManyToOne
    @JoinColumn(name = "finance_id")
    private Finance finance;
    @Enumerated(EnumType.STRING)
    private AssetType type;

}
