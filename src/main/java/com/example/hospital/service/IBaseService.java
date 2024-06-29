package com.example.hospital.service;

import java.util.List;

public interface IBaseService<T> {
    T add(T entity);
    T update(T entity);
    boolean delete(long id);
    T findById(long id);
    List<T> findAll();

}
