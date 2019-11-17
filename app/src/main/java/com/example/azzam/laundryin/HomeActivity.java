package com.example.azzam.laundryin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeActivity extends AppCompatActivity {
    private DatabaseReference mdatabase;
    private TextView tvNama;
    private TextView tvidNumber;
    String id_member;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        final String email = intent.getStringExtra("emailPass");
        final String emailporf = intent.getStringExtra("emailProfile");
        final String idmemberHistory = intent.getStringExtra("idmemberHistory");
        final String idmemberTracking = intent.getStringExtra("idmemberTracking");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        tvNama = (TextView) findViewById(R.id.NamaHome);
        tvidNumber = (TextView) findViewById(R.id.idNumberHome);
        mdatabase = FirebaseDatabase.getInstance().getReference().child("Member");
        mdatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    if (dataSnapshot1.child("Email").getValue().toString().equals(email) ||
                            dataSnapshot1.child("Email").getValue().toString().equals(emailporf) ||
                            dataSnapshot1.child("id_member").getValue().toString().equals(idmemberHistory) ||
                            dataSnapshot1.child("id_member").getValue().toString().equals(idmemberTracking)){
                        String nama = dataSnapshot1.child("Nama").getValue().toString();
                        String idNumber = dataSnapshot1.child("NIK").getValue().toString();
                        id_member = dataSnapshot1.child("id_member").getValue().toString();
                        tvNama.setText(nama);
                        tvidNumber.setText(idNumber);
//                        break;
                    }
//                    else {
//                        Toast.makeText(HomeActivity.this, "Data tidak ditemukan", Toast.LENGTH_SHORT).show();
////                        break;
//                    }
                }


//                String nama = dataSnapshot.child("Nama").getValue().toString();
//                String idNumber = dataSnapshot.child("NIK").getValue().toString();
//                id_member = dataSnapshot.child("id_member").getValue().toString();
//                tvNama.setText(email);
//                tvidNumber.setText(idNumber);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void moveWA(View view) {
        Intent intent = getPackageManager().getLaunchIntentForPackage("com.whatsapp");
        startActivity(intent);
    }
    public void moveTracking(View view) {
        Intent intent = new Intent(HomeActivity.this, TrackingActivity.class);
        intent.putExtra("idmemberpass",id_member);
        startActivity(intent);
    }
    public void moveProfile(View view) {
        Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);
        intent.putExtra("idmemberpass",id_member);
        startActivity(intent);
    }
    public void moveHistory(View view) {
        Intent intent = new Intent(HomeActivity.this, HistoryActivity.class);
        intent.putExtra("idmemberpass",id_member);
        startActivity(intent);
    }

    public void keluar(View view) {
        startActivity(new Intent(HomeActivity.this,LoginActivity.class));
        finish();
    }
}
