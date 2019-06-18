package com.sih.resthousefe;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Calendar;

public class DActivity extends AppCompatActivity implements View.OnFocusChangeListener,TimePickerDialog.OnTimeSetListener
        ,View.OnClickListener {

    private static final String TAG = "MainActivity";

    TextView editcheckin,editcheckout,cald;
    ImageView cal;
    private  int mYear,mMonth,mDay,mHour,mMinute,mper;
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        editcheckin = (TextView) findViewById(R.id.ctime);
        editcheckout = (TextView) findViewById(R.id.cotime);
        cal = (ImageView) findViewById(R.id.calender);
        cald = findViewById(R.id.dated);

        cal.setOnClickListener(this);
        editcheckout.setOnClickListener(this);
        editcheckin.setOnClickListener(this);
        getImages();
    }

    private void getImages(){
        Log.d(TAG, "initImageBitmaps: preparing bitmaps.");

        mImageUrls.add("https://ui.cltpstatic.com/places/hotels/4379/437988/images/9703022_w.jpg");
        mNames.add("Havasu Falls");

        mImageUrls.add("http://photos.wikimapia.org/p/00/00/77/03/17_big.jpg");
        mNames.add("Trondheim");

        mImageUrls.add("https://pbs.twimg.com/media/Cq21NK7XgAEVQdG.jpg");
        mNames.add("Portugal");

        mImageUrls.add("http://www.nainitalcottages.com/cottage_photos/big/57_529.jpg");
        mNames.add("Rocky Mountain National Park");


        mImageUrls.add("https://c1.hiqcdn.com/images/property/resortimg/414336_w403.jpg");
        mNames.add("Mahahual");

        mImageUrls.add("http://www.tribuneindia.com/2008/20081122/dun2.jpg");
        mNames.add("Frozen Lake");


        mImageUrls.add("https://ui.cltpstatic.com/places/hotels/7227/722741/images/IMG_5913_w.jpg");
        mNames.add("White Sands Desert");

        mImageUrls.add("https://ui.cltpstatic.com/places/hotels/3843/384334/images/11_w.jpg");
        mNames.add("Austrailia");

        mImageUrls.add("https://www.kayak.co.kr/rimg/himg/2b/9c/4c/ean-273080-2186324_231_b-image.jpg?width=420&height=278&crop=true");
        mNames.add("Washington");

        initRecyclerView();

    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init recyclerview");

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerView = findViewById(R.id.my_recycler_view1);
        recyclerView.setLayoutManager(layoutManager);
        DetailAdapter adapter = new DetailAdapter(this, mNames, mImageUrls);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void onFocusChange(View view, boolean b) {

    }

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {

    }

    @Override
    public void onClick(View view) {

        if (view == cal) {
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int monofyear, int dayofmon) {

                    cald.setText(dayofmon + "-" + (monofyear + 1) + "-" + year);
                }
            }, mYear, mMonth, mDay);
            datePickerDialog.show();

        }

        if (view == editcheckin) {
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);
            mper = c.get(Calendar.AM_PM);


            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int hourofday, int min) {

                    String AM_PM;
                    if(hourofday<12){
                        AM_PM="AM";

                    }else
                    {
                        AM_PM="PM";
                        hourofday-=12;
                    }
                    editcheckin.setText(hourofday + ":" + min + " " + AM_PM);

                }
            }, mHour, mMinute, false);
            timePickerDialog.show();
        }
        if (view == editcheckout) {
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);
            mper = c.get(Calendar.AM_PM);


            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int hourofday, int min) {

                    String AM_PM;
                    if(hourofday<12){
                        AM_PM="AM";

                    }else
                    {
                        AM_PM="PM";
                        hourofday-=12;
                    }
                    editcheckout.setText(hourofday + ":" + min + " " + AM_PM);

                }
            }, mHour, mMinute, false);
            timePickerDialog.show();
        }
    }
}



