package com.financialLab.financedevapp.types;

import lombok.Getter;

import java.util.Arrays;
import java.util.Locale;

@Getter
public enum ExpenseType {

    ACCOMMODATION("Alojamiento"){
        @Override
        public boolean isThisType(String value) {
            return value.equals(this.getValue().toLowerCase());
        }
    },
    SERVICES("Servicios"){
        @Override
        public boolean isThisType(String value) {
            return value.equals(this.getValue().toLowerCase());
        }
    },
    FOOD("Alimentos"){
        @Override
        public boolean isThisType(String value) {
            return value.equals(this.getValue().toLowerCase());
        }
    },
    VEHICLE("Vehículo"){
        @Override
        public boolean isThisType(String value) {
            return value.equals(this.getValue().toLowerCase());
        }
    },
    ENTERTAINMENT("Entretenimiento"){
        @Override
        public boolean isThisType(String value) {
            return value.equals(this.getValue().toLowerCase());
        }
    },
    TRAVEL("Viajes"){
        @Override
        public boolean isThisType(String value) {
            return value.equals(this.getValue().toLowerCase());
        }
    },
    PERSONAL("Personales"){
        @Override
        public boolean isThisType(String value) {
            return value.equals(this.getValue().toLowerCase());
        }
    },
    CHILDREN("Hijos"){
        @Override
        public boolean isThisType(String value) {
            return value.equals(this.getValue().toLowerCase());
        }
    },
    INSURANCE_TAXES("Seguros e impuestos"){
        @Override
        public boolean isThisType(String value) {
            return value.equals(this.getValue().toLowerCase());
        }
    },
    DONATIONS("Donaciones"){
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
    };

    private final String value;
    ExpenseType(String value) {
        this.value = value;
    }

    public abstract boolean isThisType(String value);

    public static ExpenseType getExpenseByValue(String value){
        if (value== null) {
            return null;
        }
        return Arrays.stream(ExpenseType.values()).filter(expenseType -> expenseType.isThisType(value.toLowerCase(Locale.ROOT))).findFirst().orElse(null);
    }

}
