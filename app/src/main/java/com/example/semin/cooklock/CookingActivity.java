package com.example.semin.cooklock;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;

public class CookingActivity extends AppCompatActivity {
    String foodid;

    private int remainTime;
    private int sessionTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cooking);

        InputStream inputStream = getResources().openRawResource(R.raw.recipe_basic);

        Scanner scanner = new Scanner(inputStream);
        ArrayList<Recipe_Seq> list = new ArrayList<>();
        Recipe_Seq cur_session;
        Intent i = getIntent();

        foodid = i.getExtras().getString("name");
        int sessionNum = 0;
        scanner.nextLine();
        while(scanner.hasNextLine()){
            String[] data = scanner.nextLine().split(",");
            try {
                if(data[0].equals(foodid)) {
                    list.add(new Recipe_Seq(Integer.parseInt(data[0]), Integer.parseInt(data[1]), data[2], data[3]));
                    sessionNum++;
                }
            }catch(NumberFormatException e){
                continue;
            }catch(ArrayIndexOutOfBoundsException e){
                continue;
            }
        }
        scanner.close();


        for(int session=1; session <= sessionNum; session++) {
            int cursor = 0;
            do {
                cur_session = list.get(cursor);
                cursor++;
            }
            while(cur_session.getRecipe_session() == session);

            ((TextView)findViewById(R.id.sessionNum))
                    .setText(Integer.toString(cur_session.getRecipe_id()));

            URL sessionImage = null;
            try {
                sessionImage = new URL(cur_session.getRecipe_session_Image());
                Bitmap bitmap = BitmapFactory.decodeStream(sessionImage.openStream());

                ((ImageView)findViewById(R.id.sessionImage))
                        .setImageBitmap(bitmap);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            ((TextView)findViewById(R.id.sessionDisp))
                    .setText(cur_session.getRecipe_display());
        }

    }
}
