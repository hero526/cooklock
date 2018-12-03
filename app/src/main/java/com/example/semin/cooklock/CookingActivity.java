package com.example.semin.cooklock;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CookingActivity extends AppCompatActivity {
    String foodid;
    static int sessionNum = 0;
    Bitmap bitmap;

    CountDownTimer cTimer = null;

    int cur_sessionNum = 0;
    ArrayList<Recipe_Seq> list = new ArrayList<>();
    Recipe_Seq cur_session;

    private int remain_Time = 0;
    private int session_Time = 0;

    boolean mBreak = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        cur_sessionNum = 0;
        sessionNum = 0;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cooking);

        InputStream inputStream = getResources().openRawResource(R.raw.recipe_sequence);

        Scanner scanner = new Scanner(inputStream);

        Intent i = getIntent();

        foodid = i.getExtras().getString("foodid");
        scanner.nextLine();
        while (scanner.hasNextLine()) {
            String[] data = scanner.nextLine().split("\t");
            try {
                if (data.length == 3) {
                    if (data[1].equals(foodid)) {
                        list.add(new Recipe_Seq(Integer.parseInt(data[0]), Integer.parseInt(data[1]), data[2], ""));
                        sessionNum++;
                    }
                } else {
                    if (data[0].equals(foodid)) {
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

        ((TextView) findViewById(R.id.sessionDisp))
                .setText("요리를 시작합니다.");
        ((Button) findViewById(R.id.nextButton))
                .setText("시작");

        ((ProgressBar) findViewById(R.id.progressBar))
                .setMax(sessionNum);
    }

    public void onClickButton(View view) {
        cancelTimer();
        remain_Time = session_Time = 0;
        if(view.getId() == R.id.prevButton) {
            if (cur_sessionNum < 2) return;
            cur_sessionNum-=2;
        }
        if (cur_sessionNum == sessionNum) {
            Intent i = new Intent(this, CompleteActivity.class);
            startActivity(i);
        } else {
            cur_session = list.get(cur_sessionNum);

            ImageView imageView = (ImageView) findViewById(R.id.sessionImage);

            Thread mThread = new Thread() {
                @Override
                public void run() {
                    try {
                        URL url = new URL(cur_session.getRecipe_session_Image());

                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
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

            if (cur_session.getRecipe_session_Image() != "") {
                mThread.start();
                try {
                    mThread.join();
                    imageView.setImageBitmap(bitmap);
                } catch (InterruptedException e) {
                }
            }

            ((TextView) findViewById(R.id.sessionDisp))
                    .setText(cur_session.getRecipe_display());

            cur_sessionNum++;

            ((Button) findViewById(R.id.nextButton)).setText("다음");
            ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);

            progressBar.setProgress(cur_sessionNum);

            Pattern pattern = Pattern.compile("[0-9]분");
            Matcher matcher = pattern.matcher(cur_session.getRecipe_display());
            if(matcher.find()) {
                remain_Time = session_Time = Integer.parseInt(matcher.group().substring(0, 1)) * 60;
                ((TextView)findViewById(R.id.remainTime)).setText("0"+Integer.toString(session_Time/60)+":00");
            }
            else {
                ((TextView)findViewById(R.id.remainTime)).setText("+1분");
            }

            if(session_Time != 0) {
                startTimer();
            }
        }
    }

    public void settingTimer(View view) {
        cancelTimer();
        remain_Time += 60;
        startTimer();
    }

    void startTimer() {
        cTimer = new CountDownTimer(remain_Time*1000, 1000) {
            public void onTick(long millisUntilFinished) {
                int min = remain_Time / 60;
                int sec = remain_Time % 60;
                ((TextView)findViewById(R.id.remainTime)).setText(String.format("%02d", min) + ":" + String.format("%02d", sec));
                remain_Time--;
            }
            public void onFinish() {
                ((TextView)findViewById(R.id.remainTime)).setText("완료!");
            }
        };
        cTimer.start();
    }

    void cancelTimer() {
        if(cTimer!=null)
            cTimer.cancel();
    }
}
