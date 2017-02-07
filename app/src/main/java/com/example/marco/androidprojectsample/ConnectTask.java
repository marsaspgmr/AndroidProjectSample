package com.example.marco.androidprojectsample;

import android.os.AsyncTask;

/**
 * Created by Marco on 07/02/2017.
 */

public class ConnectTask extends AsyncTask<String,String,SimpleClient> {

    @Override
    protected SimpleClient doInBackground(String... message) {
        SimpleClient mSimpleClient = new SimpleClient();
        mSimpleClient.run();

        return null;
    }
}
