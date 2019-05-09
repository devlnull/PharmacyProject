package com.pharmacy.repo;

import com.pharmacy.entities.Entity;

import java.util.List;
import java.util.function.Predicate;

public interface IRepo<T extends Entity> {
    List<T> GetAll();
    List<T> GetAll(Predicate<T> predicate);
    T Get(String id);
    boolean Add(T item);
    boolean Delete(T item);
    boolean Delete(String id);
    boolean Update(T item);
}
