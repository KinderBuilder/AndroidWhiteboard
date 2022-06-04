package com.example.hellobird;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        Button optionB_MA = (Button) findViewById(R.id.settingButtonMenu);
        optionB_MA.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, options.class));
            }
        });
        Button exitB_MA = (Button) findViewById(R.id.exitButtonMenu);
        exitB_MA.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                finishAffinity();
            }
        });
        Button boardB_MA = (Button) findViewById(R.id.loadButtonMenu);
        boardB_MA.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, board.class));
            }
        });
    }


}