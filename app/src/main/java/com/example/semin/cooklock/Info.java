package com.example.semin.cooklock;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class Info extends AppCompatActivity {

    Bitmap[] bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        ImageButton[] barray = new ImageButton[6];
        bitmap = new Bitmap[6];
        barray[0] = (ImageButton) findViewById(R.id.image1);
        barray[1] = (ImageButton) findViewById(R.id.image2);
        barray[2] = (ImageButton) findViewById(R.id.image3);
        barray[3] = (ImageButton) findViewById(R.id.image4);
        barray[4] = (ImageButton) findViewById(R.id.image5);
        barray[5] = (ImageButton) findViewById(R.id.image6);

        TextView[] text = new TextView[6];

        text[0] = (TextView) findViewById(R.id.text1);
        text[1] = (TextView) findViewById(R.id.text2);
        text[2] = (TextView) findViewById(R.id.text3);
        text[3] = (TextView) findViewById(R.id.text4);
        text[4] = (TextView) findViewById(R.id.text5);
        text[5] = (TextView) findViewById(R.id.text6);

        for (int i = 0; i < MainActivity.inlist.size(); i++) {
            text[i].setText(MainActivity.inlist.get(i).getIgrdName());
        }

        Thread mThread = new Thread() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < MainActivity.inlist.size(); i++) {
                        URL url = new URL(MainActivity.inlist.get(i).getImgSrc());

                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                        conn.setDoInput(true);
                        conn.connect();

                        InputStream is = conn.getInputStream();
                        bitmap[i] = BitmapFactory.decodeStream(is);
                    }
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
            for (int i = 0; i < MainActivity.inlist.size(); i++)
                barray[i].setImageBitmap(bitmap[i]);
        } catch (InterruptedException e) {
        }
    }

    public void onClickButton(View v) {
        switch (v.getId()) {
            case R.id.image1:
                AlertDialog.Builder dig = new AlertDialog.Builder(this);
                dig.setTitle("재료 설명");
                dig.setItems(R.array.ingregroup,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String[] ingregroup = getResources().getStringArray(R.array.ingregroup);
                                makeDialog(v.getId(), which);
                                //Toast.makeText(getApplicationContext(), ingregroup[which], Toast.LENGTH_SHORT).show();
                            }
                        });
                dig.show();
                break;

            case R.id.image2:
                AlertDialog.Builder dig2 = new AlertDialog.Builder(this);
                dig2.setTitle("재료 설명");
                dig2.setItems(R.array.ingregroup,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String[] ingregroup = getResources().getStringArray(R.array.ingregroup);
                                makeDialog(v.getId(), which);
                                //Toast.makeText(getApplicationContext(), ingregroup[which], Toast.LENGTH_SHORT).show();
                            }
                        });
                dig2.show();
                break;


            case R.id.image3:
                AlertDialog.Builder dig3 = new AlertDialog.Builder(this);
                dig3.setTitle("재료 설명");
                dig3.setItems(R.array.ingregroup,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String[] ingregroup = getResources().getStringArray(R.array.ingregroup);
                                makeDialog(v.getId(), which);
                                //Toast.makeText(getApplicationContext(), ingregroup[which], Toast.LENGTH_SHORT).show();
                            }
                        });
                dig3.show();
                break;


            case R.id.image4:
                AlertDialog.Builder dig4 = new AlertDialog.Builder(this);
                dig4.setTitle("재료 설명");
                dig4.setItems(R.array.ingregroup,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String[] ingregroup = getResources().getStringArray(R.array.ingregroup);
                                makeDialog(v.getId(), which);
                                //Toast.makeText(getApplicationContext(), ingregroup[which], Toast.LENGTH_SHORT).show();
                            }
                        });
                dig4.show();
                break;

            case R.id.image5:
                AlertDialog.Builder dig5 = new AlertDialog.Builder(this);
                dig5.setTitle("재료 설명");
                dig5.setItems(R.array.ingregroup,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String[] ingregroup = getResources().getStringArray(R.array.ingregroup);
                                makeDialog(v.getId(), which);
                                //Toast.makeText(getApplicationContext(), ingregroup[which], Toast.LENGTH_SHORT).show();
                            }
                        });
                dig5.show();
                break;

            case R.id.image6:
                AlertDialog.Builder dig6 = new AlertDialog.Builder(this);
                dig6.setTitle("재료 설명");
                dig6.setItems(R.array.ingregroup,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String[] ingregroup = getResources().getStringArray(R.array.ingregroup);
                                makeDialog(v.getId(), which);
                                //Toast.makeText(getApplicationContext(), ingregroup[which], Toast.LENGTH_SHORT).show();
                            }
                        });
                dig6.show();
                break;
        }


    }

    public void makeDialog(int id, int which) {
        String[] ingregroup = getResources().getStringArray(R.array.ingregroup);
        AlertDialog.Builder dig = new AlertDialog.Builder(this);

        dig.setTitle(ingregroup[which]);

        dig.setMessage("test value. Need to modify");

        dig.show();
    }
}
