package com.example.hellobird;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.ImageButton;

public class color extends AppCompatActivity {
    //public Button backButton;
    //public Button redButton;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.color);

        Button backB_COLOR = (Button) findViewById(R.id.backButtonColor);
        Button redB_Color = (Button) findViewById(R.id.redButtonColor);
        Button orangeB_Color = (Button) findViewById(R.id.orangeButtonColor);
        Button yellowB_Color = (Button) findViewById(R.id.yellowButtonColor);
        Button greenB_Color = (Button) findViewById(R.id.greenButtonColor);
        Button blueB_Color = (Button) findViewById(R.id.blueButtonColor);
        Button purpleB_Color = (Button) findViewById(R.id.purpleButtonColor);
        Button blackB_Color = (Button) findViewById(R.id.blackButtonColor);
        Button whiteB_Color = (Button) findViewById(R.id.whiteButtonColor);

        backB_COLOR.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                finish();
            }
        });
        redB_Color.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                paintView.color = Color.RED;
            }
        });
        orangeB_Color.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                paintView.color = Color.rgb(245,163,12);
            }
        });
        yellowB_Color.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                paintView.color = Color.YELLOW;
            }
        });
        greenB_Color.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                paintView.color = Color.GREEN;
            }
        });
        blueB_Color.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                paintView.color = Color.BLUE;
            }
        });
        purpleB_Color.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                paintView.color = Color.rgb(122,60,145);
            }
        });
        blackB_Color.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                paintView.color = Color.BLACK;
            }
        });
        whiteB_Color.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                paintView.color = Color.WHITE;
            }
        });
    }
}
