package com.sih.resthousefe;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;



public class Login extends AppCompatActivity {

    @BindView(R.id.edit_username)
    EditText Username;
    @BindView(R.id.edit_pass)EditText PassWord;
    @BindView(R.id.submit)Button Submit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        ButterKnife.bind(this);


    }
}
