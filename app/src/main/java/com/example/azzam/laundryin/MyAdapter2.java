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

public class MyAdapter2 extends RecyclerView.Adapter<MyAdapter2.ExampleViewHolder> {
    private ArrayList<ModelTracking> mExampleList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.tracking_items,parent,false);
        ExampleViewHolder evh = new ExampleViewHolder(v,mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(ExampleViewHolder holder, int position) {
        ModelTracking currentitem = mExampleList.get(position);
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

        public ExampleViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            tanggal = itemView.findViewById(R.id.tanggal);
            harga = itemView.findViewById(R.id.harga);
            paket = itemView.findViewById(R.id.paket);
            berat = itemView.findViewById(R.id.berat);
            id_transaksi = itemView.findViewById(R.id.idtransaksi);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    public MyAdapter2(ArrayList<ModelTracking> exampleList) {
        mExampleList = exampleList;
    }
}
