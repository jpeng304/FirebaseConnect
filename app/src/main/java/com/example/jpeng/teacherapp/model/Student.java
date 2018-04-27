package com.example.jpeng.teacherapp.model;

import java.io.Serializable;

/**
 * Created by jpeng on 4/25/2018.
 */

public class Student implements Serializable {
    private String student_name;
    private String student_ID;
    private boolean student_attendance;
    private String student_course1;
    private String student_course2;
    private int id;
    //private String student_course3;

    public Student(){

    }

    public Student(String student_name, String student_ID, String student_course1, String student_course2){
        this.student_name = student_name;
        this.student_ID = student_ID;
        this.student_course1 = student_course1;
        this.student_course2 = student_course2;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getStudent_ID() {
        return student_ID;
    }

    public void setStudent_ID(String student_ID) {
        this.student_ID = student_ID;
    }

    public String getStudent_course1() {
        return student_course1;
    }

    public void setStudent_course1(String student_course1) {
        this.student_course1 = student_course1;
    }

    public String getStudent_course2() {
        return student_course2;
    }

    public void setStudent_course2(String student_course2) {
        this.student_course2 = student_course2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    /*
    public String getStudent_course3() {
        return student_course3;
    }

    public void setStudent_course3(String student_course3) {
        this.student_course3 = student_course3;
    }*/
}
