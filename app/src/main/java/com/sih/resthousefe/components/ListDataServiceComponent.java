package com.sih.resthousefe.components;

import com.sih.resthousefe.Scope.ClientScope;
import com.sih.resthousefe.SearchFragment;
import com.sih.resthousefe.modules.ApolloModule;

import dagger.Component;


@ClientScope
@Component(modules = ApolloModule.class)
public interface ListDataServiceComponent {
    void injectSearchFragment(SearchFragment searchFragment);
}
