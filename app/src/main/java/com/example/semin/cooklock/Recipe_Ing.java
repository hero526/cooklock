package com.example.semin.cooklock;

public class Recipe_Ing {
    private int recipe_id;
    private String recipe_ingri_name;
    private String recipe_ingri_quantity;
    private String recipe_ingri_type;

    public Recipe_Ing(int recipe_id, String recipe_ingri_name, String recipe_ingri_quantity, String recipe_ingri_type) {
        this.recipe_id = recipe_id;
        this.recipe_ingri_name = recipe_ingri_name;
        this.recipe_ingri_quantity = recipe_ingri_quantity;
        this.recipe_ingri_type = recipe_ingri_type;
    }

    public int getRecipe_id() {
        return recipe_id;
    }

    public void setRecipe_id(int recipe_id) {
        this.recipe_id = recipe_id;
    }

    public String getRecipe_ingri_name() {
        return recipe_ingri_name;
    }

    public void setRecipe_ingri_name(String recipe_ingri_name) {
        this.recipe_ingri_name = recipe_ingri_name;
    }

    public String getRecipe_ingri_quantity() {
        return recipe_ingri_quantity;
    }

    public void setRecipe_ingri_quantity(String recipe_ingri_quantity) {
        this.recipe_ingri_quantity = recipe_ingri_quantity;
    }

    public String getRecipe_ingri_type() {
        return recipe_ingri_type;
    }

    public void setRecipe_ingri_type(String recipe_ingri_type) {
        this.recipe_ingri_type = recipe_ingri_type;
    }
}
