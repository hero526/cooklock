package com.example.semin.cooklock;

import android.util.Log;

import com.google.gson.JsonParser;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Translate {
    String result;

    public Translate(String inputstring) {
        String clientId = "T2_GlYzcihhXwUZwdgPg";//애플리케이션 클라이언트 아이디값";
        String clientSecret = "zM487rI5Fl";//애플리케이션 클라이언트 시크릿값";

        try {
            String text = URLEncoder.encode(inputstring, "UTF-8");
            String apiURL = "https://openapi.naver.com/v1/papago/n2mt";
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            // post request
            String postParams = "source=en&target=ko&text=" + text;
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(postParams);
            wr.flush();
            wr.close();
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            int i = 0;
            result = response.toString();
            for(i = result.length()-5; i >0; i--)
            {
                if(result.charAt(i) == '\"')
                break;
            }

            result = result.substring(i+1,result.length()-4);
            Log.i("abcd",result);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public String getresultString()
    {
        return result;
    }
}