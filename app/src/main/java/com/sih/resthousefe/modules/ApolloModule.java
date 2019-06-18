package com.sih.resthousefe.modules;

import com.sih.resthousefe.Scope.ClientScope;
import com.sih.resthousefe.ViewInfoQuery;
import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.cache.http.ApolloHttpCache;
import com.apollographql.apollo.cache.http.DiskLruHttpCacheStore;

import java.util.List;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;


@Module(includes = {NetworkModule.class,CacheModule.class,QueryModule.class})
public class ApolloModule {

    public   List<ViewInfoQuery.Holidayhome> list;
    @Provides
    @ClientScope
    public ApolloClient apolloClient(OkHttpClient okHttpClient,ApolloHttpCache apolloHttpCache){
        return  ApolloClient.builder().serverUrl("https://resthousegraphql.herokuapp.com/graphql").httpCache(apolloHttpCache).okHttpClient(okHttpClient).build();
    }
    @Provides
    @ClientScope
    public ApolloHttpCache apolloHttpCache(DiskLruHttpCacheStore diskLruHttpCacheStore) {
        return new ApolloHttpCache(diskLruHttpCacheStore);
    }
    @Provides
    @ClientScope
    public ApolloCall<ViewInfoQuery.Data> apolloCall(ApolloClient apolloClient, ViewInfoQuery viewInfoQuery){
        return apolloClient.query(viewInfoQuery);
    }


}
