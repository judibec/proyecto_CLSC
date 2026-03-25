package com.financialLab.financedevapp.configuration;

import com.financialLab.financedevapp.dto.requests.NewUserRequestDTO;
import com.financialLab.financedevapp.models.Role;
import com.financialLab.financedevapp.models.User;
import com.financialLab.financedevapp.services.RoleService;
import com.financialLab.financedevapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Service
public class ObjectDrawer {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private static PasswordEncoder passwordEncoder;

    public void ensureAdminUser() {
        if (userService.exitsUserBYEmail("admin@example.com")) {
            return;
        }

        NewUserRequestDTO dto = new NewUserRequestDTO();
        dto.setName("Admin");
        dto.setLastname("Admin");
        dto.setEmail("admin@example.com");
        dto.setPassword("root");
        dto.setPhone("0001112222");
        dto.setBirthday(LocalDate.of(2002, 1, 24));

        Set<Role> defaultRoles = new HashSet<>();
        defaultRoles.add(roleService.getRoleByName("USER"));
        defaultRoles.add(roleService.getRoleByName("ADMIN"));
        User admin = userService.create(dto, defaultRoles);

        userService.save(admin);
    }

    public void ensureCommonUser() {
        if (userService.exitsUserBYEmail("user@example.com")) {
            return;
        }

        NewUserRequestDTO dto = new NewUserRequestDTO();
        dto.setName("User");
        dto.setLastname("Common");
        dto.setEmail("user@example.com");
        dto.setPassword("root");
        dto.setPhone("0001112223");
        dto.setBirthday(LocalDate.of(2002, 1, 24));

        Set<Role> defaultRoles = new HashSet<>();
        defaultRoles.add(roleService.getRoleByName("USER"));
        User admin = userService.create(dto, defaultRoles);

        userService.save(admin);
    }
}
