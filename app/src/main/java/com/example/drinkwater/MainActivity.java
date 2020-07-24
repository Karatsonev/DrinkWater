package com.example.drinkwater;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements MainActivityContract.View {

    private MainActivityPresenter mPresenter;
    private TextView mTxtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPresenter = new MainActivityPresenter(this,this);
        mPresenter.onViewCreated();
        mPresenter.registerPrefsListener();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unregisterPrefsListener();
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