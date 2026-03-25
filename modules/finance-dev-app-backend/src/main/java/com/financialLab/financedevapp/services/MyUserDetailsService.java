package com.financialLab.financedevapp.services;

import com.financialLab.financedevapp.models.Role;
import com.financialLab.financedevapp.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;  // Suponiendo que tienes un repositorio para usuarios

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Buscar el usuario en la base de datos
        User user = userService.getByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        // Extraer los nombres de los roles del usuario
        List<String> roleNames = user.getUserRoles().stream()
                .map(Role::getNameRol)
                .toList();

        //System.out.println("User roles: " + roleNames.get(0));
        // Convertir el usuario en un objeto de tipo UserDetails
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .build();
    }
}

