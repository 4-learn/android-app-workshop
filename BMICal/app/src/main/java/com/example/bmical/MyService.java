package com.example.bmical;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.widget.Toast;

public class MyService extends Service {
    private final Handler handler = new Handler();
    private final Runnable task = new Runnable() {
        @Override public void run() {
            Toast.makeText(getApplicationContext(), "Hello from Service", Toast.LENGTH_SHORT).show();
            handler.postDelayed(this, 5000); // 每 5 秒一次
        }
    };

    @Override public int onStartCommand(Intent intent, int flags, int startId) {
        handler.post(task);
        return START_NOT_STICKY; // 教學用即可
    }

    @Override public void onDestroy() {
        handler.removeCallbacks(task);
        super.onDestroy();
    }

    @Override public IBinder onBind(Intent intent) { return null; }
}
