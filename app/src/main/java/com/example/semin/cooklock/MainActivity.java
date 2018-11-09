package com.example.semin.cooklock;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showIngredient(View view) {
        Intent i = new Intent(this, IngredientActivity.class);
        startActivity(i);
    }

    public void showRecipe(View view) {
        Intent i = new Intent(this, RecipeActivity.class);
        startActivity(i);
    }
}
