package com.example.jpeng.teacherapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jpeng.teacherapp.model.Student;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class AddStudent extends AppCompatActivity {
    private DatabaseReference databaseReference;

    private FirebaseAuth.AuthStateListener authListener;

    private FirebaseAuth mAuth;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;
    private DatabaseReference mdatabase;
    private FirebaseUser user;
    private Button btnAddInfo;
    private EditText etStudentName;
    private EditText etStudentID;
    private EditText etCourse1;
    private EditText etCourse2;

    String usermail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference().child("studentInfo");
        user = FirebaseAuth.getInstance().getCurrentUser();
        usermail = user.getEmail();
        btnAddInfo = (Button) findViewById(R.id.btnAddInfo);
        etStudentName = (EditText) findViewById(R.id.etStudentName);
        etStudentID = (EditText) findViewById(R.id.etStudentID);
        etCourse1 = (EditText) findViewById(R.id.etCourse1);
        etCourse2 = (EditText) findViewById(R.id.etCourse2);


        btnAddInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String stdName = etStudentName.getText().toString();
                String stdID = etStudentID.getText().toString();
                String courseName1 = etCourse1.getText().toString();
                String courseName2 = etCourse2.getText().toString();

                //Check if every text is filled.
                //If not then it will output message.
                //If everything is filled, then push the object into the Firebase
                if(stdName.isEmpty() || stdID.isEmpty() || courseName1.isEmpty() || courseName2.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "All fields must have text", Toast.LENGTH_SHORT).show();
                } else {
                    Student info = new Student(stdName,stdID,courseName1,courseName2);
                    myRef.push().setValue(info);

                    etStudentName.setText(null);
                    etStudentID.setText(null);
                    etCourse1.setText(null);
                    etCourse2.setText(null);
                }
            }
        });



    }

}
