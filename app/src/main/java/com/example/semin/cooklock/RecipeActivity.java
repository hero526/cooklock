package com.example.semin.cooklock;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class RecipeActivity extends ListActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        ListView listView;
        InputStream inputStream = getResources().openRawResource(R.raw.recipe_basic);

        Scanner scanner = new Scanner(inputStream);
        ArrayList<Recipe_Basic> list = new ArrayList<>();

        scanner.nextLine();
        while(scanner.hasNextLine()){
            String[] data = scanner.nextLine().split(",");
            try {
                list.add(new Recipe_Basic(Integer.parseInt(data[0]),data[1],
                        data[2],data[4],data[7],data[8],data[10],data[13]));
            }catch(NumberFormatException e){
                continue;
            }catch(ArrayIndexOutOfBoundsException e){
                continue;
            }
        }
        scanner.close();


    }


}
