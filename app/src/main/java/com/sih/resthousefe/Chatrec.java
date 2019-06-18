package com.sih.resthousefe;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class Chatrec extends RecyclerView.ViewHolder  {







    TextView leftText,rightText;



    public Chatrec(View itemView){

        super(itemView);



        leftText = (TextView)itemView.findViewById(R.id.leftText);

        rightText = (TextView)itemView.findViewById(R.id.rightText);





    }

}