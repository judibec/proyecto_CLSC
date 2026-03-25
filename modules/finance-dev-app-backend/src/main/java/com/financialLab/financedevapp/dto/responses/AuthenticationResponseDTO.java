package com.financialLab.financedevapp.dto.responses;

import com.financialLab.financedevapp.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponseDTO {

    private String token;
    private Date expiresAt;
    private UserDTO user;

}
