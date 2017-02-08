package com.example.marco.androidprojectsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new ConnectTask().execute("");

        TextView dialogTextView = (TextView) findViewById(R.id.logTextView);
        TextView cdsTextView = (TextView) findViewById(R.id.cdsTextView);

    }
}


