package com.sih.resthousefe;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.services.language.v1beta2.CloudNaturalLanguage;
import com.google.api.services.language.v1beta2.CloudNaturalLanguageRequestInitializer;
import com.google.api.services.language.v1beta2.model.AnnotateTextRequest;
import com.google.api.services.language.v1beta2.model.AnnotateTextResponse;
import com.google.api.services.language.v1beta2.model.Document;
import com.google.api.services.language.v1beta2.model.Entity;
import com.google.api.services.language.v1beta2.model.Features;
import com.google.api.services.language.v1beta2.model.Sentiment;

import java.io.IOException;
import java.util.List;

import javax.annotation.Nonnull;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;

public class BAdapter extends RecyclerView.Adapter<BAdapter.MyViewHolder>{

    public RelativeLayout commant;

    private List<String> rregname,rname,rfromdate,rtodate,rstatus;
    private  List<Float> rrating;
    private static Context mcon;
    Observable<String> observable;
    List<Entity> entityList = null;
    Sentiment sentiment;
    String entities;
    Context mContext;
    OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
    ApolloClient apolloClient = ApolloClient.builder()
            .serverUrl("https://resthousegraphql.herokuapp.com/graphql")
            .okHttpClient(okHttpClient)
            .build();

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.ddetail)
        TextView regname;
        @BindView(R.id.Named)
        TextView suitename;
        @BindView(R.id.fromdate)
        TextView fromdate;
        @BindView(R.id.todate)
        TextView  todate;
        @BindView(R.id.ratingBar1)
        RatingBar ratingBar;
        @BindView(R.id.submitcom)
        Button sbutton;
        @BindView(R.id.myreview)
        TextView myreview;
        @BindView(R.id.showstar)
        TextView showstar;
        @BindView(R.id.commandsLayout)
        RelativeLayout commant;
        @BindView(R.id.getcommand)
        EditText gcomment;
        @BindView(R.id.ml)
        ImageView mlimg;




        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);
            view.setOnClickListener(this);
        }

        public void MyAdapter(Context con){
            mcon = con;
        }

        @Override
        public void onClick(View v) {

        }
    }


    public BAdapter(Context context,List<String> myregname,List<String> myName,List<String> myfromdate,List<String> mytodate,List<Float> myrating,List<String> mystatus) {

        mContext = context;
        rregname=myregname;
        rname = myName;
        rfromdate = myfromdate;
        rtodate = mytodate;
        rrating = myrating;
        rstatus = mystatus;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_bookinghis, parent, false);


//        addListenerOnRatingBar();
//        addListenerOnButton();

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.regname.setText(rregname.get(position));
        holder.suitename.setText(rname.get(position));
        holder.fromdate.setText(rfromdate.get(position));
        holder.todate.setText(rtodate.get(position));
        holder.ratingBar.setRating(rrating.get(position));
        holder.sbutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                holder.commant.setVisibility(View.GONE);

                observable = Observable.create(d -> {
                    try {
                        sentiment = getSentiment(holder.gcomment.getText().toString());
                    } catch (IOException e) {
                        Log.d("TAg", "Error" + e);
                    }
                    d.onComplete();
                });
                observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new DisposableObserver<String>() {
                            @Override
                            public void onNext(String s) {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {
                                entities = "";
                                for(Entity entity:entityList) {

                                    entities += "\n" + entity.getName().toUpperCase();
                                }

                                Log.i("Tag","Score: "+ sentiment.getScore()+" "+"Magnitude: "+ sentiment.getMagnitude() + " "+entities);
                                if(sentiment.getScore() >= 0.8 && sentiment.getMagnitude() >= 0.3){
                                    Log.d("Positive","Post to twitter");
                                    updateBe("Positive",holder.gcomment.getText().toString());
                                    holder.mlimg.setImageDrawable(mContext.getDrawable(mContext.getResources().getIdentifier("@drawable/happy",null,mContext.getPackageName())));
                                }
                                else if(sentiment.getScore() <= -0.6 && sentiment.getMagnitude() >= 0.4){
                                    Log.d("Negative","Post to Admin");
                                    updateBe("Negative",holder.gcomment.getText().toString());
                                    holder.mlimg.setImageDrawable(mContext.getDrawable(mContext.getResources().getIdentifier("@drawable/sad",null,mContext.getPackageName())));
                                }
                                else {
                                    Log.d("Neutral","No Action");
                                    updateBe("Neutral",holder.gcomment.getText().toString());
                                    holder.mlimg.setImageDrawable(mContext.getDrawable(mContext.getResources().getIdentifier("@drawable/neutral",null,mContext.getPackageName())));

                                }
                                Log.i("Tag","Completed");
                            }
                        });
            }

        });
        holder.myreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(holder.commant.getVisibility() == View.GONE)
                {
                    holder.commant.setVisibility(View.VISIBLE);
                }else {
                    holder.commant.setVisibility(View.GONE);
                }
//                     rt.animate().translationY(100);
            }
        });

    }

    private void updateBe(String tag,String com) {
        CommentMutation commentMutation = CommentMutation.builder().tag(tag).comment(com).build();
        apolloClient.mutate(commentMutation).enqueue(new ApolloCall.Callback<CommentMutation.Data>() {
            @Override
            public void onResponse(@Nonnull Response<CommentMutation.Data> response) {
                Log.d("Tag","Comment Success");
            }

            @Override
            public void onFailure(@Nonnull ApolloException e) {

            }
        });
    }

    @Override
    public int getItemCount()
    {
        return rname.size();
    }

    public Sentiment getSentiment(String text) throws IOException{
        final CloudNaturalLanguage naturalLanguage =  new CloudNaturalLanguage.Builder(AndroidHttp.newCompatibleTransport(),new AndroidJsonFactory(),null)
                .setCloudNaturalLanguageRequestInitializer(new CloudNaturalLanguageRequestInitializer("AIzaSyD4rBPwG_8Uu0esSyXPd9UDjsvdTL1TAB4"))
                .setApplicationName("Resthouse")
                .build();
        Document document = new Document();
        document.setType("PLAIN_TEXT");
        document.setLanguage("en-US");
        document.setContent(text);
        Features features = new Features();
        features.setExtractEntities(true);
        features.setExtractDocumentSentiment(true);
        final AnnotateTextRequest request = new AnnotateTextRequest();
        request.setDocument(document);
        request.setFeatures(features);
        AnnotateTextResponse response =
                naturalLanguage.documents()
                        .annotateText(request).execute();
        entityList = response.getEntities();
        return response.getDocumentSentiment();
    }

}

