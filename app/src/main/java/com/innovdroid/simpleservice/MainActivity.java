package com.innovdroid.simpleservice;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;

import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    HelloService mservice;
    boolean mbound = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startService(View view) {

        bindService(new Intent(getBaseContext(), HelloService.class), mConnection, Context.BIND_AUTO_CREATE);

    }

    public void stopService(View view) {

       if (mbound){

           unbindService(mConnection);
           mbound=false;
       }

    }


    public void getFromService(View view) {

        if (mbound) {

            int num = mservice.getRandomNumber();
            Toast.makeText(mservice, "Num:" + num, Toast.LENGTH_SHORT).show();
        }
    }


    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder service) {

            HelloService.LocalBinder binder = (HelloService.LocalBinder)service ;
            mservice = binder.getService();
            mbound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mbound = false;
        }
    };
}

