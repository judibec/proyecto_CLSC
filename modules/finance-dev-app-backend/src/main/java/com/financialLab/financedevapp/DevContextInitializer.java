package com.financialLab.financedevapp;

import com.financialLab.financedevapp.configuration.ObjectDrawer;
import com.financialLab.financedevapp.models.User;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Profile("dev")
public class DevContextInitializer {

    @Autowired
    private ObjectDrawer objectDrawer;

    @PostConstruct
    @Transactional
    public void initializeDBForDev() {
        // Create a Admin User
        objectDrawer.ensureAdminUser();

        // Create a Simple User
        objectDrawer.ensureCommonUser();
    }
}
