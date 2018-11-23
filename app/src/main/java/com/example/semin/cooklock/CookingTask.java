package com.example.semin.cooklock;

import android.os.AsyncTask;
import android.widget.TextView;

public class CookingTask extends AsyncTask<Integer, Integer, Integer> {
    TextView mOutput;

    public void setOutputView(TextView txt) {
        mOutput = txt;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Integer doInBackground(Integer... integers) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }

        for (int i = integers[0] - 1; i >= 0; i--) {
            for (int j = 59; j >= 0; j--) {
                if (this.isCancelled()) return 1;
                publishProgress(i, j); // trigger the execution of onProgressUpdate( )
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
            }
        }
        return 0;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        mOutput.setText(String.format("%02d", values[0]) + ":" + String.format("%02d", values[1]));
    }

    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);
    }

    @Override
    protected void onCancelled(Integer integer) {
        super.onCancelled(integer);
        //mOutput.setText("취소됨");
    }
}

