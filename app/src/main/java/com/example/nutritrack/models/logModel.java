package com.example.nutritrack.models;

public class logModel {

    String foodName;

    double foodCalorie;

    public logModel(String foodName, double foodCalorie) {
        this.foodName = foodName;
        this.foodCalorie = foodCalorie;
    }

    public String getFoodName() {
        return foodName;
    }

    public Double getFoodCalorie() {
        return foodCalorie;
    }
}
