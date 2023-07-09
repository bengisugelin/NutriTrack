package com.example.nutritrack.models;

import java.sql.Date;

public class UserModel {

    private int id;
    private String fname;
    private String lname;

    private String username;
    private String password;

    private Date dob;
    private String email;
    private double height;  //cm
    private double weight;  //kg

    private String activityLevel; //sedentary, active etc.


    public UserModel(int id, String fname, String lname, String username, String password, Date dob, String email, double height, double weight, String activitytype) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.username = username;
        this.password = password;
        this.dob = dob;
        this.email = email;
        this.height = height;
        this.weight = weight;
        this.activityLevel = activitytype;
    }

    public UserModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getActivityLevel() {
        return activityLevel;
    }

    public void setActivityLevel(String activityLevel) {
        this.activityLevel = activityLevel;
    }


    @Override
    public String toString() {
        return "UserModel{" +
                "id=" + id +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", dob=" + dob +
                ", email='" + email + '\'' +
                ", height=" + height +
                ", weight=" + weight +
                ", activitytype='" + activityLevel + '\'' +
                '}';
    }
}
