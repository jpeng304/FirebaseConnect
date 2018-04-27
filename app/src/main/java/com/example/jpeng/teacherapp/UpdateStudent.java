package com.example.jpeng.teacherapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.jpeng.teacherapp.adapter.ListStudentInfo;
import com.example.jpeng.teacherapp.model.Student;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UpdateStudent extends AppCompatActivity {


    private ListView studentListView;
    private ListStudentInfo Sadapter;
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private DatabaseReference myRef;
    private FirebaseUser user;
    private Button btnReturn;
    ArrayList <String> clubkey = new ArrayList<>();
    ArrayList<Student> getStudent = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_listview);

        studentListView = (ListView)findViewById(R.id.stdlistview);
        btnReturn = (Button) findViewById(R.id.btnReturn);

        //Get the instance and reference of the child
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference().child("studentInfo");

        //Store the value from Firebase to a generic arraylist
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // loops through all children in studentInfo table
                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    Student get_Info = new Student();
                    get_Info.setStudent_name(ds.getValue(Student.class).getStudent_name());
                    get_Info.setStudent_ID(ds.getValue(Student.class).getStudent_ID());
                    get_Info.setStudent_course1(ds.getValue(Student.class).getStudent_course1());
                    get_Info.setStudent_course2(ds.getValue(Student.class).getStudent_course2());

                    clubkey.add(ds.getKey());
                    getStudent.add(get_Info);
                }
                //set listview to adapter
                createStudentlist();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("DATABASE ERROR", String.valueOf(databaseError));
            }
        });


        //when one of the listview item is pressed, pass the key and studentid to the next activity
        studentListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView nametextView = (TextView) view.findViewById(R.id.txtName);
                TextView idTextView = (TextView) view.findViewById(R.id.txtID);
                String Sname = nametextView.getText().toString();    //get the text of the string
                String Sid = idTextView.getText().toString();
                String key = clubkey.get(i);

                Intent intent = new Intent(UpdateStudent.this, Update_Info.class);
                intent.putExtra("keyString", key);
                intent.putExtra("studentid", Sid);
                startActivity(intent);

            }
        });

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UpdateStudent.this, AccountActivity.class);
                startActivity(intent);
            }
        });
    }

    //set the listview with the Sadapter
    private void createStudentlist() {
        Sadapter = new ListStudentInfo(this, getStudent);
        studentListView.setAdapter(Sadapter);
    }
}
