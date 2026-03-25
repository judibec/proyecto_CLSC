package com.financialLab.financedevapp.dto.requests;

import com.financialLab.financedevapp.models.Role;
import com.financialLab.financedevapp.models.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class NewUserRequestDTO {

    private String name;
    private String lastname;
    private String email;
    private String phone;
    private LocalDate birthday;
    private String password;
    private String goal;

    public static User toModel(NewUserRequestDTO dto, Set<Role> defaultRoles) {
        User model = new User();

        model.setName(dto.getName());
        model.setLastName(dto.getLastname());
        model.setEmail(dto.getEmail());
        model.setPhone(dto.getPhone());
        model.setBirthday(dto.getBirthday());
        model.setPassword(dto.getPassword());
        model.setGoal(dto.getGoal());

        model.setUserRoles(defaultRoles);

        return model;
    }

}
