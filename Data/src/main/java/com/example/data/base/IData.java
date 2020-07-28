package com.example.data.base;
import io.reactivex.rxjava3.core.Observable;

public interface IData<T> {

    Observable<T[]> getAll();
    Observable<T> getById(Object id);
    Observable<T> add(T obj);
}
