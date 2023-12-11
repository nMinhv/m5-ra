package com.ra.model.repository;

import java.util.List;

public interface IGenericRepository <T,ID>{
    List<T> findAll();

    T saveOrUpdate( T t);

    Boolean create ( T t);

    T findById(ID id);

    void delete(ID id);
}
