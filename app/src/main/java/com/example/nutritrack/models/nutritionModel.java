package com.example.nutritrack.models;

public class nutritionModel {

    private int food_quantity;
    private String food_unit; //cup, ounce, gram - can also be blank
    private String food_name; // apple, rice, chickpeas etc.

    private double calories;
    private double total_fat; //gram
    private double saturated_fat; //gram

    private double cholesterol; //gram
    private double sodium;

    private double total_carbonhydrate; //gram
    private double dietary_fiber ; //gram
    private double total_sugars; //gram

    private double protein; //gram

    private double calcium; //gram
    private double iron; //gram
    private double potassium; //gram

    public nutritionModel() {
    }

    public nutritionModel( String food_name, double calories, double total_fat,
                          double saturated_fat, double cholesterol, double sodium, double total_carbonhydrate,
                          double dietary_fiber, double total_sugars, double protein, double calcium,
                          double iron, double potassium) {

        this.food_name = food_name;
        this.calories = calories;
        this.total_fat = total_fat;
        this.saturated_fat = saturated_fat;
        this.cholesterol = cholesterol;
        this.sodium = sodium;
        this.total_carbonhydrate = total_carbonhydrate;
        this.dietary_fiber = dietary_fiber;
        this.total_sugars = total_sugars;
        this.protein = protein;
        this.calcium = calcium;
        this.iron = iron;
        this.potassium = potassium;
    }

    public nutritionModel(int food_quantity, String food_unit, String food_name, double calories,
                          double total_fat, double saturated_fat, double cholesterol, double sodium,
                          double total_carbonhydrate, double dietary_fiber, double total_sugars,
                          double protein,  double calcium, double iron, double potassium) {
        this.food_quantity = food_quantity;
        this.food_unit = food_unit;
        this.food_name = food_name;
        this.calories = calories;
        this.total_fat = total_fat;
        this.saturated_fat = saturated_fat;
        this.cholesterol = cholesterol;
        this.sodium = sodium;
        this.total_carbonhydrate = total_carbonhydrate;
        this.dietary_fiber = dietary_fiber;
        this.total_sugars = total_sugars;
        this.protein = protein;

        this.calcium = calcium;
        this.iron = iron;
        this.potassium = potassium;
    }

    public int getFood_quantity() {
        return food_quantity;
    }

    public String getFood_unit() {
        return food_unit;
    }

    public String getFood_name() {
        return food_name;
    }

    public double getCalories() {
        return calories;
    }

    public double getTotal_fat() {
        return total_fat;
    }

    public double getSaturated_fat() {
        return saturated_fat;
    }

    public double getCholesterol() {
        return cholesterol;
    }

    public double getSodium() {
        return sodium;
    }

    public double getTotal_carbonhydrate() {
        return total_carbonhydrate;
    }

    public double getDietary_fiber() {
        return dietary_fiber;
    }

    public double getTotal_sugars() {
        return total_sugars;
    }

    public double getProtein() {
        return protein;
    }


    public double getCalcium() {
        return calcium;
    }

    public double getIron() {
        return iron;
    }

    public double getPotassium() {
        return potassium;
    }
}
