package com.example.nutritrack.models;

public class RecipeModel {
    private String title;
    private String servings;
    private String ingredients;
    private String instructions;

    public RecipeModel(String title, String servings, String ingredients, String instructions) {
        this.title = title;
        this.servings = servings;
        this.ingredients = ingredients;
        this.instructions = instructions;
    }

    public String getTitle() {
        return title;
    }

    public String getServings() {
        return servings;
    }

    public String getIngredients() {
        return ingredients;
    }

    public String getInstructions() {
        return instructions;
    }
}
