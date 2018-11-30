package com.example.semin.cooklock;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;

public class IngredientMonthlyThread extends Activity {

    private boolean DEBUG = true;
    private final String TAG = "IngredientMonthlyInfo";

    //- OpenAPI Web Serve 접속용 URL 상수
    private final String monthFdmtLst_URL = "http://api.nongsaro.go.kr/service/monthFd/monthFdmtLst?";
    private final String monthFdmtDtl_URL = "http://api.nongsaro.go.kr/service/monthFd/monthFdmtDtl?";
    private String month_REQ_CODE = "thisYear=2018&thisMonth=";
    private String ingrCode_REQ_CODE = "cntntsNo=";
    private String SERVICE_KEY = "&apiKey=20181115OUPDZZHIFCDZHWKWELKWA";


    //- UI Widget 객체 변수
    private TextView InfoTXT;
    private ImageView InfoImg;

    //- Open API Web server 접속 제어 관련 변수들
    private IngrAsyncTask ingrAsyncTask;
    private String ingrOpenURL1; //월에 맞게 가져오는 거
    private String ingrOpenURL2; //그 코드에 맞는 정보를 가져올 수 있는

    private String szMsg = ""; //parsing 된 data 저장

    int currentMonth = Calendar.getInstance().get(Calendar.MONTH)+1;



    ArrayList ingrMonthlyList = new ArrayList<IngredientMonthly>();


    public IngredientMonthlyThread(){

        ingrAsyncTask = new IngrAsyncTask();
        ingrAsyncTask.execute();
        ingrOpenURL1 = monthFdmtLst_URL + month_REQ_CODE + currentMonth + SERVICE_KEY;


        if(DEBUG) Log.i(TAG, "Constructor OpenURL = " + ingrOpenURL1);
    }


    class IngrAsyncTask extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] Object) {
            try {
                //- 태그별 텍스트 저장 변수
                String ingrdCode = null;
                String igrdName = null;
                String img_src_rtnFileCours = null;
                String img_src_rtnStreFileNm = null;
                String toBuy = null;
                String toMaintain = null;
                String toAccept = null;

                //- 텍스트 값을 가지고 태그 확인용 변수
                boolean b_ingrdCode = false;
                boolean b_fdmtNm = false;
                boolean b_rtnFileCours = false;
                boolean b_rtnStreFileNm = false;
                boolean b_prchCheatDtl = false;
                boolean b_cstdyMthDtl = false;
                boolean b_ntkMthDtl = false;

                //- OpneAPI 접속 URI
                URL url1 = new URL(ingrOpenURL1);

                InputStream is1 = url1.openStream();
                XmlPullParserFactory factory1 = XmlPullParserFactory.newInstance();
                XmlPullParser parser1 = factory1.newPullParser();


                //- OpneAPI의 XML 데이터 가져외
                parser1.setInput(new InputStreamReader(is1, "UTF-8"));

                int gCount = 1;


                //- OpneAPI의 XML 파싱하기
                int eventType = parser1.getEventType();
                while (eventType != XmlPullParser.END_DOCUMENT)
                {
                    IngredientMonthly item = new IngredientMonthly();
                    switch (eventType) {
                        case XmlPullParser.START_DOCUMENT:
                            break;

                        case XmlPullParser.END_TAG:
                            if (parser1.getName().equals("item")) {
                                szMsg = ingrdCode;
                                item.setIgrdNo(Integer.parseInt(szMsg));
                                ingrMonthlyList.add(item);

                                //Log.i(TAG, item.toString());

                                //szMsg += "" + gCount++ + ". [ 재료 코드 ] " + ingrdCode + "\n";

                                ingrOpenURL2 = monthFdmtDtl_URL + ingrCode_REQ_CODE + ingrdCode + SERVICE_KEY;
                                //Log.i(TAG, "url2  " + ingrOpenURL2 );
                                URL url2 = new URL(ingrOpenURL2);
                                InputStream is2 = url2.openStream();
                                XmlPullParserFactory factory2 = XmlPullParserFactory.newInstance();
                                XmlPullParser parser2 = factory2.newPullParser();

                                //- OpneAPI의 XML 데이터 가져외
                                parser2.setInput(new InputStreamReader(is2, "UTF-8"));

                                //- OpneAPI의 XML 파싱하기
                                int eventType2 = parser2.getEventType();
                                while (eventType2 != XmlPullParser.END_DOCUMENT) {
                                    switch (eventType2) {
                                        case XmlPullParser.START_DOCUMENT:
                                            break;

                                        case XmlPullParser.END_TAG:
                                            if (parser2.getName().equals("item")) {
                                                szMsg = igrdName;
                                                item.setIgrdName(szMsg);

                                                szMsg = img_src_rtnFileCours;
                                                String temp = img_src_rtnStreFileNm;
                                                item.setImgSrc("http://www.nongsaro.go.kr/"+szMsg + "/" + temp);

                                                szMsg = toBuy;
                                                item.setToBuy(szMsg);

                                                szMsg = toMaintain;
                                                item.setToMaintain(szMsg);

                                                szMsg = toAccept;
                                                item.setToAccept(szMsg);

                                                //Log.i(TAG, "inner" + item.toString());
                                            }
                                            break;

                                        case XmlPullParser.START_TAG:
                                            if (parser2.getName().equals("fdmtNm")) { //재료명
                                                b_fdmtNm = true;
                                            }
                                            if (parser2.getName().equals("rtnFileCours")) { // cms_contents/854
                                                b_rtnFileCours = true;
                                            }
                                            if (parser2.getName().equals("rtnStreFileNm")) { // 209115_MF_BIMG_01.jpg
                                                b_rtnStreFileNm = true;
                                            }
                                            if (parser2.getName().equals("prchCheatDtl")) { //품종 특성 구입 요령
                                                b_prchCheatDtl = true;
                                            }
                                            if (parser2.getName().equals("cstdyMthDtl")) { // 보관법 및 손질법
                                                b_cstdyMthDtl = true;
                                            }
                                            if (parser2.getName().equals("ntkMthDtl")) { // 섭취 방법
                                                b_ntkMthDtl = true;
                                            }
                                            break;

                                        case XmlPullParser.TEXT:
                                            if (b_fdmtNm) {
                                                igrdName = parser2.getText();
                                                b_fdmtNm = false;
                                            }

                                            if (b_rtnFileCours) {
                                                img_src_rtnFileCours = parser2.getText();
                                                b_rtnFileCours = false;
                                            }

                                            if (b_rtnStreFileNm) {
                                                img_src_rtnStreFileNm = parser2.getText();
                                                b_rtnStreFileNm = false;
                                            }

                                            if (b_prchCheatDtl) {
                                                toBuy = parser2.getText();
                                                b_prchCheatDtl = false;
                                            }

                                            if (b_cstdyMthDtl) {
                                                toMaintain = parser2.getText();
                                                b_cstdyMthDtl = false;
                                            }

                                            if (b_ntkMthDtl) {
                                                toAccept = parser2.getText();
                                                b_ntkMthDtl = false;
                                            }
                                            break;
                                    }
                                    eventType2 = parser2.next();
                                }

                                if (eventType2 == XmlPullParser.END_DOCUMENT) {
                                    //Log.i(TAG, "szMsg : " + szMsg);
                                }
                            }
                            break;

                        case XmlPullParser.START_TAG:
                            if (parser1.getName().equals("cntntsNo")) {
                                b_ingrdCode = true;
                            }
                            break;

                        case XmlPullParser.TEXT:
                            if (b_ingrdCode) {
                                ingrdCode = parser1.getText();
                                b_ingrdCode = false;
                            }
                            break;

                    }
                    eventType = parser1.next();
                    if (eventType == XmlPullParser.END_DOCUMENT) {
                        //Log.i(TAG, "szMsg : " + szMsg);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.i(TAG, "IngredientMonthlyInfo : e = " + e.getMessage());
            }


            for(int i =0;i<ingrMonthlyList.size(); i++)
            {
                IngredientMonthly temp = (IngredientMonthly) ingrMonthlyList.get(i);
                Log.i(TAG, temp.getIgrdName()) ;
                Log.i(TAG, temp.getToBuy() + "\n") ;
            }

            return null;

        }

        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
                //InfoTXT.setText(ingrMonthlyList.get(1));
               // InfoTXT.setText(ingrMonthlyList.indexOf(1));
                //InfoTXT.setText(ingrMonthlyList.indexOf(2));//지금은 로딩중 상태인데

        }
    }
}

