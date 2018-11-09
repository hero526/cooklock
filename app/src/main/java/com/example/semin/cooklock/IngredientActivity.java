package com.example.semin.cooklock;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class IngredientActivity extends AppCompatActivity {
    EditText ingredient_text;
    Button select_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient);
        ingredient_text = (EditText)findViewById(R.id.ingredient);
        select_button = (Button)findViewById(R.id.SelectButton);
        select_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(IngredientActivity.this,RecipeActivity.class);
                startActivity(i);
            }
        });

    }
}
