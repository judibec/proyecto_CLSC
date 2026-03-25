package com.financialLab.financedevapp.models;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
public abstract class GenericModel<T> {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @org.springframework.data.annotation.Id
    private Long id;
    @Column(unique = true, name = "external_id")
    private String externalId;

    public static String generateExternalId() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public void ensureExternalId() {
        setExternalId(externalId == null ? generateExternalId() : externalId);
    }
}
