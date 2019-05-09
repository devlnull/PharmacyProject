package com.pharmacy.repo;

import com.pharmacy.entities.Entity;

import java.util.List;
import java.util.function.Predicate;

public interface IRepo<T extends Entity> {
    List<T> GetAll();
    List<T> GetAll(Predicate<T> predicate);
    T Get(String id);
    T Get(Predicate<T> predicate);
    T Add(T item);
    T Delete(T item);
    T Delete(String id);
    T Update(T item);
}
