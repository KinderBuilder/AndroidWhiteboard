/*
* Changelog:
*In board.java
*
*In paintView.java
*loadCanvas has new parameter
*
*
*
*
*
*
*
* */


package com.example.hellobird;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Canvas;

import android.os.Build;

import android.os.Debug;
import android.util.AttributeSet;
import android.util.Log;

import android.view.View;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.file.DirectoryNotEmptyException;
import java.util.ArrayList;
import java.util.Arrays;


public class paintView extends View{

    private Paint brush;
    private Canvas can;
    private int[][] grid;
    private int cellSize;
    private ArrayList<String> files;
    private String contents[];
    File directory;
    private String currentFile;

    //Static means that it is a part of the class, not the object (aka only one color out of the many paintView objects)
    public static int color = Color.BLACK;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public paintView(Context context, AttributeSet attribute) {
        //Calls the super constructor

        //Purpose
        //Function
        super(context, attribute);
        brush = new Paint();
        cellSize = 10;

        File directory = new File(context.getFilesDir().toString());
        contents = directory.list();
        Log.d("paintView","paintView constructor has ran");
    }
    //Auto generates the grid
    public boolean needsInit(){
        return grid == null;
    }

    public void init(){
        int rows = getHeight()/cellSize;
        int collums = getWidth()/cellSize;
        grid = new int[rows][collums];
        for(int i = 0; i < rows; i++){
            for(int x = 0; x < collums; x++){
                grid[i][x] = Color.WHITE;
            }
        }
    }

    //Difference between
    public void generateGrid(){
        int rows = getHeight()/cellSize;
        int collums = getWidth()/cellSize;
        grid = new int[rows][collums];
    }
    //Draws the canvas/drawable area on the screen
    public void onDraw(Canvas canvas){
        can = canvas;
        Log.d("whenRun", "onDraw has ran but idk when since this is a preprogramed message. AUTOCORRECT");
        //Access a function in super, not the constructor
        super.onDraw(canvas);
        canvas.drawPaint(brush);
        brush.setColor(Color.WHITE);
        //invalidate();
        //How it draws the canvas, the first two are the coords of the first dot, the last twoare the coords of the last two dots. The two dots are used to make the rectangle
        if(!needsInit()) {
            for (int i = 0; i < grid.length; i++) {
                for (int x = 0; x < grid[i].length; x++) {
                    brush.setColor(grid[i][x]);
                    canvas.drawRect(x * cellSize, i * cellSize, (x + 1) * cellSize, (i + 1) * cellSize, brush);
                }
            }
        }
        //canvas.drawRect(getLeft()+10.0f, getTop()+10.0f,getRight()-10.0f, getBottom()-10.0f, brush);
    }

    //Draws a dot on the screen
    public void drawDot(int x, int y){
        //Converts the coordinates of the x and y from the screen to the canvas
        //x -= getLeft();
        //y -= getTop();
        int row = y/cellSize;
        int collum = x/cellSize;
        //For out or bounds
        if(row>0&&row<grid.length&&collum>0&&collum<grid[grid.length-1].length){
            Log.d("results", row + ":" + collum);
            Log.d("cords", x + ":" + y);
            grid[row][collum] = color;
        }
    }

    //Draws a line on the screen
    public void drawLine(int startX, int startY, int endX, int endY){
        //The variables for calculating the cells between the start and end of the line, not the pixels
        int startRow = startY/cellSize;
        int startCollum = startX/cellSize;
        int endRow = endY/cellSize;
        int endCollum = endX/cellSize;

        //The variables on where the loop should start & end
        int topRow = Math.min(startRow,endRow);
        int bottomRow = Math.max(startRow,endRow);
        int topCollum = Math.min(startCollum,endCollum);
        int bottomCollum = Math.max(startCollum,endCollum);


    }

    public void clearCan(){
        //Note: For each loop, useful for arrays, Downside: no index access (ex: x, y, hotdogs, ect)
        for (int[] ints : grid) {
            Arrays.fill(ints, Color.WHITE);
        }
    }
    public void saveCanvas(Context context, String fileName){
        try {
            //Creates the writer
            //Located in data/com.example.hellobird/files
            updateContents(context);
            //Prints out all the contents in files
            for(int i = 0; i < contents.length; i++){
                Log.d("Contents Of File", contents[i]);
            }
            //Saves the current file
            OutputStreamWriter writer = new OutputStreamWriter(context.openFileOutput(fileName,Context.MODE_PRIVATE));
            Log.d("FileInfo","Directory of DrawingTest" + directory.toString());
            for(int y = 0; y < grid.length; y++){
                for(int x = 0; x <grid[y].length; x++){
                    writer.write(""+grid[y][x]);
                    if(x<grid[y].length-1){
                        writer.write(",");
                    }
                }
                writer.write("\n");
            }
            Log.d("File","Saving Now... File saved was manually chosen");
            Log.d("FileInfo",context.getFilesDir().getAbsolutePath());
            Log.d("FileInfo",fileName);
            //Signals that it's job is finished & allows other things to manipulate the file
            writer.close();
        }catch (IOException e){
            Log.e("Grid Saving Error",e.toString());
        }
    }
    public void createCanvas(Context context, String fileName){
        if(currentFile!=null){
            saveCanvas(context,currentFile);
        }
        //Creates a blank canvas
        clearCan();
        Log.d("context1234567890",context.toString());
        //Saves it as a new canvas
        try {
            if(!fileName.contains(".txt")){
                fileName=fileName+".txt";
            }
            //For explanation, look at saveCanvas
            OutputStreamWriter writer = new OutputStreamWriter(context.openFileOutput(fileName, Context.MODE_PRIVATE));;
            for (int y = 0; y < grid.length; y++) {
                for (int x = 0; x < grid[y].length; x++) {
                    writer.write("" + grid[y][x]);
                    if (x < grid[y].length - 1) {
                        writer.write(",");
                    }
                }
                writer.write("\n");
            }
            writer.close();
            invalidate();
            Toast toast = Toast.makeText(context, "Created File: " + fileName, Toast.LENGTH_SHORT);
            toast.show();
            updateContents(context);
        }catch (IOException e){
            Log.e("Grid Saving Error",e.toString());
        }
    }
    public void loadCanvas(Context context, String fileName){
        Log.d("loadCanvas",fileName);
        Log.d("loadCanvas", String.valueOf(contents.length));
        //Test to see if the grid is blank
        if(grid==null){
            Log.d("loadCanvas","null");
            generateGrid();
        }
        //Error: GridTest is null becuase it is not filled with anything unless the canvas is drawn on. Solution, Look at where onDraw is called and what it does to the grid
        try{
            //Raw string of the file location
            for(int i = 0; i < contents.length; i++) {
                //Checks to
                if ((fileName).equals(contents[i])) {
                    InputStream inputStream = context.openFileInput(fileName);
                    if (inputStream == null) {
                        Log.e("Grid Loading Error", "Cannot find file");
                        return;
                    }
                    //Reads the raw data
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                    //Refines/Parces the data
                    BufferedReader reader = new BufferedReader(inputStreamReader);
                    //
                    for (int y = 0; y < grid.length; y++) {
                        String storage = reader.readLine();
                        String data[] = storage.split(",");
                        for (int x = 0; x < grid[y].length; x++) {
                            grid[y][x] = Integer.parseInt(data[x]);
                        }
                    }
                    break;
                } else if (i + 1 == contents.length) {
                    Toast toast = Toast.makeText(context, "Error: File Not Found", Toast.LENGTH_SHORT);
                    toast.show();
                }
                invalidate();
            }
            updateContents(context);
            currentFile = fileName;
            Log.d("currentFile", currentFile);
        }catch(IOException e){
            Log.e("Grid Loading Error", e.toString());
        }
    }
    public void deleteFile(Context context, String fileName){
        updateContents(context);
        if(contents.length<=1){
            Log.d("DeleteFiles","Too Short");
            Log.d("DeleteFiles",String.valueOf(contents.length));
        }else{
            try {
                if(directory==null){
                    updateDirectory(context);
                }
                File deleteThisFile = new File(directory,fileName);
                if (deleteThisFile.delete()) {
                    Log.d("DeleteError", "No Delete Errors");
                }else{
                    Log.d("DeleteError", "Failed to Delete");
                }
                //printContents();
                Log.d("DeleteFiles",String.valueOf(contents.length));
            }
            catch (NoSuchFieldError e){
                Log.e("DeleteError","No such file/directory");
            }
        }
        updateContents(context);
        loadCanvas(context,contents[0]);
    }

    public String[] getContents(){
        return contents;
    }
    public void printContents(){
        for(int i = 0; i < contents.length; i++) {
            Log.d("ContentsOfFile", contents[i]);
        }
    }
    public void updateContents(Context context){
        directory = new File(context.getFilesDir().toString());
        contents = directory.list();
    }
    public void updateDirectory(Context context){
        directory = new File(context.getFilesDir().toString());
    }
    public int[][] getGrid(){
        return grid;
    }

    //Fix a warning that isn't really helpful (I think)
    @Override
    public boolean performClick(){return true;}

}
