package com.example.marco.androidprojectsample;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.net.Socket;


public class MainActivity extends AppCompatActivity {
    TextView dialogTextView = null;
    TextView cdsTextView = null;
    int triesToConnect = 0;
    Socket socket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dialogTextView = (TextView) findViewById(R.id.logTextView);
        cdsTextView = (TextView) findViewById(R.id.cdsTextView);
        //GPSTracker gps = new GPSTracker(this);
        new ConnectTask().execute("");
    }

    class ConnectTask extends AsyncTask<String,String,SimpleClient> {

        @Override
        protected SimpleClient doInBackground(String... message) {
            SimpleClient mSimpleClient = new SimpleClient(new MonitorMessage() {
                @Override
                public void returnMessage(String message) {
                    publishProgress(message);
                }
            });

            //connect to server
            //if connection can't be estabilished retrying for 2 times
            do {
                socket = mSimpleClient.run();
                triesToConnect++;
                if(triesToConnect > 1)
                    publishProgress("Trying re-connecting...");

            } while(socket == null &&
                    triesToConnect < mSimpleClient.MAX_TRIES_TO_CONNECT);

            if(socket.isConnected()){
                //traccia la posizione del dispositivo
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);

            //update dialogTextView
            if(dialogTextView != null)
                dialogTextView.setText(values[0]);
        }
    }
}


