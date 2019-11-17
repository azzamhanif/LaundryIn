package com.example.azzam.laundryin;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private TextView tvEmail;
    private TextView tvPass;
    private static String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        tvEmail = (TextView) findViewById(R.id.tv_email);
        tvPass = (TextView) findViewById(R.id.tv_pass);

    }

    public void masuk(View view) {
        email = tvEmail.getText().toString().trim();
        String password = tvPass.getText().toString().trim();
        if (TextUtils.isEmpty(email)){
            Toast.makeText(LoginActivity.this, "Please enter e-mail", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)){
            Toast.makeText(LoginActivity.this, "Please enter password", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                            intent.putExtra("emailPass", email);
                            startActivity(intent);
                            tvEmail.setText("");
                            tvPass.setText("");
                        } else {
                            Toast.makeText(LoginActivity.this, "Email and Password are not registered", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }

    public void register(View view) {
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
    }
}
