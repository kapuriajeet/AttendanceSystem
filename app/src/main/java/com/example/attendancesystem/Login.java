package com.example.attendancesystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText ID = findViewById(R.id.ID);
        final EditText password = findViewById(R.id.password);
        final Button loginBtn = findViewById(R.id.loginBtn);
        final TextView registerNowBtn = findViewById(R.id.registerNowBtn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String idText = ID.getText().toString();
                final String passwordText = password.getText().toString();

                if (idText.isEmpty() || passwordText.isEmpty()) {
                    Toast.makeText(Login.this, "Please enter valid ID and Password", Toast.LENGTH_SHORT).show();
                }
                else {

                }
            }
        });

        registerNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Open Register Activity
                startActivity(new Intent(Login.this, Register.class));
            }
        });
    }
}