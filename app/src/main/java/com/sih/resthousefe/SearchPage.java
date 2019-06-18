package com.sih.resthousefe;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class SearchPage extends AppCompatActivity {

    String[] recent = new String[2];
    //    ArrayList<String> arr = new ArrayList<String>(3);
    ArrayList<String> szones = new ArrayList<String>(50);
    //    ListView show;
    ListView zones;
    String value3;
    String value2;
    String value1;
    AutoCompleteTextView autoCompleteTextView;
    String places[]={"Central Railways","Northern Railway","Eastern Railways","South Central Railways","South Western Railways","Western Railways","South Eastern Railway",
            "Northeast Frontier Railways","North Eastern Railways","North Western Railways","West Central Railways"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_page);
        autoCompleteTextView = findViewById(R.id.auto);
        final TextView t1 = findViewById(R.id.textView1);
        final TextView t2 = findViewById(R.id.textView2);
        final TextView t3 = findViewById(R.id.textView3);

        final SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        final SharedPreferences.Editor editor = pref.edit();

        value3 = pref.getString("2",null);
        value2 = pref.getString("1",null);
        value1 = pref.getString("3",null);

        t1.setText(value2);
        t2.setText(value3);
        t3.setText(value1);


//        show = findViewById(R.id.Recent_Search);

        zones = findViewById(R.id.zones);


        szones.add("Central Railways");
        szones.add("Northern Railway");
        szones.add("Eastern Railways");
        szones.add("Southern Railways");
        szones.add("South Central Railways");
        szones.add("South Western Railways");
        szones.add("Western Railways");
        szones.add("South Eastern Railway");
        szones.add("Northeast Frontier Railways");
        szones.add("North Eastern Railways");
        szones.add("North Western Railways");
        szones.add("West Central Railways");
        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(SearchPage.this, android.R.layout.simple_list_item_1, szones);
        zones.setAdapter(arrayAdapter1);

//        places= getResources().getStringArray(R.array.places);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,places);
        autoCompleteTextView.setAdapter(adapter);
//        autoCompleteTextView.setThreshold(1);

        String PlaceName = autoCompleteTextView.getText().toString();

//        Toast.makeText(this, PlaceName, Toast.LENGTH_SHORT).show();

        Log.i("name",PlaceName);

        if (t1.equals(null)) {
            editor.putString("1",PlaceName);

            editor.commit();


            t1.setText(PlaceName);
        }else {
            value3 = pref.getString("2",null);


            String text3 = t2.getText().toString();

            editor.putString("3",value3);
            editor.commit();


            t3.setText(value3);

            value2 = pref.getString("1",null);


            String text2 = t1.getText().toString();

            editor.putString("2",value2);
            editor.commit();

            t2.setText(value2);

            editor.putString("1",PlaceName);
            editor.commit();

            t1.setText(PlaceName);



        }



//        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
//                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);
//
//        AutocompleteFilter typeFilter = new AutocompleteFilter.Builder().
//                setTypeFilter(Place.TYPE_COUNTRY).setCountry("IN").build();
//        autocompleteFragment.setFilter(typeFilter);
//
//        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
//            @Override
//            public void onPlaceSelected(Place place) {
//                // TODO: Get info about the selected place.
////                Log.i(TAG, "Place: " + place.getName());
//
//
//                String PlaceName = place.getName().toString();
//
//
//                if (t1.equals(null)) {
//                    editor.putString("1",PlaceName);
//
//                    editor.commit();
//
//
//                    t1.setText(PlaceName);
//                }else {
//                    value3 = pref.getString("2",null);
//
//
//                    String text3 = t2.getText().toString();
//
//                    editor.putString("3",value3);
//                    editor.commit();
//
//
//                    t3.setText(value3);
//
//                    value2 = pref.getString("1",null);
//
//
//                    String text2 = t1.getText().toString();
//
//                    editor.putString("2",value2);
//                    editor.commit();
//
//                    t2.setText(value2);
//
//                    editor.putString("1",PlaceName);
//                    editor.commit();
//
//                    t1.setText(PlaceName);
//
//
//
//                }



//                if (i==3){
//
//                    final List<String> list =  new ArrayList<String>();
//                    Collections.addAll(list, PlaceName);
//                    list.remove(2);
//
//                }
//                i=0;
//                if (i==0) {
//                    recent[i] = PlaceName;
//                }else if (i==1){
//                    recent[i] = PlaceName;
//                }else if (i==2){
//                    recent[2]= PlaceName;
//                }
//                i++;




//                int i=0;
//                if (PlaceName != null){
//                    arr.add(PlaceName);
//                    if (i==3) {
//                        String value = arr.get(0);
//                        String value2 = arr.get(1);
//                        String value3 = arr.get(2);
//                        arr.set(1, value);
//                        arr.set(2, value2);
//                        arr.set(3, value3);
////                    Collections.swap(arr, 0, 1);
//                        arr.remove(3);
//
//                    }
//                    i++;
//                }


//                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(SearchPage.this, android.R.layout.simple_list_item_1, arr);
//                show.setAdapter(arrayAdapter);

//                Toast.makeText(SearchPage.this, ""+ PlaceName, Toast.LENGTH_SHORT).show();

//                SharedPreferences preferences = PreferenceManager.getDefaultSharedPrefererences(this);
//                SharedPreferences.Editor editor = preferences.edit();
//                editor.putString("key","value");
//                editor.apply();
//                int i=0;
//
//                int first = 3;
//
//                SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
//                SharedPreferences.Editor editor = pref.edit();
//                if (i != 1) {
//
//                    editor.putString("3", PlaceName);
//                    editor.apply();
//                    change=1;
//
//                }
//
//                else if (i == 1){
//
//                    editor.putString("2", PlaceName);
//                    editor.apply();
//                    change = 2;
//                }else if (i==2){
//                    editor.putString("1", PlaceName);
//                    editor.apply();
//                    change = 3;
//                }
//
//                i = change;



//            }



//            @Override
//            public void onError(Status status) {
//                // TODO: Handle the error.
////                Log.i(TAG, "An error occurred: " + status);
//            }
//        });



    }
}
