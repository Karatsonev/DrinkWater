package com.example.data;
import com.example.data.base.IData;
import com.google.gson.Gson;
import java.util.Objects;
import io.reactivex.rxjava3.core.Observable;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpData<T> implements IData<T> {

    private String mUrl;
    private OkHttpClient mClient;
    private Class<T[]> mClassArray;
    private Class<T> mClassSingle;

    public HttpData(String url, Class<T[]> classArray, Class<T> classSingle) {
     this.mUrl = url;
     this.mClient = new OkHttpClient();
     this.mClassSingle = classSingle;
     this.mClassArray = classArray;
    }

    @Override
    public Observable<T[]> getAll() {
        return Observable.create(emitter -> {
          try {
              Request req = HttpData.this.buildGetRequest(mUrl);
              Response res = mClient.newCall(req).execute();
              T[] obj = HttpData.this.parseArray(Objects.requireNonNull(res.body()).string(), mClassArray);
              emitter.onNext(obj);
          } catch (Exception ex) {
              emitter.onError(ex);
          }
        });
    }

    @Override
    public Observable<T> getById(Object id) {
        return Observable.create(emitter -> {
           Request req = buildGetRequest(mUrl);
           Response res = mClient.newCall(req).execute();
            T obj = parseSingle(Objects.requireNonNull(res.body()).string(), mClassSingle);
           emitter.onNext(obj);
        });
    }

    @Override
    public Observable<T> add(T obj) {
        return Observable.create(emitter -> {
         try {
             Request req = HttpData.this.buildPostRequestWithBody(obj);
             Response res = mClient.newCall(req).execute();
             T result = HttpData.this.parseSingle((Objects.requireNonNull(res.body()).string()), mClassSingle);
             emitter.onNext(result);
         } catch (Exception ex) {
             emitter.onError(ex);
         }
        });
    }

    private T[] parseArray(String string, Class<T[]> klass) throws Exception {
      try {
          Gson gson = new Gson();
          return gson.fromJson(string, klass);
      } catch (Exception ex) {
         throw new Exception(ex.getMessage());
      }
    }

    private T parseSingle(String string, Class<T> klass) throws Exception {
       try {
           Gson gson = new Gson();
           return gson.fromJson(string,klass);
       } catch (Exception ex) {
           throw new Exception(ex.getMessage());

       }
    }

    private Request buildGetRequest(String url) {
        return new Request.Builder()
                .url(mUrl)
                .get()
                .build();
    }

    private Request buildPostRequestWithBody(T obj) {
        Gson gson = new Gson();
        String json = gson.toJson(obj);
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), json);

        return new Request.Builder()
                .url(mUrl)
                .post(body)
                .build();
    }
}
