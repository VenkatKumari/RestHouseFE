package com.sih.resthousefe;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;

import javax.annotation.Nonnull;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.OkHttpClient;



public class RequestSumbit extends AppCompatActivity {

    @BindView(R.id.autofill) CheckBox autofil;
    @BindView(R.id.empname) TextView empname;
    @BindView(R.id.empdesignation) TextView empdesignation;

    @BindView(R.id.divunit) TextView divunit;
    @BindView(R.id.empscale) TextView empscale;
    @BindView(R.id.emprate) TextView emprate;

    @BindView(R.id.fromdate) TextView fromdate;
    @BindView(R.id.todate) TextView todate;
    @BindView(R.id.nameofperson1) TextView name1;
    @BindView(R.id.nameofperson2) TextView name2;
    @BindView(R.id.nameofperson3) TextView name3;

    @BindView(R.id.nameofperson5) TextView name5;
    @BindView(R.id.ageofperson1) TextView age1;
    @BindView(R.id.ageofperson2) TextView age2;
    @BindView(R.id.ageofperson3) TextView age3;

    @BindView(R.id.ageofperson5) TextView age5;
    @BindView(R.id.purpose) TextView purpose;
    @BindView(R.id.hhval)EditText e1;


    @BindView(R.id.submit_btn)
    Button sbutton;
    OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
    ApolloClient apolloClient = ApolloClient.builder()
            .serverUrl("https://resthousegraphql.herokuapp.com/graphql")
            .okHttpClient(okHttpClient)
            .build();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_form);
        ButterKnife.bind(this);

        empdesignation.setText(getIntent().getExtras().getString("Empid"));
        empname.setText(getIntent().getExtras().getString("Empna"));
        empscale.setText(getIntent().getExtras().getString("Empmail"));
        divunit.setText(getIntent().getExtras().getString("Empmob"));
        emprate.setText(getIntent().getExtras().getString("Empdes"));
        Log.d("TAg","out: "+ getIntent().getExtras().getString("Empid"));
        autofil.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if(autofil.isChecked()) {
                    fromdate.setText("1-04-2018");
                    todate.setText("4-04-2018");
                    name1.setText("Arjun");
                    name2.setText("Arjun1");
                    name3.setText("Arjun2");

                    name5.setText("Arjun4");
                    age1.setText("25");
                    age2.setText("27");
                    age3.setText("25");

                    age5.setText("24");
                    purpose.setText("Business");
                }
                else {
                    empname.setText("");
                    empdesignation.setText("");

                    divunit.setText("");
                    empscale.setText("");
                    emprate.setText("");

                    fromdate.setText("");
                    todate.setText("");
                    name1.setText("");
                    name2.setText("");
                    name3.setText("");

                    name5.setText("");
                    age1.setText("");
                    age2.setText("");
                    age3.setText("");

                    age5.setText("");
                    purpose.setText("");

                }

            }
        });

        sbutton.setOnClickListener(v ->{
            BookMutation bookMutation = BookMutation.builder().empid(empdesignation.getText().toString()).empname(empname.getText().toString()).designation(emprate.getText().toString()).emonno(divunit.getText().toString()).empemail(empscale.getText().toString()).hhid(e1.getText().toString()).fdate(fromdate.getText().toString()).tdate(todate.getText().toString()).build();
            apolloClient.mutate(bookMutation).enqueue(new ApolloCall.Callback<BookMutation.Data>() {
                @Override
                public void onResponse(@Nonnull Response<BookMutation.Data> response) {
                    Log.d("Tag","out: "+ response.data());
                    RequestSumbit.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplication(),"Successfully requested Room",Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getApplication(), SuiteDetail.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                            startActivity(intent);
                        }
                    });
                }

                @Override
                public void onFailure(@Nonnull ApolloException e) {
                    Log.d("Tag","" + e);
                }
            });

        });


    }
}
