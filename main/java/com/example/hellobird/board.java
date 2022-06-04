package com.example.hellobird;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

//import com.example.hellobird.paintView;

public class board extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    public Button optionsButton;
    public paintView paint;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.board);

        Button optionB_BOARD = (Button) findViewById(R.id.optionButtonBoard);
        Button clearB_BOARD = (Button) findViewById(R.id.clearButtonBoard);
        Button saveB_BOARD = (Button) findViewById(R.id.saveButtonBoard);
        Button createB_BOARD = (Button) findViewById(R.id.createButtonBoard);
        Button deleteB_BOARD = (Button) findViewById(R.id.deleteButtonBoard);

        paint = findViewById(R.id.testPaint);
        paint.getRootView().invalidate();

        ArrayList<String> testString = new ArrayList<>();

        for (String p : paint.getContents()) {
            testString.add(p);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, testString);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(board.this);

        spinner.performClick();

        spinner.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent motionEvent){
                //Only Save
                adapter.clear();
                for (String p : paint.getContents()) {
                    adapter.add(p);
                }
                Log.d("Message","-M");
                paint.printContents();
                return false;
            }
        });


        optionB_BOARD.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                startActivity(new Intent(board.this, options.class));
            }
        });
        clearB_BOARD.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                paint.invalidate();
                paint.clearCan();
                paint.invalidate();
                paint.printContents();
            }
        });
        saveB_BOARD.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //Context Way to get information about the app (what it is doing)
                //board = activity (a type of context); .this is how the class refers to itself. without it, then it would be similar to making a new object of board
                Log.d("Test",spinner.getSelectedItem().toString());
                paint.saveCanvas(board.this,spinner.getSelectedItem().toString());
            }
        });
        deleteB_BOARD.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                paint.invalidate();
                final AlertDialog.Builder builder = new AlertDialog.Builder(board.this);
                View myView = getLayoutInflater().inflate(R.layout.confirm_menu, null);
                final EditText txt_inputText = (EditText)myView.findViewById(R.id.txt_input);
                Button btn_cancelConfirmMenu = (Button)myView.findViewById(R.id.btn_cancelConfirmMenu);
                Button btn_okayConfirmMenu = (Button)myView.findViewById(R.id.btn_okayConfirmMenu);
                builder.setView(myView);
                final AlertDialog alertDialog = builder.create();
                alertDialog.setCanceledOnTouchOutside(false);



                btn_cancelConfirmMenu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });
                btn_okayConfirmMenu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        paint.invalidate();
                        paint.deleteFile(board.this, spinner.getSelectedItem().toString());
                        alertDialog.dismiss();
                    }
                });
                alertDialog.show();
                builder.setMessage("Hoi").setTitle("This is a title");
            }
        });
        createB_BOARD.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                paint.invalidate();
                final AlertDialog.Builder builder = new AlertDialog.Builder(board.this);
                View myView = getLayoutInflater().inflate(R.layout.custom_dialog, null);
                final EditText txt_inputText = (EditText)myView.findViewById(R.id.txt_input);
                Button btn_cancel = (Button)myView.findViewById(R.id.btn_cancel);
                Button btn_okay = (Button)myView.findViewById(R.id.btn_okay);
                builder.setView(myView);
                final AlertDialog alertDialog = builder.create();
                alertDialog.setCanceledOnTouchOutside(false);



                btn_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });
                btn_okay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        paint.createCanvas(board.this, txt_inputText.getText().toString());
                        paint.invalidate();
                        alertDialog.dismiss();


                    }
                });
                alertDialog.show();
                builder.setMessage("Hoi").setTitle("This is a title");
                //paint.createCanvas(board.this);
            }
        });
        paint.setOnTouchListener(new View.OnTouchListener(){
            public boolean onTouch(View v, MotionEvent motionEvent){
                //Sets action to what the user did (ex: touch down, move, ect)
                paint.invalidate();
                int action = motionEvent.getAction();
                paintView viewP = (paintView)v;
                if(viewP.needsInit()) viewP.init();
                //Uses switch statements to decide what to do based on the action
                switch (action){
                    case MotionEvent.ACTION_DOWN:
                        viewP.drawDot((int)motionEvent.getX(),(int)motionEvent.getY());
                        break;
                    case MotionEvent.ACTION_MOVE:
                        viewP.drawDot((int)motionEvent.getX(),(int)motionEvent.getY());
                        break;
                    default:
                        break;
                }
                //Tells android to do it over as it has expired (hence invalidate)
                paint.invalidate();
                return true;
            }
        });
    }
    protected void onStart() {
        paint.invalidate();
        super.onStart();
        paint.invalidate();
        Log.d("start","started");
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        paint.invalidate();
        Toast toast = Toast.makeText(board.this,"Loaded:" +paint.getContents()[position], Toast.LENGTH_SHORT);
        toast.show();
        Log.d("pos", String.valueOf(position));
        paint.loadCanvas(board.this, paint.getContents()[position]);
        paint.invalidate();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

