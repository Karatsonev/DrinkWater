package com.example.drinkwater.di.modules;
import com.example.drinkwater.models.Post;
import dagger.Module;
import dagger.Provides;

@Module
public class DataModule {


    @Provides
    Class<Post> providePostType() {
        return Post.class;
    }

    @Provides
    Class<Post[]> providePostArrayType() {
        return Post[].class;
    }

}
