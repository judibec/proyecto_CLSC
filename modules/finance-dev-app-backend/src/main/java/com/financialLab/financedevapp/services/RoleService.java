package com.financialLab.financedevapp.services;

import com.financialLab.financedevapp.dto.RoleDTO;
import com.financialLab.financedevapp.exception.FinancialAppException;
import com.financialLab.financedevapp.models.Role;
import com.financialLab.financedevapp.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleService extends SimpleCrudService<Role, RoleRepository>{
    @Autowired
    private RoleRepository roleRepository;

    public RoleService(RoleRepository repository) {
        super(repository);
    }

    public List<RoleDTO> getAllRoles() {
        List<Role> roles = this.findAll();
        return roles.stream().map(RoleDTO::of).collect(Collectors.toList());
    }

    public Role getUserRole() {
        Optional<Role> roleUser = this.repository.findByNameRol("USER");
        Role role = null;
        if(roleUser.isPresent()){
            role = roleUser.get();
        }

        return role;
    }

    public Role getRoleByName(String rolName) {
        Optional<Role> optRole = this.repository.findByNameRol(rolName);
        if (optRole.isEmpty()) {
            throw FinancialAppException.objectNotFound("Role ADMIN not found");
        }
        return optRole.get();
    }

    @Transactional
    public RoleDTO create(RoleDTO roleDTO) {
        Role role = this.save(RoleDTO.toModel(roleDTO));

        return RoleDTO.of(role);
    }

    @Override
    protected void updateData(Role existingObject, Role updatedObject) {

    }
}
