package com.example.attendancesystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyFacultyAdapter extends RecyclerView.Adapter<MyFacultyAdapter.MyViewHolder> {

    Context context;

    ArrayList<FacultyClasses> FacultyList;

    public MyFacultyAdapter(Context context, ArrayList<FacultyClasses> facultyList) {
        this.context = context;
        FacultyList = facultyList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        FacultyClasses facultyClasses = FacultyList.get(position);
        holder.SubjectCode.setText(facultyClasses.getSubjectCode());
        holder.SubjectName.setText(facultyClasses.getSubjectName());
    }

    @Override
    public int getItemCount() {
        return FacultyList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView SubjectCode, SubjectName;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            SubjectCode = itemView.findViewById(R.id.facultySubjectCode);
            SubjectName = itemView.findViewById(R.id.facultySubjectName);

        }
    }

}
