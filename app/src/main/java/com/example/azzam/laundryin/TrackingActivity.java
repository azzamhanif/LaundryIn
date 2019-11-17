package com.example.azzam.laundryin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class TrackingActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private MyAdapter2 mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private String idmember;

    private DatabaseReference mDatabase;
    private ArrayList<ModelTracking> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracking);

        list = new ArrayList<ModelTracking>();
        buildRecyclerView();
        Intent intent = getIntent();
        idmember = intent.getStringExtra("idmemberpass");

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Transaksi").child("member");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    if (dataSnapshot1.child("id_member").getValue().toString().equals(idmember)){
                        String berat = dataSnapshot1.child("berat").getValue().toString();
                        String harga = dataSnapshot1.child("harga").getValue().toString();
                        String paket = dataSnapshot1.child("paket").getValue().toString();
                        String tgl = dataSnapshot1.child("tanggal_masuk").getValue().toString();
                        String id_transaksi = dataSnapshot1.child("id_transaksi").getValue().toString();

                        list.add(new ModelTracking(id_transaksi,paket,berat,tgl,harga));
                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(TrackingActivity.this, "Opss... Something is wrong", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.recycle_tracking);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new MyAdapter2(list);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new MyAdapter2.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                String idTransaksi = list.get(position).getId_transaksi();
//                startActivity(new Intent(TrackingActivity.this, Test.class).putExtra("idTransaksiDetail",idTransaksi));
                startActivity(new Intent(TrackingActivity.this, TrackingDetailActivity.class).putExtra("idTransaksiDetail",idTransaksi).putExtra("idmemberDetail",idmember));
            }
        });
    }

    public void backHome(View view) {
        startActivity(new Intent(TrackingActivity.this, HomeActivity.class).putExtra("idmemberTracking",idmember));
        finish();
    }

//    public void detailTracking(View view) {
//        Toast.makeText(TrackingActivity.this, "Fitur dalam tahap pengembangan", Toast.LENGTH_SHORT).show();
//    }
}
