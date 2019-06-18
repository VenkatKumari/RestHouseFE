package com.sih.resthousefe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Table extends AppCompatActivity {

    private ArrayList<Model> productList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.table2);

        productList = new ArrayList<Model>();
        ListView lview = (ListView) findViewById(R.id.listview);
        listviewAdapter adapter = new listviewAdapter(this,productList);
        lview.setAdapter(adapter);
        populateList();

        adapter.notifyDataSetChanged();

        lview.setOnItemClickListener(new OnItemClickListener() {

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

    private void populateList() {

        Model item1, item2, item3, item4, item5;

        item1 = new Model("1", "Manager, General Manager", "Vacation with family", "100%");
        productList.add(item1);

        item2 = new Model("2", "Chief Yard Master", "Business work", "80%");
        productList.add(item2);

        item3 = new Model("3", "Station Supervisor", "Field work and other personal works", "60%");
        productList.add(item3);

        item4 = new Model("4", "Train Ticket Examiner,", "tour with family and friends", "50%");
        productList.add(item4);

        item5 = new Model("5", "Track Man", "Educational purpose", "20%");
        productList.add(item5);
    }
}
