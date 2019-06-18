package com.sih.resthousefe;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class PassengerAdapter extends RecyclerView.Adapter<PassengerAdapter.MyViewHolder> {

    private List<Passengers> passList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView sno, roomno, passname;

        public MyViewHolder(View view) {
            super(view);
            sno = (TextView) view.findViewById(R.id.sno);
            roomno = (TextView) view.findViewById(R.id.roomno);
            passname = (TextView) view.findViewById(R.id.passname);
        }
    }


    public PassengerAdapter(List<Passengers> passList) {
        this.passList = passList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycle, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Passengers movie = passList.get(position);
        holder.sno.setText(movie.getSno());
        holder.roomno.setText(movie.getRoomno());
        holder.passname.setText(movie.getPassname());
    }

    @Override
    public int getItemCount() {
        return passList.size();
    }
}
