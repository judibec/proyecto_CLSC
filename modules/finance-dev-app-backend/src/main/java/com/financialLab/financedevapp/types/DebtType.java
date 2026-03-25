package com.financialLab.financedevapp.types;

import lombok.Getter;

import java.util.Arrays;
import java.util.Locale;

@Getter
public enum DebtType {

    MORTGAGE("Hipoteca"){
        @Override
        public boolean isThisType(String value) {
            return value.equals(this.getValue().toLowerCase());
        }
    },
    VEHICLE_CREDIT("Crédito vehículo"){
        @Override
        public boolean isThisType(String value) {
            return value.equals(this.getValue().toLowerCase());
        }
    },
    EDUCATIONAL_CREDIT("Crédito educativo"){
        @Override
        public boolean isThisType(String value) {
            return value.equals(this.getValue().toLowerCase());
        }
    },
    FREE_INVESTMENT_CREDIT("Crédito libre inversion"){
        @Override
        public boolean isThisType(String value) {
            return value.equals(this.getValue().toLowerCase());
        }
    },
    CREDIT_CARD("Tarjeta de crédito"){
        @Override
        public boolean isThisType(String value) {
            return value.equals(this.getValue().toLowerCase());
        }
    },
    REVOLVING_CREDIT("Crédito rotativo"){
        @Override
        public boolean isThisType(String value) {
            return value.equals(this.getValue().toLowerCase());
        }
    },
    FAMILY_CREDIT("Crédito familiar"){
        @Override
        public boolean isThisType(String value) {
            return value.equals(this.getValue().toLowerCase());
        }
    },
    PAYROLL_CREDIT("Crédito de nomina"){
        @Override
        public boolean isThisType(String value) {
            return value.equals(this.getValue().toLowerCase());
        }
    },
    OTHERS("Otros"){
        @Override
        public boolean isThisType(String value) {
            return value.equals(this.getValue().toLowerCase());
        }
    },
    NA("No tengo"){
        @Override
        public boolean isThisType(String value) {
            return value.equals(this.getValue().toLowerCase());
        }
    };

    private final String value;
    DebtType(String value){
        this.value = value;
    }

    public abstract boolean isThisType(String value);

    public static DebtType getDebtByTerm(String value){
        if (value == null){
            return null;
        }
        return Arrays.stream(DebtType.values()).filter(debtType -> debtType.isThisType(value.toLowerCase(Locale.ROOT))).findFirst().orElse(null);
    }
}
