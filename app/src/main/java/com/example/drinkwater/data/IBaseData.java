package com.example.drinkwater.data;

import io.reactivex.rxjava3.core.Observable;

public interface IBaseData<T> {

    Observable add(T obj, BaseDataCallback addPostCallback);
    Observable<T> getById(Object id);
    Observable<T[]> getAll();

     interface BaseDataCallback {
        void addPostCallback(String result);
    }
}
