package com.example.workouttracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
Button startButton,breakButton,stopButton;
String output = "";
Boolean isBreak = false;
String startTime = "",stopTime = "";
public static final String TIME_STAT = "com.example.workouttracker.TIME_STAT";

public static String timeCalc(){
    SimpleDateFormat formatter= new SimpleDateFormat("dd-MM-yyyy 'at' HH:mm:ss z");
    Date date = new Date(System.currentTimeMillis());
    String curDate = (formatter.format(date));
    return curDate;
}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startButton = findViewById(R.id.startButton);
        breakButton = findViewById(R.id.breakButton);
        stopButton = findViewById(R.id.stopButton);

        // Disabling Break and stop button initially
        breakButton.setEnabled(false);
        stopButton.setEnabled(false);

        //Creating collection to store timestamps

        // Start of workout start button event listener
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                breakButton.setEnabled(true);
                stopButton.setEnabled(true);
                startButton.setEnabled(false);

                // Save workout start time
                startTime = timeCalc();
                //Add start time to output
                output += "BEGIN:"+startTime+"\n";
                // To add break timestamps to output string
                breakButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(!isBreak){
                            String curDate = timeCalc();
                            output += "Break Start At:"+curDate+"\n";
                            Toast.makeText(getApplicationContext(), curDate, Toast.LENGTH_SHORT).show();
                            breakButton.setText("Break off");
                        }else{
                            String curDate = timeCalc();
                            output += "Break Over At:"+curDate+"\n";
                            Toast.makeText(getApplicationContext(), curDate, Toast.LENGTH_SHORT).show();
                            breakButton.setText("Break on");
                        }
                        isBreak = !isBreak;
                    }
                });
                //
                stopButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        breakButton.setEnabled(false);
                        stopButton.setEnabled(false);
                        startButton.setEnabled(true);

                        // Save workout stop time
                        stopTime = timeCalc();
                        // Append stop time to output
                        output += "END:"+stopTime;
                        Intent intent = new Intent(MainActivity.this,WorkoutTimingStat.class);
                        intent.putExtra(TIME_STAT,output);
                        startActivity(intent);
                    }
                });
            }
        });
    }
}