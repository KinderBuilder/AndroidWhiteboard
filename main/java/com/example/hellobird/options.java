package com.example.hellobird;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.view.View;
import android.widget.ImageButton;


public class options extends AppCompatActivity {
    public Button backButton;
    public Button homeButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.options);

        Button backB_OPTIONS = (Button) findViewById(R.id.backButtonOption);
        ImageButton colorB_OPTIONS = (ImageButton) findViewById(R.id.colorButtonOption);
        ImageButton homeB_OPTIONS = (ImageButton) findViewById(R.id.homeButtonOption);

        backB_OPTIONS.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        colorB_OPTIONS.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Log.e("Before", "It ran I guess");
                startActivity(new Intent(options.this, color.class));
            }
        });

        homeB_OPTIONS.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(options.this, MainActivity.class));
            }
        });
    }
}
