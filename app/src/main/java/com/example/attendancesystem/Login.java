package com.example.attendancesystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class Login extends AppCompatActivity {

    RadioButton roleRadioButton;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://attendance-7ef40-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText ID = findViewById(R.id.ID);
        final EditText password = findViewById(R.id.password);
        final Button loginBtn = findViewById(R.id.loginBtn);
        final TextView registerNowBtn = findViewById(R.id.registerNowBtn);
        final RadioGroup rgRole = findViewById(R.id.rgRole);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String idText = ID.getText().toString();
                final String passwordText = password.getText().toString();

                int selectedRadioButtonId = rgRole.getCheckedRadioButtonId();
                roleRadioButton = (RadioButton) findViewById(selectedRadioButtonId);

                if (idText.isEmpty() || passwordText.isEmpty() || selectedRadioButtonId == -1) {
                    Toast.makeText(Login.this, "Please enter valid ID and Password", Toast.LENGTH_SHORT).show();
                }
                else {
                    if(roleRadioButton.getText().equals("Student")){
                        databaseReference.child("Students").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {

                                // Check is ID is already existing or not
                                if(snapshot.hasChild(idText)){

                                    // ID already exits.
                                    // Now match password with the password stored in the database

                                    final String getPassword = snapshot.child(idText).child("password").getValue(String.class);

                                    if(getPassword.equals(passwordText)){
                                        Toast.makeText(Login.this, "Successfully logged in Student", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(Login.this, MainActivity.class));
                                        finish();
                                    }
                                    else{
                                        Toast.makeText(Login.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                else {
                                    Toast.makeText(Login.this, "Wrong ID", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                    else if(roleRadioButton.getText().equals("Faculty")){
                        databaseReference.child("Faculties").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {

                                // Check is ID is already existing or not
                                if(snapshot.hasChild(idText)){

                                    // Now match password with the password stored in the database

                                    final String getPassword = snapshot.child(idText).child("password").getValue(String.class);

                                    if(getPassword.equals(passwordText)){
                                        Toast.makeText(Login.this, "Successfully logged in Faculty", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(Login.this, FacultyClassesList.class));
                                        finish();
                                    }
                                    else{
                                        Toast.makeText(Login.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                else {
                                    Toast.makeText(Login.this, "Wrong ID", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
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