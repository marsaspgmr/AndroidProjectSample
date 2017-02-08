package com.example.marco.androidprojectsample;

import android.os.AsyncTask;

/**
 * Created by Marco on 07/02/2017.
 */

public class ConnectTask extends AsyncTask<String,String,SimpleClient> {

    @Override
    protected SimpleClient doInBackground(String... message) {
        SimpleClient mSimpleClient = new SimpleClient(new MonitorMessage() {
            @Override
            public void returnMessage(String message) {
                publishProgress(message);
            }
        });
        mSimpleClient.run();

        return null;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);

        //refresh DialogTextwview with current message
        //textView.setText(values[0].toString());

    }
}
