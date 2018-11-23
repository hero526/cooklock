package com.example.semin.cooklock;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MyListAdapter extends BaseAdapter {
    public ArrayList<Recipe_Basic> listViewItemList = new ArrayList<Recipe_Basic>() ;
    Bitmap bitmap;
    // ListViewAdapter의 생성자
    public MyListAdapter() {

    }

    // Adapter에 사용되는 데이터의 개수를 리턴. : 필수 구현
    @Override
    public int getCount() {



        return listViewItemList.size() ;
    }

    // position에 위치한 데이터를 화면에 출력하는데 사용될 View를 리턴. : 필수 구현
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        // "listview_item" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item, parent, false);
        }

        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득


        TextView titleTextView = (TextView) convertView.findViewById(R.id.recipe_name) ;

        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        final Recipe_Basic recipe_basic = listViewItemList.get(position);
        ImageView imageView = (ImageView)convertView.findViewById(R.id.recipe_image);


        Thread mThread = new Thread() {
            @Override
            public void run() {
                try {
                    URL url = new URL(recipe_basic.getRecipe_Image());

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


        // 아이템 내 각 위젯에 데이터 반영
      //  titleTextView.setText(recipe_basic.getRecipe_Image());
       //iconImageView.setImageBitmap();
       titleTextView.setText(recipe_basic.getRecipe_name());

        return convertView;
    }

    // 지정한 위치(position)에 있는 데이터와 관계된 아이템(row)의 ID를 리턴. : 필수 구현
    @Override
    public long getItemId(int position) {
        return position ;
    }

    // 지정한 위치(position)에 있는 데이터 리턴 : 필수 구현
    @Override
    public Object getItem(int position) {
        return listViewItemList.get(position) ;
    }

    // 아이템 데이터 추가를 위한 함수. 개발자가 원하는대로 작성 가능.
    /*public void addItem(String icon, String title) {
        Recipe_Basic item = new Recipe_Basic(icon,title);

        item.setRecipe_Image(icon);
        item.setTitle(title);


        listViewItemList.add(item);
    }
*/
    public void addItem(String recipe_id, String recipe_name, String recipe_expain, String recipe_type, String recipe_time, String recipe_kcal, String recipe_level, String recipe_Image)
    {
        Recipe_Basic item = new Recipe_Basic(recipe_id,recipe_name,recipe_expain,recipe_type,recipe_time,recipe_kcal,recipe_level,recipe_Image);

        item.setRecipe_id(recipe_id);
        item.setRecipe_name(recipe_name);
        item.setRecipe_expain(recipe_expain);
        item.setRecipe_type(recipe_type);
        item.setRecipe_kcal(recipe_kcal);
        item.setRecipe_Image(recipe_Image);

        listViewItemList.add(item);
    }


}
