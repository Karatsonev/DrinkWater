package com.example.drinkwater.di;
import android.app.Application;
import com.example.drinkwater.MainActivity;
import com.example.drinkwater.di.modules.ApplicationModule;
import com.example.drinkwater.di.modules.ConfigModule;
import com.example.drinkwater.di.modules.DataModule;
import dagger.Component;

public class Dagger2DrinkWaterApplication extends Application {

    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        this.component = DaggerDagger2DrinkWaterApplication_ApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getComponent() {
        return component;
    }

    @Component(modules = {DataModule.class, ApplicationModule.class, ConfigModule.class})
    public interface ApplicationComponent {
        void inject(MainActivity mainActivity);

    }
}
