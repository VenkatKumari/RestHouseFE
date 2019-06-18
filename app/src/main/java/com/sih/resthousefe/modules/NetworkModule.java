package com.sih.resthousefe.modules;

import com.sih.resthousefe.Scope.ClientScope;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;


@Module
public class NetworkModule {
    @Provides
    @ClientScope
    public OkHttpClient okHttpClient(){
        return new OkHttpClient.Builder().build();
    }
}
