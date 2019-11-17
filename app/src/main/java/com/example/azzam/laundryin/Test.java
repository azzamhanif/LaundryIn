package com.example.azzam.laundryin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Test extends AppCompatActivity {
    private TextView text;
    private ImageView gambar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test1);

        text = findViewById(R.id.testhere);
        gambar = findViewById(R.id.gambar);
        gambar.setVisibility(View.GONE);
        Intent intent = getIntent();
        String textBaru = intent.getStringExtra("idTransaksiDetail");
        text.setText(textBaru);
    }
}
