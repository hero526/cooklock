package com.example.semin.cooklock;

import android.widget.ImageView;

public class Recipe_Basic {
    private int recipe_id;
    private String recipe_name;
    private String recipe_expain;
    private String recipe_type;
    private String recipe_time;
    private String recipe_kcal;
    private String recipe_level;
    private ImageView recipe_Image;

    public Recipe_Basic(int recipe_id, String recipe_name, String recipe_expain, String recipe_type, String recipe_time, String recipe_kcal, String recipe_level, ImageView recipe_Image) {
        this.recipe_id = recipe_id;
        this.recipe_name = recipe_name;
        this.recipe_expain = recipe_expain;
        this.recipe_type = recipe_type;
        this.recipe_time = recipe_time;
        this.recipe_kcal = recipe_kcal;
        this.recipe_level = recipe_level;
        this.recipe_Image = recipe_Image;
    }

    public int getRecipe_id() {
        return recipe_id;
    }

    public void setRecipe_id(int recipe_id) {
        this.recipe_id = recipe_id;
    }

    public String getRecipe_name() {
        return recipe_name;
    }

    public void setRecipe_name(String recipe_name) {
        this.recipe_name = recipe_name;
    }

    public String getRecipe_expain() {
        return recipe_expain;
    }

    public void setRecipe_expain(String recipe_expain) {
        this.recipe_expain = recipe_expain;
    }

    public String getRecipe_type() {
        return recipe_type;
    }

    public void setRecipe_type(String recipe_type) {
        this.recipe_type = recipe_type;
    }

    public String getRecipe_time() {
        return recipe_time;
    }

    public void setRecipe_time(String recipe_time) {
        this.recipe_time = recipe_time;
    }

    public String getRecipe_kcal() {
        return recipe_kcal;
    }

    public void setRecipe_kcal(String recipe_kcal) {
        this.recipe_kcal = recipe_kcal;
    }

    public String getRecipe_level() {
        return recipe_level;
    }

    public void setRecipe_level(String recipe_level) {
        this.recipe_level = recipe_level;
    }

    public ImageView getRecipe_Image() {
        return recipe_Image;
    }

    public void setRecipe_Image(ImageView recipe_Image) {
        this.recipe_Image = recipe_Image;
    }
}
