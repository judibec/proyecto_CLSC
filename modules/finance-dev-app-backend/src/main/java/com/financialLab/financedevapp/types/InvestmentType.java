package com.financialLab.financedevapp.types;

import lombok.Getter;

import java.util.Arrays;
import java.util.Locale;

@Getter
public enum InvestmentType {

    OWN_BUSINESS("Negocio Propio"){
        @Override
        public boolean isThisType(String value) {
            return value.equals(this.getValue().toLowerCase());
        }
    },
    REAL_ESTATE("Bienes Raíces"){
        @Override
        public boolean isThisType(String value) {
            return value.equals(this.getValue().toLowerCase());
        }
    },
    FINANCIAL_PRODUCTS("Productos Financieros"){
        @Override
        public boolean isThisType(String value) {
            return value.equals(this.getValue().toLowerCase());
        }
    },
    CRYPTO_ASSETS("Criptoactivos"){
        @Override
        public boolean isThisType(String value) {
            return value.equals(this.getValue().toLowerCase());
        }
    },
    FINANCIAL_STOCKS("Acciones"){
        @Override
        public boolean isThisType(String value) {
            return value.equals(this.getValue().toLowerCase());
        }
    },
    METALS("Metales"){
        @Override
        public boolean isThisType(String value) {
            return value.equals(this.getValue().toLowerCase());
        }
    },
    BUSINESS("Negocios"){
        @Override
        public boolean isThisType(String value) {
            return value.equals(this.getValue().toLowerCase());
        }
    },

    // AQUI EMPIEZAN LOS INGRESOS PASIVOS
    FINANCIAL_RETURNS("Rendimientos Financieros"){
        @Override
        public boolean isThisType(String value) {
            return value.equals(this.getValue().toLowerCase());
        }
    },
    LEASES("Arrendamientos"){
        @Override
        public boolean isThisType(String value) {
            return value.equals(this.getValue().toLowerCase());
        }
    },
    INTERESTS("Intereses"){
        @Override
        public boolean isThisType(String value) {
            return value.equals(this.getValue().toLowerCase());
        }
    },
    DIVIDENDS("Dividendos"){
        @Override
        public boolean isThisType(String value) {
            return value.equals(this.getValue().toLowerCase());
        }
    },
    ROYALTIES("Regalías"){
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
    DIGITAL_MONETIZATION("Monetización Digital"){
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
    InvestmentType(String value) {
        this.value = value;
    }

    public abstract boolean isThisType(String value);

    public static InvestmentType getInvestmentByValue(String value){
        if (value== null) {
            return null;
        }
        return Arrays.stream(InvestmentType.values()).filter(investmentType -> investmentType.isThisType(value.toLowerCase(Locale.ROOT))).findFirst().orElse(null);
    }
}
