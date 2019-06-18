package com.sih.resthousefe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

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

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class MlActivity extends AppCompatActivity {

    @BindView(R.id.text1)EditText editText;
    @BindView(R.id.go)Button button;
    @BindView(R.id.textv)TextView textView;
    @BindView(R.id.img)ImageView imageView;
    @BindView(R.id.rb1)RadioButton radioButton1;
    @BindView(R.id.rb2)RadioButton radioButton2;
    @BindView(R.id.rb3)RadioButton radioButton3;
    Observable<String> observable;
    List<Entity> entityList = null;
    Sentiment sentiment;
    String text,entities;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ml);
        ButterKnife.bind(this);
        radioButton1.setOnClickListener(v ->{
            editText.setText(radioButton1.getText());
        });
        radioButton2.setOnClickListener(v ->{
            editText.setText(radioButton2.getText());
        });
        radioButton3.setOnClickListener(v ->{
            editText.setText(radioButton3.getText());
        });
        button.setOnClickListener(v -> {
            text = editText.getText().toString();
            observable = Observable.create(d -> {
                try{
                   sentiment =  getSentiment(text);
                }catch (IOException e){
                    Log.d("TAg","Error"+e);
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
                                textView.setText(entities);
                                imageView.setImageDrawable(getDrawable(getResources().getIdentifier("@drawable/happy",null,getPackageName())));
                            }
                            else if(sentiment.getScore() <= -0.6 && sentiment.getMagnitude() >= 0.4){
                                Log.d("Negative","Post to Admin");
                                textView.setText(entities);
                                imageView.setImageDrawable(getDrawable(getResources().getIdentifier("@drawable/sad",null,getPackageName())));
                            }
                            else {
                                Log.d("Neutral","No Action");
                                textView.setText(entities);
                                imageView.setImageDrawable(getDrawable(getResources().getIdentifier("@drawable/neutral",null,getPackageName())));
                            }
                            Log.i("Tag","Completed");
                        }
                    });

        });

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
