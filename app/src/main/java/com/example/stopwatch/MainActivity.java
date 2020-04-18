package com.example.stopwatch;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {
    private int seconds = 0;
    private boolean running;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null){
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
        }
        runTime();
    }

    public void onSaveInstanceState(Bundle savedInstanceState){
        savedInstanceState.putInt("seconds", seconds);
        savedInstanceState.putBoolean("running", running);
    }

    public void startStopWatch(View view) {
        running = true;
    }

    public void stopWatch(View view) {
        running = false;
    }

    public void resetStopWatch(View view) {
        running = false;
        seconds = 0;
    }

    private void runTime(){
        final TextView timeview = findViewById(R.id.txtTimeView);

        final Handler handler= new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds/3600;
                int minutes = (seconds%3600)/60;
                int secs = seconds%60;
                String time = String.format("%d:%02d:%02d", hours, minutes, secs);
                timeview.setText(time);

                if (running){
                    seconds++;
                }
                handler.postDelayed(this, 1000);
            }
        });//used to schedule a code - creating delay


    }
}
