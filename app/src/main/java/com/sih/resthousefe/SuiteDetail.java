package com.sih.resthousefe;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.api.cache.http.HttpCachePolicy;
import com.apollographql.apollo.cache.http.ApolloHttpCache;
import com.apollographql.apollo.cache.http.DiskLruHttpCacheStore;
import com.apollographql.apollo.exception.ApolloException;
import com.google.vr.sdk.widgets.pano.VrPanoramaView;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;

import javax.annotation.Nonnull;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;




public class SuiteDetail extends AppCompatActivity implements View.OnFocusChangeListener,TimePickerDialog.OnTimeSetListener
        ,View.OnClickListener {


    private ArrayList<Model> productList;
    private VrPanoramaView panoWidgetView;
    private ImageLoaderTask backgroundImageLoaderTask;
    OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
    ApolloClient apolloClient = ApolloClient.builder()
            .serverUrl("https://resthousegraphql.herokuapp.com/graphql")
            .okHttpClient(okHttpClient)
            .build();
    String ssid,ssname,ssrating,ssrate,ssbedtype,ssavail,sstype;
    String hid,hname,hdetail,hcontacts,hdate,hlocation,hava,hrava,hsuiteno,psuitename,psuiteavail,psuiterating,psuiteprice,psuitetype;

    public int Listsize = 0;
    private static final String TAG = "MainActivity";
    private  int mYear,mMonth,mDay,mHour,mMinute,mper;
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();
    boolean Isup=true;
    private String one ="A";
    private String two ="B";
    private String three ="C";
    private String four ="D";


    @BindView(R.id.grpa)
    ImageView imga;
    @BindView(R.id.grpb)
    ImageView imgb;
    @BindView(R.id.grpc)
    ImageView imgc;
    @BindView(R.id.grpd)
    ImageView imgd;
    @BindView(R.id.calender)
    ImageView cal;
    @BindView(R.id.ctime)
    TextView editcheckin;
    @BindView(R.id.cotime)
    TextView editcheckout;
    @BindView(R.id.dated)
    TextView cald;
    @BindView(R.id.Named)
    TextView nname;
    @BindView(R.id.Addressd)
    TextView naddress;
    @BindView(R.id.Contactd)
    TextView ncontact;
    @BindView(R.id.Suited)
    TextView nsuited;
    @BindView(R.id.suitenamed)
    TextView suitenamed;
    @BindView(R.id.suiteavaildis)
    TextView suiteavaildis;
    @BindView(R.id.ratingtd)
    TextView ratingd;
    @BindView(R.id.priced)
    TextView priced;
    @BindView(R.id.remain)
    ImageView remaind;
    @BindView(R.id.requestbutton)
    Button requestsubmit;
    @BindView(R.id.call)
    ImageButton call;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailview);
        ButterKnife.bind(this);

        cal.setOnClickListener(this);
        editcheckout.setOnClickListener(this);
        editcheckin.setOnClickListener(this);
        panoWidgetView = (VrPanoramaView) findViewById(R.id.pano_view);

        final RelativeLayout rdd = (RelativeLayout) findViewById(R.id.suite2detail);
        final RelativeLayout rt = (RelativeLayout) findViewById(R.id.rr3);

        Intent mIntent = getIntent();
        int intValue = mIntent.getIntExtra("SUITE_ID", 0);
        getImages();


        imga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callQuery1(intValue,one);


                if(Isup)
                {
                    rdd.setVisibility(View.VISIBLE);


                }else {
//                    suitenamed.setText("");
//                    suiteavaildis.setText("");
//                    ratingd.setText("");
//                    priced.setText("");
                    rdd.setVisibility(View.GONE);
                }
//                     rt.animate().translationY(100);
                Isup = !Isup;
            }

        });


        imgb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callQuery2(intValue,two);
                if(Isup)
                {
                    rdd.setVisibility(View.VISIBLE);



                }else {
//                    suitenamed.setText("");
//                    suiteavaildis.setText("");
//                    ratingd.setText("");
//                    priced.setText("");
                    rdd.setVisibility(View.GONE);
                }
//                     rt.animate().translationY(100);
                Isup = !Isup;
            }
        });
        imgc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callQuery3(intValue,three);
                if(Isup)
                {
                    rdd.setVisibility(View.VISIBLE);


                }else {
//                    suitenamed.setText("");
//                    suiteavaildis.setText("");
//                    ratingd.setText("");
//                    priced.setText("");

                    rdd.setVisibility(View.GONE);
                }
//                     rt.animate().translationY(100);
                Isup = !Isup;
            }
        });
        imgd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callQuery4(intValue,four);


                if(Isup)
                {
                    rdd.setVisibility(View.VISIBLE);

                }else {
//                    suitenamed.setText("");
//                    suiteavaildis.setText("");
//                    ratingd.setText("");
//                    priced.setText("");
                    rdd.setVisibility(View.GONE);
                }
//                     rt.animate().translationY(100);
                Isup = !Isup;
            }
        });

        remaind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        requestsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SuiteDetail.this,Main2Activity.class);
                startActivity(intent);
            }
        });





//     Integer bid = getIntent().("SUITE_ID");
//     Integer num1 = Integer.parseInt(bid);
        Log.d("tag","ShelDetail"+intValue);

        productList = new ArrayList<Model>();
        io.reactivex.Observable.just(1).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<Integer>() {
                    @Override
                    public void onNext(Integer integer) {
                        RoombQuery roombQuery = RoombQuery.builder().build();
                        apolloClient.query(roombQuery).enqueue(new ApolloCall.Callback<RoombQuery.Data>() {
                            @Override
                            public void onResponse(@Nonnull Response<RoombQuery.Data> response) {
                                String dt;
                                for(int i= 0;i<response.data().booking().size();i++){
                                    if(i == 0){
                                        dt = "Confirmed";
                                    }else{
                                        dt = "Waiting List";
                                    }
                                    Model item = new Model(response.data().booking().get(i).empname,response.data().booking().get(i).designation,response.data().booking().get(i).fdate, dt);
                                    productList.add(item);
                                }
                            }

                            @Override
                            public void onFailure(@Nonnull ApolloException e) {

                            }
                        });
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        runfun();
                    }
                });




        callQuery(intValue);

//        Observable.just(1,2,3,4,5).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new DisposableObserver<Integer>() {
//                    @Override
//                    public void onNext(Integer integer) {
//                        try {
//                            loadPanoImage(integer);
//                            Log.d("Tag","out: "+ integer);
//                            Thread.sleep(120000);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
//
//
//
//        for(Integer i=0; i<5; i++){
//
//        }
        loadPanoImage();

    }

    private void runfun() {
        ListView lview = (ListView) findViewById(R.id.listview);
        listviewAdapter adapter = new listviewAdapter(this,productList);
        lview.setAdapter(adapter);


        adapter.notifyDataSetChanged();
        lview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                String sno = ((TextView)view.findViewById(R.id.sNo)).getText().toString();
                String product = ((TextView)view.findViewById(R.id.product)).getText().toString();
                String category = ((TextView)view.findViewById(R.id.category)).getText().toString();
                String price = ((TextView)view.findViewById(R.id.price)).getText().toString();

                Toast.makeText(getApplicationContext(),
                        "S no : " + sno +"\n"
                                +"Product : " + product +"\n"
                                +"Category : " +category +"\n"
                                +"Price : " +price, Toast.LENGTH_SHORT).show();
            }
        });
    }

//    private void populateList() {
//        RoombQuery roombQuery = RoombQuery.builder().build();
//        apolloClient.query(roombQuery).enqueue(new ApolloCall.Callback<RoombQuery.Data>() {
//            @Override
//            public void onResponse(@Nonnull Response<RoombQuery.Data> response) {
//                String dt;
//                for(int i= 0;i<response.data().booking().size();i++){
//                    if(i == 0){
//                        dt = "Confirmed";
//                    }else{
//                        dt = "Waiting List";
//                    }
//                   Model item = new Model(response.data().booking().get(i).empname,response.data().booking().get(i).designation,response.data().booking().get(i).fdate, dt);
//                    productList.add(item);
//                }
//            }
//
//            @Override
//            public void onFailure(@Nonnull ApolloException e) {
//
//            }
//        });
//
////        Model item1, item2, item3, item4, item5;
////
////        item1 = new Model("1", "Manager, General Manager", "Vacation with family", "100%");
////        productList.add(item1);
////
////        item2 = new Model("2", "Chief Yard Master", "Business work", "80%");
////        productList.add(item2);
////
////        item3 = new Model("3", "Station Supervisor", "Field work and other personal works", "60%");
////        productList.add(item3);
////
////        item4 = new Model("4", "Train Ticket Examiner,", "tour with family and friends", "50%");
////        productList.add(item4);
////
////        item5 = new Model("5", "Track Man", "Educational purpose", "20%");
////        productList.add(item5);
//        try {
//            Thread.sleep(4000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }

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
        mNames.add("Australia");

        mImageUrls.add("https://www.kayak.co.kr/rimg/himg/2b/9c/4c/ean-273080-2186324_231_b-image.jpg?width=420&height=278&crop=true");
        mNames.add("Washington");

        initRecyclerView();

    }

    private void initRecyclerView() {
        Log.d(TAG, "initRecyclerView: init recyclerview");
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerView = findViewById(R.id.my_recycler_view1);
        recyclerView.setLayoutManager(layoutManager);
        DetailAdapter adapter = new DetailAdapter(this, mNames, mImageUrls);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onPause() {
        panoWidgetView.pauseRendering();
        super.onPause();
    }

    @Override
    public void onResume() {
        panoWidgetView.resumeRendering();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        // Destroy the widget and free memory.
        panoWidgetView.shutdown();
        super.onDestroy();
    }

    private synchronized void loadPanoImage() {
        ImageLoaderTask task = backgroundImageLoaderTask;
        if (task != null && !task.isCancelled()) {
            // Cancel any task from a previous loading.
            task.cancel(true);
        }

        // pass in the name of the image to load from assets.
        VrPanoramaView.Options viewOptions = new VrPanoramaView.Options();
        viewOptions.inputType = VrPanoramaView.Options.TYPE_STEREO_OVER_UNDER;

        // use the name of the image in the assets/ directory.
        String panoImageName = "room1-converted.jpg";

        // create the task passing the widget view and call execute to start.
        task = new ImageLoaderTask(panoWidgetView,viewOptions,panoImageName);
        task.execute(this.getAssets());
        backgroundImageLoaderTask = task;
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



    void callQuery(int id) {
        setUpClient("https://resthousegraphql.herokuapp.com/graphql");
        DetailViewQuery detailViewQuery = DetailViewQuery.builder()
                .id(id)
                .build();
        apolloClient.query(detailViewQuery)
                .httpCachePolicy(HttpCachePolicy.CACHE_FIRST)
                .enqueue(new ApolloCall.Callback<DetailViewQuery.Data>() {
                    @Override
                    public void onResponse(@Nonnull Response<DetailViewQuery.Data> response) {
                        DetailViewQuery.Data data = response.data();

                        hname = data.holidayhome.get(0).hhname.toString();
                        hcontacts = data.holidayhome.get(0).hhacontact.toString();
                        hlocation = data.holidayhome.get(0).hhlocation.toString();
                        hsuiteno = data.holidayhome.get(0).hhavail.toString();

                        SuiteDetail.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                nname.setText(hname);
                                ncontact.setText(hcontacts);
                                naddress.setText(hlocation);
                                nsuited.setText(hsuiteno);
                            }

                        });

                    }

                    @Override
                    public void onFailure(@Nonnull ApolloException e) {
                    }

                });
    }

    void callQuery1(int id,String one) {
        setUpClient("https://resthousegraphql.herokuapp.com/graphql");
        SuiteQuery  suiteQuery = SuiteQuery.builder()
                .id(id)
                .suitetype(one)
                .build();
        apolloClient.query(suiteQuery)
                .httpCachePolicy(HttpCachePolicy.CACHE_FIRST)
                .enqueue(new ApolloCall.Callback<SuiteQuery.Data>() {
                    @Override
                    public void onResponse(@Nonnull Response< SuiteQuery.Data> response) {
                        SuiteQuery.Data data = response.data();
                        String dd = "[]";
                        SuiteDetail.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {


                                if (data.suite.toString() == (dd)) {
                                    suitenamed.setText("");
                                    suiteavaildis.setText("");
                                    ratingd.setText("");
                                    priced.setText("");

                                } else {

                                    psuitename = data.suite.get(0).suitename.toString();
                                    psuiteavail = data.suite.get(0).suiteavail.toString();
                                    psuiterating = data.suite.get(0).suiterating.toString();
                                    psuiteprice = data.suite.get(0).suiterate.toString();
                                    psuitetype = data.suite.get(0).suitetype.toString();

                                    Log.d("tag", "Clicked" + psuitetype);


                                    suitenamed.setText(psuitename);
                                    suiteavaildis.setText(psuiteavail);
                                    ratingd.setText(psuiterating);
                                    priced.setText(psuiteprice);
                                }
                            }

                        });
                    }

                    @Override
                    public void onFailure(@Nonnull ApolloException e) {
                    }

                });
    }



    void callQuery2(int id ,String two) {
        setUpClient("https://resthousegraphql.herokuapp.com/graphql");
        SuiteQuery  suiteQuery = SuiteQuery.builder()
                .id(id)
                .suitetype(two)
                .build();
        apolloClient.query(suiteQuery)
                .httpCachePolicy(HttpCachePolicy.CACHE_FIRST)
                .enqueue(new ApolloCall.Callback<SuiteQuery.Data>() {
                    @Override
                    public void onResponse(@Nonnull Response< SuiteQuery.Data> response) {
                        SuiteQuery.Data data = response.data();
                        Log.d("da","dataa:"+ data.suite.toString()+":ghgh");
                        String dd = "[]";
                        SuiteDetail.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {


                                if (data.suite.toString() == (dd)) {
                                    suitenamed.setText("");
                                    suiteavaildis.setText("");
                                    ratingd.setText("");
                                    priced.setText("");

                                } else {

                                    psuitename = data.suite.get(0).suitename.toString();
                                    psuiteavail = data.suite.get(0).suiteavail.toString();
                                    psuiterating = data.suite.get(0).suiterating.toString();
                                    psuiteprice = data.suite.get(0).suiterate.toString();
                                    psuitetype = data.suite.get(0).suitetype.toString();

                                    Log.d("tag", "Clicked" + psuitetype);


                                    suitenamed.setText(psuitename);
                                    suiteavaildis.setText(psuiteavail);
                                    ratingd.setText(psuiterating);
                                    priced.setText(psuiteprice);
                                }
                            }

                        });
                    }

                    @Override
                    public void onFailure(@Nonnull ApolloException e) {
                    }

                });
    }

    void callQuery3(int id,String three) {
        setUpClient("https://resthousegraphql.herokuapp.com/graphql");
        SuiteQuery suiteQuery = SuiteQuery.builder()
                .id(id)
                .suitetype(three)
                .build();
        apolloClient.query(suiteQuery)
                .httpCachePolicy(HttpCachePolicy.CACHE_FIRST)
                .enqueue(new ApolloCall.Callback<SuiteQuery.Data>() {
                    @Override
                    public void onResponse(@Nonnull Response< SuiteQuery.Data> response) {
                        SuiteQuery.Data data = response.data();
                        String dd = "[]";

                        SuiteDetail.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (data.suite.toString() == (dd)) {
                                    suitenamed.setText("");
                                    suiteavaildis.setText("");
                                    ratingd.setText("");
                                    priced.setText("");

                                } else {

                                    psuitename = data.suite.get(0).suitename.toString();
                                    psuiteavail = data.suite.get(0).suiteavail.toString();
                                    psuiterating = data.suite.get(0).suiterating.toString();
                                    psuiteprice = data.suite.get(0).suiterate.toString();
                                    psuitetype = data.suite.get(0).suitetype.toString();

                                    Log.d("tag", "Clicked" + psuitetype);


                                    suitenamed.setText(psuitename);
                                    suiteavaildis.setText(psuiteavail);
                                    ratingd.setText(psuiterating);
                                    priced.setText(psuiteprice);
                                }
                            }

                        });
                    }



                    @Override
                    public void onFailure(@Nonnull ApolloException e) {
                    }

                });
    }

    void callQuery4(int id,String four) {
        setUpClient("https://resthousegraphql.herokuapp.com/graphql");
        SuiteQuery  suiteQuery = SuiteQuery.builder()
                .id(id)
                .suitetype(four)
                .build();
        apolloClient.query(suiteQuery)
                .httpCachePolicy(HttpCachePolicy.CACHE_FIRST)
                .enqueue(new ApolloCall.Callback<SuiteQuery.Data>() {
                    @Override
                    public void onResponse(@Nonnull Response< SuiteQuery.Data> response) {
                        SuiteQuery.Data data = response.data();
                        String dd = "[]";
                        SuiteDetail.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {


                                if (data.suite.toString() == (dd)) {
                                    suitenamed.setText("");
                                    suiteavaildis.setText("");
                                    ratingd.setText("");
                                    priced.setText("");

                                } else {

                                    psuitename = data.suite.get(0).suitename.toString();
                                    psuiteavail = data.suite.get(0).suiteavail.toString();
                                    psuiterating = data.suite.get(0).suiterating.toString();
                                    psuiteprice = data.suite.get(0).suiterate.toString();
                                    psuitetype = data.suite.get(0).suitetype.toString();

                                    Log.d("tag", "Clicked" + psuitetype);


                                    suitenamed.setText(psuitename);
                                    suiteavaildis.setText(psuiteavail);
                                    ratingd.setText(psuiterating);
                                    priced.setText(psuiteprice);
                                }
                            }

                        });
                    }
                    @Override
                    public void onFailure(@Nonnull ApolloException e) {
                    }

                });
    }





    public void setUpClient(String qlurl){

        File file = new File(this.getCacheDir().toURI());
        //Size in bytes of the cache
        int size = 1024*1024;

        //Create the http response cache store
        DiskLruHttpCacheStore cacheStore = new DiskLruHttpCacheStore(file, size);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .build();

        apolloClient = ApolloClient.builder()
                .serverUrl(qlurl)
                .httpCache(new ApolloHttpCache(cacheStore))
                .okHttpClient(okHttpClient)
                .build();
    }
}
