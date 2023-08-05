package com.example.nutritrack.models;

public class ExerciseModel {

    private String exerciseName;
    private int calorieBurnedHourly;

    public ExerciseModel(String exerciseName, int calorieBurnedHourly) {
        this.exerciseName = exerciseName;
        this.calorieBurnedHourly = calorieBurnedHourly;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public int getCalorieBurnedHourly() {
        return calorieBurnedHourly;
    }
}
