package com.example.semin.cooklock;


import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class RecipeItemView extends LinearLayout {
    ImageView imag;
    TextView textView;
    public RecipeItemView(Context context) {
        super(context);
        init(context);
    }

    public RecipeItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.item ,this,true);

      imag = (ImageView)findViewById(R.id.recipe_image);
      textView  = (TextView)findViewById(R.id.recipe_name);
    }

    public void setName(String name){
        textView.setText(name);
    }

    public void setImage(String address){

    }
}
