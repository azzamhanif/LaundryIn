package com.example.azzam.laundryin;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by azzam on 9/15/2019.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ExampleViewHolder> {
    private ArrayList<ModelHistory> mExampleList;

    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_items,parent,false);
        ExampleViewHolder evh = new ExampleViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(ExampleViewHolder holder, int position) {
        ModelHistory currentitem = mExampleList.get(position);
        holder.berat.setText("Weight : "+currentitem.getBerat()+" Kg");
        holder.tanggal.setText(currentitem.getTanggal_masuk());
        holder.harga.setText("Price : Rp "+currentitem.getHarga());
        holder.paket.setText("Package : "+currentitem.getPaket());
        holder.id_transaksi.setText("Id Transaction : "+currentitem.getId_transaksi());
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }

    public static class ExampleViewHolder extends RecyclerView.ViewHolder{
        public TextView tanggal;
        public TextView harga;
        public TextView paket;
        public TextView berat;
        public TextView id_transaksi;

        public ExampleViewHolder(View itemView) {
            super(itemView);
            tanggal = itemView.findViewById(R.id.tv_tgl);
            harga = itemView.findViewById(R.id.tv_harga);
            paket = itemView.findViewById(R.id.tv_paket);
            berat = itemView.findViewById(R.id.tv_berat);
            id_transaksi = itemView.findViewById(R.id.tv_id_transaksi);
        }
    }

    public MyAdapter(ArrayList<ModelHistory> exampleList) {
        mExampleList = exampleList;
    }
}
