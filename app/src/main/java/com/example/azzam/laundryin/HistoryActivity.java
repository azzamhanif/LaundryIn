package com.example.azzam.laundryin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private DatabaseReference mDatabase;
    ArrayList<ModelHistory> list;
    private String idmember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        list = new ArrayList<ModelHistory>();
        buildRecyclerView();
        Intent intent = getIntent();
        idmember = intent.getStringExtra("idmemberpass");

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Transaksi").child("member");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    if (dataSnapshot1.child("id_member").getValue().toString().equals(idmember )){
                        String berat = dataSnapshot1.child("berat").getValue().toString();
                        String harga = dataSnapshot1.child("harga").getValue().toString();
                        String paket = dataSnapshot1.child("paket").getValue().toString();
                        String tgl = dataSnapshot1.child("tanggal_masuk").getValue().toString();
                        String id_transaksi = dataSnapshot1.child("id_transaksi").getValue().toString();

                        list.add(new ModelHistory(id_transaksi,paket,berat,tgl,harga));
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(HistoryActivity.this, "Opss... Something is wrong", Toast.LENGTH_SHORT).show();

            }
        });



    }


    public void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.recycle_history);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new MyAdapter(list);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void backHome(View view) {
        startActivity(new Intent(HistoryActivity.this, HomeActivity.class).putExtra("idmemberHistory",idmember));
        finish();
    }

    public void search(View view) {
        Toast.makeText(HistoryActivity.this, "Untuk saat ini fitur belum tersedia", Toast.LENGTH_SHORT).show();
    }
}
