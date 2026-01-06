package com.zharfna.zharfna.service.base.crud;

import com.zharfna.zharfna.entity.base.BaseEntity;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Validator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public class CrudServiceImpl<T extends BaseEntity<ID>,
        ID extends Serializable,
        R extends JpaRepository<T, ID>>
        implements CrudService<T, ID> {

    protected R repository;
    protected Validator validator;

    public CrudServiceImpl(R repository, Validator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    @Override
    public T save(T entity) {
        repository.save(entity);
        return entity;
    }

    @Override
    public void deleteById(ID id) {
        repository.deleteById(id);
    }

    @Override
    public T findById(ID id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("id: " + id));
    }
}
