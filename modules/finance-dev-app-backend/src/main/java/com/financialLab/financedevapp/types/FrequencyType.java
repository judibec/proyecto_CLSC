package com.financialLab.financedevapp.types;

import lombok.Getter;

import java.util.Arrays;
import java.util.Locale;

@Getter
public enum FrequencyType {
    MONTHLY("Mensual"){
        @Override
        public boolean isThisType(String value) {
            return value.equals(this.getValue().toLowerCase());
        }
    },
    TOTAL("Total"){
        @Override
        public boolean isThisType(String value) {
            return value.equals(this.getValue().toLowerCase());
        }
    };

    private final String value;
    FrequencyType(String value) {
        this.value = value;
    }

    public abstract boolean isThisType(String value);

    public static FrequencyType getFrequencyByValue(String value){
        if (value== null) {
            return null;
        }
        return Arrays.stream(FrequencyType.values()).filter(frequencyType -> frequencyType.isThisType(value.toLowerCase(Locale.ROOT))).findFirst().orElse(null);
    }
}
