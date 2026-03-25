package com.financialLab.financedevapp.services;

import com.financialLab.financedevapp.GlobalApplicationContext;
import com.financialLab.financedevapp.models.User;
import org.springframework.security.core.context.SecurityContextHolder;


public class SecurityService {

    public static User getLoggedUser(){
        //TODO: Research how to get all the user without the UserService
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        UserService userService = GlobalApplicationContext.getBean(UserService.class);
        return userService.getByEmail(email);
    }





}
