package com.example.drinkwater;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.drinkwater.data.HttpData;
import com.example.drinkwater.data.IBaseData;
import com.example.drinkwater.data.PreferencesUtils;
import com.example.drinkwater.di.Dagger2DrinkWaterApplication;
import com.example.drinkwater.models.Post;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity implements MainActivityContract.View {

    @Inject
    public PreferencesUtils preferencesUtils;
    @Inject
    public HttpData<Post> data;

    public MainActivityPresenter mPresenter;
    private TextView mTxtResult;
    private CompositeDisposable disposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPresenter = new MainActivityPresenter(this, this);
        mPresenter.onViewCreated();

        //Init Dependency Injection
        ((Dagger2DrinkWaterApplication) getApplication())
                .getComponent()
                .inject(this);

        disposable.add(data.getAll()
                .subscribe(posts -> {
                    StringBuilder sb = new StringBuilder();
                    for (Post post : posts) {
                        sb.append(post.getTitle().charAt(0));
                        sb.append(",");
                    }
                    mTxtResult.setText(sb.toString());
                }));
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

    @Override
    public void setPresenter(MainActivityContract.Presenter presenter) {
     }


    public void onIncrementCounterClicked(View view) {
        mPresenter.incrementCounter();
    }
}