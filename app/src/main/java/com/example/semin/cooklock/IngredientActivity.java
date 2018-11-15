package com.example.semin.cooklock;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class IngredientActivity extends AppCompatActivity {
    AutoCompleteTextView ingredient_text;
    Button select_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient);
        ingredient_text = (AutoCompleteTextView) findViewById(R.id.ingredient);

        InputStream inputStream = getResources().openRawResource(R.raw.recipe_ingrident);

        Scanner scanner = new Scanner(inputStream);
        ArrayList<String> list_ing = new ArrayList<>();

        scanner.nextLine();
        while(scanner.hasNextLine()){
            String[] data = scanner.nextLine().split(",");
            System.out.println(data[2]);

            try {
                if(!list_ing.contains(data[2])){
                    list_ing.add(new String(data[2]));
                }

            }catch(NumberFormatException e){
                continue;
            }catch(ArrayIndexOutOfBoundsException e){
                continue;
            }
        }

        scanner.close();
        String[] ingredient_list = new String[list_ing.size() ];
        list_ing.toArray( ingredient_list );
        System.out.println("list");
        System.out.println(ingredient_list[1]);





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
