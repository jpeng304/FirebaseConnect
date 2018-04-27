package com.example.jpeng.teacherapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.jpeng.teacherapp.model.Student;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Update_Info extends AppCompatActivity {

    ListView exerciseList;
    private String post_key;
    private String student_id;
    private EditText etname;
    private EditText etID;
    private EditText etCourse1;
    private EditText etCourse2;
    private Button btnUpdate;
    private Button btnRemove;
    private Button btnCancel;
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private DatabaseReference myRef;
    private FirebaseUser user;

    ArrayList<Student> getStudnetInfo =new ArrayList<>();
    List<String> studentList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_student);

        //Get the key of the object and the student id
        //that user wanted to look at after pressing the listview item
        //passed from the previous activity
        Intent intent = getIntent();
        post_key = intent.getExtras().getString("keyString");
        student_id = intent.getExtras().getString("studentid");
        Log.d("THE KEY: ", post_key);

        etname = (EditText) findViewById(R.id.etStudentName_update);
        etID = (EditText) findViewById(R.id.etStudentID_update);
        etCourse1 = (EditText) findViewById(R.id.etCourse1_update);
        etCourse2 = (EditText) findViewById(R.id.etCourse2_update);
        btnUpdate = (Button) findViewById(R.id.btnUpdateInfo);
        btnRemove = (Button) findViewById(R.id.btnRemove);
        btnCancel = (Button) findViewById(R.id.btnCancel);

        //declare instance and reference to the child of the root wanted to look at
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference().child("studentInfo");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // loops through all children in studentInfo to get the
                // one student info that we want by checking with student ID
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Student check_user = new Student();
                    check_user.setStudent_ID(ds.getValue(Student.class).getStudent_ID());
                    if(student_id.equals(check_user.getStudent_ID()))
                    {
                        Student get_stdInfo = new Student();
                        get_stdInfo.setStudent_name(ds.getValue(Student.class).getStudent_name());
                        get_stdInfo.setStudent_ID(ds.getValue(Student.class).getStudent_ID());
                        get_stdInfo.setStudent_course1(ds.getValue(Student.class).getStudent_course1());
                        get_stdInfo.setStudent_course2(ds.getValue(Student.class).getStudent_course2());

                        getStudnetInfo.add(get_stdInfo);
                    }

                }
                //set student info onto the UI
                setStudentlist();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("DATABASE ERROR", String.valueOf(databaseError));
            }
        });

        //Update the key in the child when UPdate button is clicked
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String stdName = etname.getText().toString();
                String stdID = etID.getText().toString();
                String courseName1 = etCourse1.getText().toString();
                String courseName2 = etCourse2.getText().toString();


                if(stdName.isEmpty() || stdID.isEmpty() || courseName1.isEmpty() || courseName2.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "All fields must have text", Toast.LENGTH_SHORT).show();
                } else {
                    Student info = new Student(stdName,stdID,courseName1,courseName2);
                    //Changed the value of the chosen key
                    myRef.child(post_key).setValue(info);
                    Intent intent = new Intent(Update_Info.this, UpdateStudent.class);
                    startActivity(intent);
                }
            }
        });

        //Remove the chosen key and its value in the child
        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myRef.child(post_key).removeValue();
                Intent intent = new Intent(Update_Info.this, UpdateStudent.class);
                startActivity(intent);
            }
        });

        //Cancel any changes and back to the listview
        btnCancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Update_Info.this, UpdateStudent.class);
                startActivity(intent);
            }
        });
    }


    //set the student info onto the UI
    private void setStudentlist(){
        etname.setText(getStudnetInfo.get(0).getStudent_name().toString());
        etID.setText(getStudnetInfo.get(0).getStudent_ID().toString());
        etCourse1.setText(getStudnetInfo.get(0).getStudent_course1().toString());
        etCourse2.setText(getStudnetInfo.get(0).getStudent_course2().toString());
    }
}
