package com.example.azzam.laundryin;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by azzam on 9/15/2019.
 */

public class MyAdapter3 extends RecyclerView.Adapter<MyAdapter3.ExampleViewHolder> {
    private ArrayList<ModelTrackingDetail> mExampleList;

    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.tracking_detail_items,parent,false);
        ExampleViewHolder evh = new ExampleViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(ExampleViewHolder holder, int position) {
        ModelTrackingDetail currentItem = mExampleList.get(position);
        holder.status.setText(currentItem.getStatus());
        holder.desc.setText(currentItem.getDesc());

        if (holder.status.getText().toString().equals("Cuci")){
            holder.panah_Atas.setVisibility(View.VISIBLE);
        }
        if (holder.status.getText().toString().equals("Selesai")){
            holder.panah_Bawah.setVisibility(View.GONE);
            holder.desc.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }


    public static class ExampleViewHolder extends RecyclerView.ViewHolder{
        public TextView status;
        public TextView desc;
        public ImageView panah_Atas;
        public ImageView panah_Bawah;
//        public Button btn_antar;
//        public  Button btn_done;

        public ExampleViewHolder(View itemView) {
            super(itemView);
            status= itemView.findViewById(R.id.status);
            desc = itemView.findViewById(R.id.deskripsi);
            panah_Atas = itemView.findViewById(R.id.panahAtas);
            panah_Bawah = itemView.findViewById(R.id.panahBawah);
//            btn_antar = itemView.findViewById(R.id.button_antar);
//            btn_done = itemView.findViewById(R.id.button_done);
        }
    }

    public MyAdapter3(ArrayList<ModelTrackingDetail> exampleList) {
        mExampleList = exampleList;
    }
}
