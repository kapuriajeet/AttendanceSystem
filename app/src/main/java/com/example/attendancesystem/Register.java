package com.example.attendancesystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class Register extends AppCompatActivity {

    RadioButton roleRadioButton;
    // Create object of DatabaseReference class to access firebase's realtime Database
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://attendance-7ef40-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText name = findViewById(R.id.name);
        final EditText phone = findViewById(R.id.phone);
        final EditText ID = findViewById(R.id.ID);
        final EditText password = findViewById(R.id.password);
        final EditText conPassword = findViewById(R.id.conPassword);

        final Button registerBtn = findViewById(R.id.registerBtn);
        final TextView loginNowBtn = findViewById(R.id.loginNowBtn);

        final RadioGroup rgRole = findViewById(R.id.rgRole);


        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Get Data from EditTexts into String Variables
                final String nameText = name.getText().toString();
                final String phoneText = phone.getText().toString();
                final String idText = ID.getText().toString();
                final String passwordText = password.getText().toString();
                final String conPasswordText = conPassword.getText().toString();

                int selectedRadioButtonId = rgRole.getCheckedRadioButtonId();
                roleRadioButton = (RadioButton) findViewById(selectedRadioButtonId);


                // Check if user has filled all the fields before sending data to firebase
                if(nameText.isEmpty() || phoneText.isEmpty() || idText.isEmpty() || passwordText.isEmpty() || conPasswordText.isEmpty() || selectedRadioButtonId == -1) {
                    Toast.makeText(Register.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                }
                // Check if passwordText and conPasswordText are same or not
                else if (!passwordText.equals(conPasswordText)) {
                    Toast.makeText(Register.this, "Passwords are not matching", Toast.LENGTH_SHORT).show();
                }
                else {

                    if(roleRadioButton.getText().equals("Student")){
                        databaseReference.child("Students").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {

                                // Check if ID is not registered before

                                if(snapshot.hasChild(idText)) {
                                    Toast.makeText(Register.this, "ID is already registered", Toast.LENGTH_SHORT).show();
                                }
                                else {

                                    //Storing data to firebase Realtime Database
                                    // We are using ID as unique identity of every user
                                    // So all other details of user comes under ID
                                    databaseReference.child("Students").child(idText).child("name").setValue(nameText);
                                    databaseReference.child("Students").child(idText).child("phone").setValue(phoneText);
                                    databaseReference.child("Students").child(idText).child("password").setValue(passwordText);
                                    // Show a success message
                                    Toast.makeText(Register.this, "User Registered Successfully", Toast.LENGTH_SHORT).show();
                                    finish();
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

                                // Check if ID is not registered before

                                if(snapshot.hasChild(idText)) {
                                    Toast.makeText(Register.this, "ID is already registered", Toast.LENGTH_SHORT).show();
                                }
                                else {

                                    //Storing data to firebase Realtime Database
                                    // We are using ID as unique identity of every user
                                    // So all other details of user comes under ID
                                    databaseReference.child("Faculties").child(idText).child("name").setValue(nameText);
                                    databaseReference.child("Faculties").child(idText).child("phone").setValue(phoneText);
                                    databaseReference.child("Faculties").child(idText).child("password").setValue(passwordText);
                                    // Show a success message
                                    Toast.makeText(Register.this, "User Registered Successfully", Toast.LENGTH_SHORT).show();
                                    finish();
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

        loginNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}