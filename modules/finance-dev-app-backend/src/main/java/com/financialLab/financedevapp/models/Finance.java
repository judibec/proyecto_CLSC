package com.financialLab.financedevapp.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Finance extends GenericModel<Finance>{

    @OneToMany(mappedBy = "finance", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Revenue> revenues;
    @OneToMany(mappedBy = "finance", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Expenses> expenses;
    @OneToMany(mappedBy = "finance", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Investment> investments;
    @OneToMany(mappedBy = "finance", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Debt> debts;
    @OneToMany(mappedBy = "finance", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Asset> assets;
}
