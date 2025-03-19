package com.example.ex2;


import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
public class StopwatchService extends Service {

    private long startTime = 0;
    private long elapsedTimeBeforePause = 0;
    private boolean isRunning = false;
    private Handler handler = new Handler();
    private Runnable timerRunnable;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String action = intent.getAction();

        switch (action) {
            case "START":
                if (!isRunning) {
                    startTime = System.currentTimeMillis();
                    isRunning = true;
                    startTimer();
                }
                break;

            case "STOP":
                if (isRunning) {
                    elapsedTimeBeforePause += System.currentTimeMillis() - startTime;
                    isRunning = false;
                    handler.removeCallbacks(timerRunnable);
                }
                break;

            case "RESET":
                startTime = 0;
                elapsedTimeBeforePause = 0;
                isRunning = false;
                handler.removeCallbacks(timerRunnable);
                sendResetBroadcast();
                break;
        }

        return START_STICKY;
    }

    private void startTimer() {
        timerRunnable = new Runnable() {
            @Override
            public void run() {
                if (isRunning) {
                    long elapsed = elapsedTimeBeforePause + (System.currentTimeMillis() - startTime);
                    Intent broadcast = new Intent("STOPWATCH_UPDATE");
                    broadcast.putExtra("elapsed_time", elapsed);
                    sendBroadcast(broadcast);
                    handler.postDelayed(this, 1000);
                }
            }
        };
        handler.post(timerRunnable);
    }

    private void sendResetBroadcast() {
        Intent broadcast = new Intent("STOPWATCH_UPDATE");
        broadcast.putExtra("elapsed_time", 0L);
        sendBroadcast(broadcast);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
