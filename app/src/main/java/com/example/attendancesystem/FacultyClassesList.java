package com.example.attendancesystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FacultyClassesList extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://attendance-7ef40-default-rtdb.firebaseio.com/");
    MyFacultyAdapter myFacultyAdapter;
    ArrayList<FacultyClasses> FacultyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_classes_list);

        recyclerView = findViewById(R.id.facultyClassList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FacultyList = new ArrayList<>();

        myFacultyAdapter = new MyFacultyAdapter(this, FacultyList);
        recyclerView.setAdapter(myFacultyAdapter);

        databaseReference.child("FacultyClassesList").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                for(DataSnapshot dataSnapshot : snapshot.getChildren()){

                    FacultyClasses facultyClasses = dataSnapshot.getValue(FacultyClasses.class);
                    FacultyList.add(facultyClasses);
                    System.out.print(FacultyList);

                }

                myFacultyAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}