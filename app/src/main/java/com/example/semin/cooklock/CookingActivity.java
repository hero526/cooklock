package com.example.semin.cooklock;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class CookingActivity extends AppCompatActivity {
    String foodid;
    int sessionNum = 0;
    Bitmap bitmap;

    int cur_sessionNum = 0;
    ArrayList<Recipe_Seq> list = new ArrayList<>();
    Recipe_Seq cur_session;

    private int remainTime;
    private int sessionTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cooking);

        InputStream inputStream = getResources().openRawResource(R.raw.recipe_sequence);

        Scanner scanner = new Scanner(inputStream);

        Intent i = getIntent();

        //foodid = i.getExtras().getString("foodid");
        foodid = "195453";

        scanner.nextLine();
        while (scanner.hasNextLine()) {
            String[] data = scanner.nextLine().split("\t");
            try {
                if (data.length == 3) {
                    if (data[1].equals(foodid)) {
                        System.out.println("data[0] = " + data[0]);
                        list.add(new Recipe_Seq(Integer.parseInt(data[0]), Integer.parseInt(data[1]), data[2], ""));
                        sessionNum++;
                    }
                } else {
                    if (data[0].equals(foodid)) {
                        System.out.println("data[0] = " + data[0]);
                        list.add(new Recipe_Seq(Integer.parseInt(data[0]), Integer.parseInt(data[1]), data[2], data[3]));
                        sessionNum++;
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("numError!");
                continue;
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("indexError!");
                continue;
            }
        }
        scanner.close();

        Collections.sort(list);

        ((TextView) findViewById(R.id.sessionNum))
                .setText("0");
        ((TextView) findViewById(R.id.sessionDisp))
                .setText("요리를 시작합니다.");
    }

    public void onClickButton(View view) {
        if(cur_sessionNum==sessionNum) {
            Intent i = new Intent(this, CompleteActivity.class);
            startActivity(i);
        }
        else {
            cur_session = list.get(cur_sessionNum);
            ((TextView) findViewById(R.id.sessionNum))
                    .setText(Integer.toString(cur_session.getRecipe_session()));

         ImageView imageView = (ImageView) findViewById(R.id.sessionImage);


         Thread mThread = new Thread() {
             @Override
             public void run() {
                 try {
                     URL url = new URL(cur_session.getRecipe_session_Image());

                     HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                     conn.setDoInput(true);
                     conn.connect();

                     InputStream is = conn.getInputStream();
                     bitmap = BitmapFactory.decodeStream(is);
                 } catch (MalformedURLException e) {
                     e.printStackTrace();
                 } catch (IOException e) {
                     e.printStackTrace();
                 }
             }
         };

         mThread.start();
         try {
             mThread.join();
             imageView.setImageBitmap(bitmap);
         } catch (InterruptedException e) {
         }


            ((TextView) findViewById(R.id.sessionDisp))
                    .setText(cur_session.getRecipe_display());

            cur_sessionNum++;
        }
    }
}
