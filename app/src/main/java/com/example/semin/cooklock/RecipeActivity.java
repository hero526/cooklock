package com.example.semin.cooklock;

import android.app.ListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.SimpleCursorAdapter;

public class RecipeActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        loadDB();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadDB();
    }

    public void loadDB() {
        SQLiteDatabase db = openOrCreateDatabase(
                "recipe.db",
                SQLiteDatabase.CREATE_IF_NECESSARY,
                null);
        db.execSQL("CREATE TABLE IF NOT EXISTS recipe "
                + "(_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, ingredient TEXT, img TEXT, howto TEXT);");

        db.execSQL("INSERT INTO recipe(name, ingredient, img, howto) VALUES('라면', '라면', 'http://temp', '잘')");

        Cursor c = db.rawQuery("SELECT _id, name, ingredient FROM recipe;", null);
        startManagingCursor(c);

        ListAdapter adapt = new SimpleCursorAdapter(
                this,
                R.layout.recipelist,
                c,
                new String[] {"name", "ingredient"},
                new int[] {R.id.text1, R.id.text2}, 0);

        setListAdapter(adapt);

        if (db != null) {
            db.close();
        }
    }
}
