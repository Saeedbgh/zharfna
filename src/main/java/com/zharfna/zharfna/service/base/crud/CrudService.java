package com.zharfna.zharfna.service.base.crud;

import com.zharfna.zharfna.entity.base.BaseEntity;

import java.io.Serializable;

public interface CrudService<T extends BaseEntity<ID>, ID extends Serializable> {
    T save(T entity);

    void deleteById(ID id);

    T findById(ID id);
}
