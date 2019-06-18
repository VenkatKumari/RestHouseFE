package com.sih.resthousefe.modules;

import android.content.Context;

import com.sih.resthousefe.Scope.ClientScope;
import com.apollographql.apollo.cache.http.DiskLruHttpCacheStore;

import java.io.File;

import dagger.Module;
import dagger.Provides;


@Module(includes = ContextModule.class)
public class CacheModule {
    @Provides
    @ClientScope
    public Integer integer() {
        return  1024 * 1024;
    }

    @Provides
    @ClientScope
    public File file(Context context){
        return new File(context.getCacheDir().toURI());
    }

    @Provides
    @ClientScope
    public DiskLruHttpCacheStore diskLruHttpCacheStore(Integer integer,File file){
        return new DiskLruHttpCacheStore(file,integer);
    }
}
