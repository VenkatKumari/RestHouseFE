package com.sih.resthousefe;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;


public class Booked_Rooms extends AppCompatActivity {

    @BindView(R.id.cancel)Button Cancel;
    @BindView(R.id.extend)Button Extend;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.booked_rooms);
        ButterKnife.bind(this);

        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Booked_Rooms.this,Cancel.class);
                startActivity(intent);
            }
        });

        Extend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Booked_Rooms.this,Extend.class);
                startActivity(intent);
            }
        });
    }
}
