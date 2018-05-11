package com.example.jpeng.teacherapp.model;

/**
 * Created by jpeng on 4/29/2018.
 */

public class Attendance {
    private String name;
    private String studentId;
    private String timeDate;
    private int id;

    public Attendance(){}

    public Attendance(String name, String studentId, String timeDate){
        this.name = name;
        this.studentId = studentId;
        this.timeDate = timeDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getTimeDate() {
        return timeDate;
    }

    public void setTimeDate(String timeDate) {
        this.timeDate = timeDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
