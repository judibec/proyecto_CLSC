package com.financialLab.financedevapp.dto;

import com.financialLab.financedevapp.models.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO extends GenericDTO {
    private String name;
    private String lastname;
    private String email;
    private String phone;
    private LocalDate birthday;
    private FinanceDTO financeDTO;
    private String goal;
    private String points;
    //TODO: Add user roles

    public static UserDTO of(User model) {
        UserDTO dto = new UserDTO();

        dto.setExternalId(model.getExternalId());
        dto.setName(model.getName());
        dto.setLastname(model.getLastName());
        dto.setEmail(model.getEmail());
        dto.setPhone(model.getPhone());
        dto.setBirthday(model.getBirthday());
        if(model.getFinance() != null) {
            dto.setFinanceDTO(FinanceDTO.of(model.getFinance()));
        }
        dto.setPoints(model.getPoints());
        dto.setGoal(model.getGoal());

        return dto;
    }

    public static User toModel(UserDTO dto) {
        User model = new User();

        model.setName(dto.getName());
        model.setLastName(dto.getLastname());
        model.setEmail(dto.getEmail());
        model.setPhone(dto.getPhone());
        model.setBirthday(dto.getBirthday());
        model.setExternalId(dto.getExternalId());
        model.setGoal(dto.getGoal());
        model.setPoints(dto.getPoints());

        return model;
    }

}
