package com.example.semin.cooklock;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CookingActivity extends AppCompatActivity {
    String foodname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cooking);

        Intent i = getIntent();

        foodname = i.getExtras().getString("name");
    }
}
