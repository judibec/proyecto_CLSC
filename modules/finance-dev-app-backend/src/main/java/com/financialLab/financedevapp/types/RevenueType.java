package com.financialLab.financedevapp.types;

import lombok.Getter;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Locale;

@Getter
public enum RevenueType {
    SALARY("Salario") {
        @Override
        public boolean isThisType(String value) {
            return value.equals(this.getValue().toLowerCase());
        }
    },
    SAVINGS("Ahorros"){
        @Override
        public boolean isThisType(String value) {
            return value.equals(this.getValue().toLowerCase());
        }
    },
    HONORARIUM("Honorarios"){
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
    SALES("Ventas"){
        @Override
        public boolean isThisType(String value) {
            return value.equals(this.getValue().toLowerCase());
        }
    },
    COMMISSIONS("Comisiones"){
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
    RevenueType(String value) {
        this.value = value;
    }

    public abstract boolean isThisType(String value);

    public static RevenueType getRevenueByValue(String value){
        if (value== null) {
            return null;
        }
        return Arrays.stream(RevenueType.values()).filter(revenueType -> revenueType.isThisType(value.toLowerCase(Locale.ROOT))).findFirst().orElse(null);
    }

}
