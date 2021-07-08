package org.example.domain.usecases.utils;

import java.util.List;
import java.util.Optional;

public interface DAO<K,T> {
    K insert(T type);
    boolean update(K key,T type);
    boolean delete(K key);
    Optional<T> findOne(K key);
    List<T> fetchAll();
}
