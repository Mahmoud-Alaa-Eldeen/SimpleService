// issue : when app destroy, service recreate again
    //You may check inside onCreate & onStartCommand not to run the code
    // when service is recreated (for ex. check is it  time to alarm or not, if true, run the alarm).

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

import java.util.Random;

/**
 * Created by mahmoud on 06/09/17.
 */

public class HelloService extends Service {

    // Binder given to clients
    private final IBinder mBinder = new LocalBinder();
    private final Random mgenerator = new Random();
    // indicates whether onRebind should be used
    boolean mAllowRebind;

    @Nullable
    @Override // A client is binding to the service
    public IBinder onBind(Intent intent) {

        Toast.makeText(this, "onBind", Toast.LENGTH_SHORT).show();


        return mBinder;
    }

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

    @Override // called when all clients have unbound with unbindService
    public boolean onUnbind(Intent intent) {
        Toast.makeText(this, "onUnbind", Toast.LENGTH_SHORT).show();
        return mAllowRebind;
    }

    @Override
    public void onRebind(Intent intent) {
        Toast.makeText(this, "onRebind" +
                "", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onDestroy() {

        super.onDestroy();
        Toast.makeText(this, "Service destroyed !", Toast.LENGTH_SHORT).show();
    }

    //  to make instance of service
    public class LocalBinder extends Binder {

        HelloService getService() {

            return HelloService.this;

        }
    }

    public int getRandomNumber() {

        return mgenerator.nextInt(100);
    }
}
