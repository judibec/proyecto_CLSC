package com.financialLab.financedevapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.financialLab.financedevapp.models")
@EnableJpaRepositories(basePackages = "com.financialLab.financedevapp.repository")
@ComponentScan
public class FinanceDevAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(FinanceDevAppApplication.class, args);
    }
}