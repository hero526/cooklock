package com.example.semin.cooklock;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

public class IngredientActivity extends AppCompatActivity {
    AutoCompleteTextView ingredient_text;
    Button select_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient);
        ingredient_text = (AutoCompleteTextView) findViewById(R.id.ingredient);
        String[] ingredient_list = {"파","양파","마늘","김치","오징어","아몰라나도"};
        ingredient_text.setAdapter(
                new ArrayAdapter<String>(
                        IngredientActivity.this,R.layout.support_simple_spinner_dropdown_item,ingredient_list
                )
        );
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
