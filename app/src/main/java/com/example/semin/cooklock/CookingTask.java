package com.example.semin.cooklock;

import android.os.AsyncTask;
import android.widget.TextView;

public class CookingTask extends AsyncTask<Integer, Integer, Integer> {
    TextView mOutput;
    int mCount = 0;

    public void setOutputView(TextView txt) {
        mOutput = txt;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mCount = 0;
    }

    @Override
    protected Integer doInBackground(Integer... integers) {
        for(int i = 0; i < integers[0]; i++) {
            mCount++;
            publishProgress(mCount); // trigger the execution of onProgressUpdate( )
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {;}
        }

        return mCount;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        mOutput.setText(values[0]);
    }

    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);
        mOutput.setText("결과: " + integer);
    }

    @Override
    protected void onCancelled(Integer integer) {
        super.onCancelled(integer);
        mOutput.setText("취소됨");
    }
}

