package com.example.drinkwater.di.modules;

import javax.inject.Named;
import dagger.Module;
import dagger.Provides;

@Module
public class ConfigModule {

    @Provides
    @Named("apiUrl")
    String provideApiUrl(){
        return "https://jsonplaceholder.typicode.com/posts/";
    }
}
