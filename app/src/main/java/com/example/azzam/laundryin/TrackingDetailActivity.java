package com.example.azzam.laundryin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class TrackingDetailActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private String idmember;
    private TextView idtrasnaksiDetail;
    private Button btn_antar;
    private Button btn_done;

    private DatabaseReference mDatabase;
    ArrayList<ModelTrackingDetail> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracking_detail);
        idtrasnaksiDetail = findViewById(R.id.id_transaksidetail);
        btn_antar = findViewById(R.id.button_antar);
        btn_done = findViewById(R.id.button_done);

        list = new ArrayList<ModelTrackingDetail>();
        buildRecyclerView();
        Intent intent = getIntent();
        Intent intent1 = getIntent();
        idmember = intent.getStringExtra("idmemberDetail");
        String idTransaksi = intent1.getStringExtra("idTransaksiDetail").toString();
        idtrasnaksiDetail.setText("Id Transaction : "+idTransaksi);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Transaksi").child("member").child(idTransaksi).child("statusJalan");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    String statusJalan = dataSnapshot1.getValue().toString();
                    String deskirpsi = "Pakaian anda berada pada proses "+statusJalan;
                    list.add(new ModelTrackingDetail(statusJalan, deskirpsi));

                    if (dataSnapshot1.getValue().toString().equals("Siap Antar")){
                        btn_antar.setEnabled(true);
                        btn_done.setEnabled(false);
                        btn_done.setBackgroundResource(R.drawable.custom_button5);
                        btn_antar.setBackgroundResource(R.drawable.custom_button);
                    }
                    if (dataSnapshot1.getValue().toString().equals("Selesai")){
                        btn_done.setEnabled(true);
                        btn_done.setBackgroundResource(R.drawable.custom_button4);
                        btn_antar.setEnabled(false);
                        btn_antar.setBackgroundResource(R.drawable.custom_button5);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(TrackingDetailActivity.this, "Opss... Something is wrong", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.tracking_detail);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new MyAdapter3(list);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }


    public void detailTracking(View view) {
        Toast.makeText(TrackingDetailActivity.this, "Fitur dalam tahap pengembangan", Toast.LENGTH_SHORT).show();
    }

    public void backHome(View view) {
        startActivity(new Intent(TrackingDetailActivity.this, TrackingActivity.class).putExtra("idmemberpass",idmember));
        finish();
    }
}
