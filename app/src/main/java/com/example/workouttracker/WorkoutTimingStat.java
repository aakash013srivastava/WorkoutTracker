package com.example.workouttracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class WorkoutTimingStat extends AppCompatActivity {
TextView stat_display;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_timing_stat);
        Intent intent = getIntent();
        String stat = intent.getStringExtra(MainActivity.TIME_STAT);
        stat_display = findViewById(R.id.statDisplay);
        stat_display.setText(stat);
    }
}