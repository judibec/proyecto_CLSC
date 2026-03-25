package com.financialLab.financedevapp.types;

import lombok.Getter;

import java.util.Arrays;
import java.util.Locale;

@Getter
public enum ConceptType {

    INVESTMENT("Inversiones"){
        @Override
        public boolean isThisType(String value) {
            return value.equals(this.getValue().toLowerCase());
        }
    },
    PASSIVE_INCOME("Ingreso Pasivo"){
        @Override
        public boolean isThisType(String value) {
            return value.equals(this.getValue().toLowerCase());
        }
    };

    private final String value;
    ConceptType(String value) {
        this.value = value;
    }

    public abstract boolean isThisType(String value);

    public static ConceptType getConceptByValue(String value){
        if (value== null) {
            return null;
        }
        return Arrays.stream(ConceptType.values()).filter(conceptType -> conceptType.isThisType(value.toLowerCase(Locale.ROOT))).findFirst().orElse(null);
    }
}
