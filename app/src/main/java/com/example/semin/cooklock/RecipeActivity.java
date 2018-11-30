package com.example.semin.cooklock;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class RecipeActivity extends AppCompatActivity {

    ListView listView;
    MyListAdapter Myadapter;
    EditText editText ;
    ImageButton button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        Myadapter = new MyListAdapter();
        listView=(ListView)findViewById(R.id.list);

        listView.setAdapter(Myadapter);
        InputStream inputStream = getResources().openRawResource(R.raw.recipe_basic);

        Scanner scanner = new Scanner(inputStream);
        scanner.nextLine();
        while(scanner.hasNextLine()){
            String[] data = scanner.nextLine().split(",");
            try {
                Myadapter.addItem(data[0],data[1],data[2],data[4],data[7],data[8],data[10],data[13]);
            }catch(NumberFormatException e){
                continue;
            }catch(ArrayIndexOutOfBoundsException e){
                continue;
            }
        }
        scanner.close();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Recipe_Basic recipe = (Recipe_Basic) Myadapter.getItem(position);
                Intent i = new Intent(getApplicationContext(),CookingActivity.class);
                i.putExtra("foodid",recipe.getRecipe_id());
                startActivity(i);
            }
        });

        editText=(EditText)findViewById(R.id.editText);

        button = (ImageButton)findViewById(R.id.imageButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editText.getText().toString();

                InputStream inputStream = getResources().openRawResource(R.raw.recipe_basic);
                Myadapter.listViewItemList.clear();
                Scanner scanner = new Scanner(inputStream);
                scanner.nextLine();
                while (scanner.hasNextLine()) {
                    String[] data = scanner.nextLine().split(",");
                    try {

                        if (data[1].contains(name))
                            Myadapter.addItem(data[0], data[1], data[2], data[4], data[7], data[8], data[10], data[13]);
                    } catch (NumberFormatException e) {
                        continue;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        continue;
                    }
                }
                scanner.close();
                Myadapter.notifyDataSetChanged();
            }
        });
    }
}
