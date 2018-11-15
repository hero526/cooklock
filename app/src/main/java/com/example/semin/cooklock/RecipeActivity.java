package com.example.semin.cooklock;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class RecipeActivity extends AppCompatActivity {

    ArrayList<Recipe_Basic> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        ListView listView=(ListView)findViewById(R.id.list);
        InputStream inputStream = getResources().openRawResource(R.raw.recipe_basic);

        Scanner scanner = new Scanner(inputStream);
        RecipeAdapter adapter = new RecipeAdapter();
         list = new ArrayList<>();

        scanner.nextLine();
        while(scanner.hasNextLine()){
            String[] data = scanner.nextLine().split(",");
            try {
                adapter.addItem(new Recipe_Basic(Integer.parseInt(data[0]),data[1],
                        data[2],data[4],data[7],data[8],data[10],data[13]));

            }catch(NumberFormatException e){
                continue;
            }catch(ArrayIndexOutOfBoundsException e){
                continue;
            }
        }
        scanner.close();


        listView.setAdapter(adapter);

    }

    class RecipeAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            RecipeItemView itemView = null;
            if(view == null)
                itemView = new RecipeItemView(getApplicationContext());
            else
                itemView = (RecipeItemView)view;

            Recipe_Basic basic = list.get(i); // i = position
            itemView.setName(basic.getRecipe_name());
            itemView.setImage(basic.getRecipe_Image());
            return itemView;
        }

        public void addItem(Recipe_Basic basic){
            list.add(basic);
        }
    }


}
