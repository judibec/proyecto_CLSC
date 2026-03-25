package com.financialLab.financedevapp.dto.requests;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class AuthRequestDTO {
    @NonNull
    private String email;
    @NonNull
    private String password;
}
