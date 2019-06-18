package com.sih.resthousefe.modules;

import android.content.Context;

import com.sih.resthousefe.Scope.ClientScope;

import dagger.Module;
import dagger.Provides;


@Module
public class ContextModule {
    private final Context context;

    public ContextModule(Context context){
        this.context = context;
    }
    @Provides
    @ClientScope
    public Context context(){
        return context;
    }
}
