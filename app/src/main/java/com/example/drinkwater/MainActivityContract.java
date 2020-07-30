package com.example.drinkwater;

public interface MainActivityContract {

    interface View {

        void initViews();

        void initCounter();

        void updateCounter(int counter);

        void setPresenter(MainActivityContract.Presenter presenter);

    }

    interface Presenter {

        void onViewCreated();

        void incrementCounter();

        int getCounterNumber();

        void registerPrefsListener();

        void unregisterPrefsListener();

        MainActivityContract.View getView();
    }
}
