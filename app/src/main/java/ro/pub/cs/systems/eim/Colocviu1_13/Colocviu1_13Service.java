package ro.pub.cs.systems.eim.Colocviu1_13;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class Colocviu1_13Service extends Service {
    ProcessingThread processingThread = null;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("MainActivity", "Sunt in service!!!");
        String instr = intent.getStringExtra("instructionsForService");
        processingThread = new ProcessingThread(this, instr);
        processingThread.start();
        return Service.START_REDELIVER_INTENT;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        processingThread.stopThread();
    }
}
