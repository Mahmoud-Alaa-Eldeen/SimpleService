package com.innovdroid.simpleservice;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;

import java.util.Calendar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startService(View view) {

        // startService(new Intent(getBaseContext(), HelloService.class));

        Intent myIntent = new Intent(MainActivity.this, HelloService.class);
        AlarmManager alarmMnager = (AlarmManager) getSystemService(ALARM_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getService(MainActivity.this, 0, myIntent, 0);

        Calendar calendar = getUserAlarmTime();
        // to repeat alarm every day. run pendingIntent (go to service to fire a sound/ notification.. etc)
        alarmMnager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 24 * 60 * 60 * 1000, pendingIntent);
    }

    public void stopService(View view) {

        stopService(new Intent(getBaseContext(), HelloService.class));

    }

    // can pass params gotten from user
    Calendar getUserAlarmTime() {
        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.HOUR_OF_DAY, 00);
        calendar.set(Calendar.MINUTE, 20);
        calendar.set(Calendar.SECOND, 0);
        return calendar;

    }


    public void getFromService(View view) {

    }
}

