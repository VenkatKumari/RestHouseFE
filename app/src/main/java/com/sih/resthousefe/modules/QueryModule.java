package com.sih.resthousefe.modules;

import com.sih.resthousefe.ViewInfoQuery;

import dagger.Module;
import dagger.Provides;


@Module
public class QueryModule {
    @Provides
    public ViewInfoQuery viewInfoQuery(){
        return  ViewInfoQuery.builder().build();
    }
}
