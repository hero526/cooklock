package com.example.semin.cooklock;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    int a = 1;
    Animation anim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView)findViewById(R.id.month_ingredient);
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
        textView.startAnimation(anim);
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
        Intent i = new Intent(this, CookingActivity.class);
        startActivity(i);
    }
    public void checkDataInput(View view) {
        Intent i = new Intent(this, IngredientMonthlyInfo.class);
        startActivity(i);
    }

}
