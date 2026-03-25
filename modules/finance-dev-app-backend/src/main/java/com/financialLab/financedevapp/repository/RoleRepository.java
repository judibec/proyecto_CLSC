package com.financialLab.financedevapp.repository;

import com.financialLab.financedevapp.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends SimpleCrudRepository<Role, Long> {
    Optional<Role> findByNameRol(String nameRol);
}
