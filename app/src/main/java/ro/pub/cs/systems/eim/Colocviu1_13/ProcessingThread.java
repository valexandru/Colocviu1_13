package ro.pub.cs.systems.eim.Colocviu1_13;

import android.content.Context;
import android.content.Intent;
import android.os.Process;
import android.util.Log;

import java.util.Date;
import java.util.Random;

public class ProcessingThread extends Thread {

    private Context context = null;
    private String instructions;

    public ProcessingThread(Context context, String instr) {
        this.context = context;

        instructions = instr;
    }

    @Override
    public void run() {
        Log.d("ProcessingThread", "Thread has started! PID: " + Process.myPid() + " TID: " + Process.myTid());
        sleep();
        sendMessage();
        Log.d("ProcessingThread", "Thread has stopped!");
    }

    private void sendMessage() {
        Intent intent = new Intent();
        intent.setAction("ro.pub.cs.systems.eim.practicaltest01.sendMessage");
        intent.putExtra("message",
                new Date(System.currentTimeMillis()) + " " + instructions);
        context.sendBroadcast(intent);
    }

    private void sleep() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

    public void stopThread() {
    }
}
