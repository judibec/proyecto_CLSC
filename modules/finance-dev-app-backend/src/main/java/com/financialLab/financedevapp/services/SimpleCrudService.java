package com.financialLab.financedevapp.services;

import com.financialLab.financedevapp.dto.FinanceDTO;
import com.financialLab.financedevapp.exception.FinancialAppException;
import com.financialLab.financedevapp.models.Finance;
import com.financialLab.financedevapp.models.GenericModel;
import com.financialLab.financedevapp.models.User;
import com.financialLab.financedevapp.repository.SimpleCrudRepository;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public abstract class SimpleCrudService <Model extends GenericModel<Model>, Repository extends SimpleCrudRepository<Model, Long>> {

    protected Repository repository;

    public SimpleCrudService(Repository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<Model> findAll() {
        Iterable<Model> result = this.repository.findAll();
        return StreamSupport.stream(result.spliterator(), false).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Model getById(long id) {
        return repository.findById(id).orElseThrow();
    }

    @Transactional
    public Model getByExternalId(String externalId) {
        return repository.findByExternalId(externalId).orElseThrow(() -> FinancialAppException.objectNotFound("Entidad no encontrada"));
    }

    @Transactional
    public Model save(Model model) {
        enrichObject(model);
        this.repository.save(model);
        return model;
    }

    @Transactional()
    public Model update(Long id, Model updatedObject) {
        Model object = this.getById(id);

        this.updateData(object, updatedObject);
        this.enrichObject(object);

        this.repository.save(object);

        return object;
    }

    @Transactional()
    public Model updateByExternalId(String externalId, Model updatedObject) {
        Model object = this.getByExternalId(externalId);

        this.updateData(object, updatedObject);
        this.enrichObject(object);

        this.repository.save(object);

        return object;
    }

    public void enrichObject(Model model) {
        if (model != null) {
            (model).ensureExternalId();
        }
    }

    /**
     * This method specifies how a model object should be updated from a DTO It should return the object with its data
     * updated
     *
     * @param existingObject model object to be updated
     * @param updatedObject  updated object that has the information to be updated
     * @return updated model object
     */
    protected abstract void updateData(Model existingObject, Model updatedObject);

    @Transactional
    public void delete(long id) {
        repository.deleteById(id);
    }

    @Transactional
    public Model deleteByExternalId(String externalId) {
        Model model = getByExternalId(externalId);
        delete(model);
        return model;
    }

    @Transactional
    public void delete(Model model) {
        repository.delete(model);
    }

    public Iterable<Model> saveAll(Iterable<Model> missingObjects) {
        missingObjects.forEach(this::enrichObject);
        return repository.saveAll(missingObjects);
    }
}
