package com.innovdroid.simpleservice;

import android.app.Service;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.widget.Toast;



public class HelloService extends Service {


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    
    // issue : when app destroy, service recreate again
    //You may check inside onCreate & onStartCommand not to run the code
    // when service is recreated (for ex. check is it  time to alarm or not, if true, run the alarm).
    @Override
    public void onCreate() {
        Toast.makeText(this, "Service created", Toast.LENGTH_SHORT).show();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "Alert", Toast.LENGTH_SHORT).show();

        try {
            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone ringtone = RingtoneManager.getRingtone(getApplicationContext(), notification);
            ringtone.play();
        } catch (Exception excep) {
            excep.printStackTrace();
        }


        return super.onStartCommand(intent, flags, startId);

    }



    @Override
    public void onDestroy() {

        super.onDestroy();
        Toast.makeText(this, "Service destroyed !", Toast.LENGTH_SHORT).show();
    }



}
