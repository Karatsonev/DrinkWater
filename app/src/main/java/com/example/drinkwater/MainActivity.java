package com.example.drinkwater;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.example.data.HttpData;
import com.example.data.base.IData;
import com.example.drinkwater.models.Post;

import java.util.Arrays;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements MainActivityContract.View {

    private MainActivityPresenter mPresenter;
    private TextView mTxtResult;
    private CompositeDisposable disposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPresenter = new MainActivityPresenter(this, this);
        mPresenter.onViewCreated();
        mPresenter.registerPrefsListener();

        String url = "https://jsonplaceholder.typicode.com/posts/";
        IData<Post> data = new HttpData<>(url, Post[].class, Post.class);
        StringBuilder sb = new StringBuilder();
        disposable.add( data.getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( posts -> Arrays.stream(posts)
                        .forEach(post -> {
                    sb.append(post.getTitle());
                    sb.append(",");
                })));
      mTxtResult.setText(sb.toString());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unregisterPrefsListener();
        disposable.clear();
    }

    @Override
    public void initViews() {
        mTxtResult = findViewById(R.id.txtResult);
    }

    @Override
    public void initCounter() {
        mTxtResult.setText(String.valueOf(mPresenter.getCounterNumber()));
    }

    @Override
    public void updateCounter(int counter) {
        mTxtResult.setText(String.valueOf(counter));
    }


    public void onIncrementCounterClicked(View view) {
        mPresenter.incrementCounter();
    }
}