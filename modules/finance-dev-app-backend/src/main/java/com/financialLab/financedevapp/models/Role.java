package com.financialLab.financedevapp.models;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Entity;

@Setter
@Getter
@Entity
public class Role extends GenericModel<Role>{
    private String nameRol;

    @Override
    public String toString() {
        return "Role{nameRol='" + nameRol + "'}";
    }
}
