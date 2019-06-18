package com.sih.resthousefe;

import com.google.firebase.database.FirebaseDatabase;


public class FirebaseChat extends android.app.Application {



    @Override

    public void onCreate() {

        super.onCreate();

        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

    }

}