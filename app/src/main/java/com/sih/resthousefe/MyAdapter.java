package com.sih.resthousefe;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{



    private List<String> rname,rcontact,rid,rdetail,rlocation,ravail;
    private static Context mcon;


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.imgd)
        ImageView imgvd;
        @BindView(R.id.Named)
        TextView namedd;
        @BindView(R.id.Addressd)
        TextView addressdd;
        @BindView(R.id.avail)
        TextView availdd;
        @BindView(R.id.ddetail)
        TextView detaildd;
        @BindView(R.id.Contactd)
        TextView contactdd;
        @BindView(R.id.myid)
        TextView id1;



        //   public ImageView imgvd;


        public MyViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            ButterKnife.bind(this,view);
        }

        public void MyAdapter(Context con)
        {
            mcon = con;
        }

        @Override
        public void onClick(View v) {
            Log.d("tag","Clicked"+v.getId());
            String dname = ((TextView)v.findViewById(R.id.myid)).getText().toString();
            Integer num = Integer.parseInt(dname);
//          int dname =  Integer.parseInt((TextView)v.findViewById(R.id.myid).getText().toString());
            Intent intent = new Intent(mcon, SuiteDetail.class);
            intent.putExtra("SUITE_ID", num);
            mcon.startActivity(intent);
        }
    }


    public MyAdapter(List<String> myid,List<String> myName,List<String> myAddress,List<String> myContact,List<String> myAvail,List<String> myDetail) {

        rid=myid;
        rname = myName;
        rlocation = myAddress;
        rcontact = myContact;
        ravail = myAvail;
        rdetail = myDetail;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listd, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.namedd.setText(rname.get(position));
        holder.addressdd.setText(rlocation.get(position));
        holder.contactdd.setText(rcontact.get(position));
        holder.availdd.setText(ravail.get(position));
        holder.detaildd.setText(rdetail.get(position));
        holder.id1.setText(rid.get(position));
        mcon = holder.id1.getContext();
        Picasso.with(holder.imgvd.getContext())
                .load("http://photos.wikimapia.org/p/00/02/02/51/47_big.jpg")
                .resize(440,200)
                .into(holder.imgvd);

    }

    @Override
    public int getItemCount()
    {
        return rname.size();
    }

}

