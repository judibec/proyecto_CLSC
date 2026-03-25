package com.financialLab.financedevapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface SimpleCrudRepository<T, ID> extends CrudRepository<T, ID> {

    @Transactional(readOnly = true)
    Optional<T> findByExternalId(String externalId);

    @Transactional(readOnly = true)
    List<T> findByExternalIdIn(List<String> externalIds);
}
