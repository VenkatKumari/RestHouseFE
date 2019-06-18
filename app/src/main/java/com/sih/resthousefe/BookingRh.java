package com.sih.resthousefe;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.api.cache.http.HttpCachePolicy;
import com.apollographql.apollo.cache.http.ApolloHttpCache;
import com.apollographql.apollo.cache.http.DiskLruHttpCacheStore;
import com.apollographql.apollo.exception.ApolloException;

import java.io.File;

import javax.annotation.Nonnull;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.OkHttpClient;

/**
 * Created by ramya on 17/3/18.
 */

public class BookingRh extends AppCompatActivity {
    @BindView(R.id.spinner)Spinner Category;
    @BindView(R.id.img)ImageView Image;
    @BindView(R.id.acstatus)RadioGroup AcStatus;
    @BindView(R.id.get_price)TextView Price;
    @BindView(R.id.ac)RadioButton Ac;
    @BindView(R.id.non_ac)RadioButton Non_Ac;
    @BindView(R.id.book)
    Button Book;

    @BindView(R.id.txt1) TextView Txt1;
    @BindView(R.id.txt2) TextView Txt2;
    @BindView(R.id.txt3) TextView Txt3;
    @BindView(R.id.txt4) TextView Txt4;
    @BindView(R.id.txt5) TextView Txt5;
    @BindView(R.id.txt6) TextView Txt6;

    String rroomno,rbedtype,rroomtype,ravailable,rtariff;
    public ApolloClient apolloClient;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.booking_rh);
        ButterKnife.bind(this);

        Book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BookingRh.this,Personal_Det.class);
                startActivity(intent);
            }
        });


        Category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String selectedItem = parent.getItemAtPosition(position).toString();
                if(selectedItem.equals("2 Double Bedroom")){
                    Toast.makeText(BookingRh.this,"2 double BedRoom",Toast.LENGTH_SHORT).show();
                    Image.setImageResource(R.drawable.rh7);
                }else {
                    Image.setImageResource(R.drawable.dormitory);
                }


            } // to close the onItemSelected
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });


        AcStatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {



                int index = AcStatus.indexOfChild(Ac);

                switch (index) {
                    case 0:

                        Price.setText("Rs.150");
                        break;

                    case 1:

                        Price.setText("Rs.100");
                        break;
                }

            }

        });

//
//        Intent mIntent = getIntent();
//        int intId = mIntent.getIntExtra("ROOM_ID", 0);
//
//     callQuery(intId);

    }

//    void callQuery(int id) {
//        setUpClient("https://resthousegraphql.herokuapp.com/graphql");
//        RoomQuery roomQuery = RoomQuery.builder()
//                .id(id)
//                .build();
//        apolloClient.query(roomQuery)
//                .httpCachePolicy(HttpCachePolicy.CACHE_FIRST)
//                .enqueue(new ApolloCall.Callback<RoomQuery.Data>() {
//                    @Override
//                    public void onResponse(@Nonnull Response<RoomQuery.Data> response) {
//
//                        RoomQuery.Data data = response.data();
//
//                        rroomno = data.room.get(0).roomno.toString();
//                        rbedtype = data.room.get(0).bedtype.toString();
//                        rroomtype = data.room.get(0).roomtype.toString();
//                        ravailable = data.room.get(0).available.toString();
//
//                        rtariff = data.room.get(0).tariff.toString();
//                        Log.d("roommo","room"+rroomno);
//
//
//                        BookingRh.this.runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//
//
//                                Txt1.setText(rroomno);
//                                Txt2.setText(rbedtype);
//                                Txt3.setText(rroomtype);
//                                Txt4.setText(ravailable);
//
//                                Txt6.setText(rtariff);
//
//
//                            }
//
//                        });
//                    }
//
//                    @Override
//                    public void onFailure(@Nonnull ApolloException e) {
//                    }
//
//                });


//
//    }
//
//
//
//
//
//
//    public void setUpClient(String qlurl){
//
//        File file = new File(this.getCacheDir().toURI());
//        //Size in bytes of the cache
//        int size = 1024*1024;
//
//        //Create the http response cache store
//        DiskLruHttpCacheStore cacheStore = new DiskLruHttpCacheStore(file, size);
//
//        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .build();
//
//        apolloClient = ApolloClient.builder()
//                .serverUrl(qlurl)
//                .httpCache(new ApolloHttpCache(cacheStore))
//                .okHttpClient(okHttpClient)
//                .build();
//    }
}
