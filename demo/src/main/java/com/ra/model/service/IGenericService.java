package com.ra.model.service;

import java.util.List;

public interface IGenericService <T,ID>{
    List<T> findAll();

    T saveOrUpdate( T t);

    T findById(ID id);

    void delete(ID id);
    Boolean create ( T t);
}
