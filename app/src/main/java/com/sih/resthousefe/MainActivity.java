package com.sih.resthousefe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;



public class MainActivity extends AppCompatActivity implements RestHFragment.OnFragmentInteractionListener,SearchFragment.OnFragmentInteractionListener,BookinghisFragment.OnFragmentInteractionListener {

    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

    @BindView(R.id.bottom_navigation) BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        ft.add(R.id.Frame1,new SearchFragment());
        ft.commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        FragmentTransaction ft0 = getSupportFragmentManager().beginTransaction();
                        ft0.replace(R.id.Frame1,new SearchFragment());
                        ft0.commit();
                        break;

                    case R.id.Booking:
                        FragmentTransaction ft1 = getSupportFragmentManager().beginTransaction();
                        ft1.replace(R.id.Frame1,new BookinghisFragment());
                        ft1.commit();
                        break;

                    case R.id.Profile:
                        Intent intent = new Intent(MainActivity.this, MlActivity.class);
                        startActivity(intent);
                        break;

                }
                return false;
            }
        });

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}

