package com.example.semin.cooklock;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    public static TextView textView;
    public static Animation anim;
    int a = 1;
    EditText editText;
    ImageButton button2 ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText)findViewById(R.id.editText);
        button2 = (ImageButton)findViewById(R.id.button2);
        textView = (TextView)findViewById(R.id.month_ingredient);
        IngredientMonthlyInfo Ingredient = new IngredientMonthlyInfo();
        Ingredient.initUI();
        anim = AnimationUtils.loadAnimation(this,R.anim.set);
        //textView.startAnimation(anim);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                //start
            }
            @Override
            public void onAnimationEnd(Animation animation) {
                //end
            }
            @Override
            public void onAnimationRepeat(Animation animation) {
                //repeat
            }
        });


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str=editText.getText().toString();
                Intent intent = new Intent(getApplicationContext(),RecipeActivity.class);
                intent.putExtra("check","main");
                intent.putExtra("name",str);
                startActivity(intent);
            }
        }); // 메인에서 단어치면 해당 단어를 포함하는 레시피 검색결과가 나온다
    }

    public void showIngredient(View view) {
        Intent i = new Intent(this, IngredientActivity.class);
        startActivity(i);
    }

    public void showRecipe(View view) {
        Intent i = new Intent(this, RecipeActivity.class);
        startActivity(i);
    }

    public void debug_complete(View view) {
       // Intent i = new Intent(this, CookingActivity.class);
       // startActivity(i);
    }

    /*
    public void checkDataInput(View view) {
        Intent i = new Intent(this, IngredientMonthlyInfo.class);
        startActivity(i);
    }
    */

    public void goCommunity(View view) {
        // TODO: https://www.instagram.com/cooklock_official/?hl=ko 로 넘어가기
    }
}
