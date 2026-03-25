package com.financialLab.financedevapp.types;

import lombok.Getter;

import java.util.Arrays;
import java.util.Locale;

@Getter
public enum AssetType {

    BANK_ACCOUNTS("Cuentas Bancarias"){
        @Override
        public boolean isThisType(String value) {
            return value.equals(this.getValue().toLowerCase());
        }
    },
    DWELLING("Vivienda"){
        @Override
        public boolean isThisType(String value) {
            return value.equals(this.getValue().toLowerCase());
        }
    },
    ESTATE("Finca"){
        @Override
        public boolean isThisType(String value) {
            return value.equals(this.getValue().toLowerCase());
        }
    },
    LOT("Lote"){
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
    FURNITURE("Muebles"){
        @Override
        public boolean isThisType(String value) {
            return value.equals(this.getValue().toLowerCase());
        }
    },
    ELECTRONICS("Electrónicos"){
        @Override
        public boolean isThisType(String value) {
            return value.equals(this.getValue().toLowerCase());
        }
    },
    ART("Arte"){
        @Override
        public boolean isThisType(String value) {
            return value.equals(this.getValue().toLowerCase());
        }
    },
    INSURANCE("Seguros"){
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
    AssetType(String value) {
        this.value = value;
    }

    public abstract boolean isThisType(String value);

    public static AssetType getAssetByValue(String value){
        if (value== null) {
            return null;
        }
        return Arrays.stream(AssetType.values()).filter(assetType -> assetType.isThisType(value.toLowerCase(Locale.ROOT))).findFirst().orElse(null);
    }
}
