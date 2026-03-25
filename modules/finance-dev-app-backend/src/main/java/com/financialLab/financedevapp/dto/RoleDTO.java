package com.financialLab.financedevapp.dto;

import com.financialLab.financedevapp.models.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RoleDTO extends GenericDTO{
    private String nameRol;


    public static RoleDTO of(Role model) {
        RoleDTO dto = new RoleDTO();
        dto.setExternalId(model.getExternalId());
        dto.setNameRol(model.getNameRol());
        return dto;
    }

    public static Role toModel(RoleDTO dto){
        Role model = new Role();
        model.setExternalId(dto.getExternalId());
        model.setNameRol(dto.getNameRol());

        return model;
    }
}
