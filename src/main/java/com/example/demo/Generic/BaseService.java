package com.example.demo.Generic;

import java.util.List;

public interface BaseService<T, ID> {
    boolean create(T t);

    boolean delete(ID id);

    List<T> getAll();

    T getById(ID id);

    T update(ID id, T t);
}
