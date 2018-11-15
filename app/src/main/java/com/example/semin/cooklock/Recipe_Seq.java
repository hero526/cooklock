package com.example.semin.cooklock;

import android.widget.ImageView;

public class Recipe_Seq {
    private int recipe_id;
    private int recipe_session;
    private String recipe_display;
    private ImageView recipe_session_Image;

    public Recipe_Seq(int recipe_id, int recipe_session, String recipe_display, ImageView recipe_session_Image) {
        this.recipe_id = recipe_id;
        this.recipe_session = recipe_session;
        this.recipe_display = recipe_display;
        this.recipe_session_Image = recipe_session_Image;
    }

    public int getRecipe_id() {
        return recipe_id;
    }

    public void setRecipe_id(int recipe_id) {
        this.recipe_id = recipe_id;
    }

    public int getRecipe_session() {
        return recipe_session;
    }

    public void setRecipe_session(int recipe_session) {
        this.recipe_session = recipe_session;
    }

    public String getRecipe_display() {
        return recipe_display;
    }

    public void setRecipe_display(String recipe_display) {
        this.recipe_display = recipe_display;
    }

    public ImageView getRecipe_session_Image() {
        return recipe_session_Image;
    }

    public void setRecipe_session_Image(ImageView recipe_session_Image) {
        this.recipe_session_Image = recipe_session_Image;
    }
}
