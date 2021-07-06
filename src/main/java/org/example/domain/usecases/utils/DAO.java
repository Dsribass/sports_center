package org.example.domain.usecases.utils;

import java.util.List;
import java.util.Optional;

public interface DAO<K,T> {
    Optional<T> findOne(K key);
    List<T> fetchAll();
    K insert(T type);
    boolean update(K key,T type);
    boolean delete(T type);
    boolean deleteByKey(K key);
}
