package com.example.drinkwater.data;
import com.google.gson.Gson;
import java.util.Objects;
import javax.inject.Inject;
import javax.inject.Named;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpData<T>  implements IBaseData<T> {

    private final String apiUrl;
    private final OkHttpClient client;
    private final Gson gson;
    private Class<T> klass;
    private Class<T[]> klassArray;

    @Inject
    public HttpData(@Named("apiUrl") String apiUrl, Class<T> klass, Class<T[]> klassArray) {
        this.apiUrl = apiUrl;
        this.client = new OkHttpClient();
        this.gson = new Gson();
        this.klass = klass;
        this.klassArray = klassArray;
    }


    @Override
    public Observable add(T obj, BaseDataCallback addPostCallback) {
        return null;
    }

    @Override
    public Observable<T> getById(Object id) {
        return null;
    }

    @Override
    public Observable<T[]> getAll() {
        return Observable.create((ObservableOnSubscribe<T[]>) emitter -> {
            Request req = new Request.Builder()
                    .url(apiUrl)
                    .build();
            Response res = client.newCall(req).execute();

            String json = Objects.requireNonNull(res.body()).string();
            T[] result = gson.fromJson(json, klassArray);

            emitter.onNext(result);
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
