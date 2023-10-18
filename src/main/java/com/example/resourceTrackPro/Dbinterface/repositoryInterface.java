package com.example.resourceTrackPro.Dbinterface;

import java.util.List;

public interface repositoryInterface<T> {
    void save(T obj);

    void delete(Long id);

    void update(T obj);

    T findById(Long id);

    List<T> getAll();

}
