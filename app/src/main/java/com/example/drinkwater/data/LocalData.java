package com.example.drinkwater.data;
import android.content.Context;
import java.util.ArrayList;
import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;

public class LocalData<T> implements IBaseData<T> {

    private final Context context;

    private final ArrayList<T> items;

    @Inject
    public LocalData(Context context) {
        this.items = new ArrayList<>();
        this.context = context;
    }

    @Override
    public Observable add(T obj, BaseDataCallback addPostCallback) {
        return Observable.create(emitter -> {
            addPostCallback.addPostCallback("Post created!");
        });
    }

    @Override
    public Observable<T> getById(Object id) {
        return null;
    }

    @Override
    public Observable<T[]> getAll() {
        return null;

    }
}
