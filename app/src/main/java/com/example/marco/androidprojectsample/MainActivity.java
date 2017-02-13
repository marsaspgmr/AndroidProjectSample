package com.example.marco.androidprojectsample;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity {
    TextView dialogTextView = null;
    TextView cdsTextView = null;
    int connection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dialogTextView = (TextView) findViewById(R.id.logTextView);
        cdsTextView = (TextView) findViewById(R.id.cdsTextView);

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
            connection = mSimpleClient.run();
            //se la connessione al server è riuscita
                //traccia la posizione del dispositivo

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


