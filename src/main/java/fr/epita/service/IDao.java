package fr.epita.service;

import java.util.List;

public interface IDao<T> {
    void save(T t);
    T findById(int id);
    List<T> findAll();
    void update(T t);
    void delete(T t);
}