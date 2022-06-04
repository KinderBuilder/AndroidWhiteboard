package com.example.hellobird;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class custom_dialog extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_dialog);
        String[] testString = {"Bio", "Lol", "name"};
        Spinner spinner = (Spinner) findViewById(R.id.fileNameSpinner_CUSTOM);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, testString);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);*/

        /*setContentView(R.layout.activity_main);
        Spinner spin = (Spinner) findViewById(R.id.fileNameSpinner_CUSTOM);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, users);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);
        spin.setOnItemSelectedListener(this);*/
        super.onCreate(savedInstanceState);
    }
}
