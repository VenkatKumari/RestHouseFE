package com.sih.resthousefe;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import butterknife.BindView;
import butterknife.ButterKnife;


public class Det_RH_View extends AppCompatActivity{

    @BindView(R.id.proceed)
    Button Proceed;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.det_rh_view);
        ButterKnife.bind(this);

        Proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Det_RH_View.this,BookingRh.class);
                startActivity(intent);
            }
        });

    }
}
