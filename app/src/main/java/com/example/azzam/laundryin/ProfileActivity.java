package com.example.azzam.laundryin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {

    private DatabaseReference mDatabaseName;
    private TextView tvprofileName;
    private TextView tvUsername;
    private TextView tvemail;
    private TextView tvfullName;
    private TextView tvidNumber;
    private TextView tvtglLahir;
    private TextView tvalamat;
    private TextView tvnoTlpn;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Intent intent = getIntent();
        final String idmember = intent.getStringExtra("idmemberpass");

        tvprofileName = (TextView) findViewById(R.id.tv_profileName);
        tvfullName = (TextView) findViewById(R.id.tv_fullName);
//        tvUsername= (TextView) findViewById(R.id.tv_username);
        tvemail = (TextView) findViewById(R.id.tv_email);
        tvidNumber = (TextView) findViewById(R.id.tv_idNumber);
//        tvtglLahir = (TextView) findViewById(R.id.tv_tglLahir);
        tvalamat = (TextView) findViewById(R.id.tv_alamat);
        tvnoTlpn = (TextView) findViewById(R.id.tv_noTlpn);

        //Firebase
        mDatabaseName = FirebaseDatabase.getInstance().getReference().child("Member").child(idmember);

        mDatabaseName.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String nama = dataSnapshot.child("Nama").getValue().toString();
                email = dataSnapshot.child("Email").getValue().toString();
                String alamat = dataSnapshot.child("Alamat").getValue().toString();
                String idNum = dataSnapshot.child("NIK").getValue().toString();
                String notlpn = dataSnapshot.child("No_telp").getValue().toString();
//                String tgl_lahir = dataSnapshot.child("tgl_lahir").getValue().toString();
//                String username = dataSnapshot.child("username").getValue().toString();

                tvprofileName.setText(nama);
                tvfullName.setText(nama);
                tvemail.setText(email);
                tvalamat.setText(alamat);
                tvidNumber.setText(idNum);
                tvnoTlpn.setText(notlpn);
//                tvtglLahir.setText(tgl_lahir);
//                tvUsername.setText(username);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void backHome(View view) {
        startActivity(new Intent(ProfileActivity.this, HomeActivity.class).putExtra("emailProfile", email));
        finish();
    }
}
