package com.example.bmical;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class AirplaneModeReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        boolean isOn = intent.getBooleanExtra("state", false);
        String msg = isOn ? "飛航模式：開啟" : "飛航模式：關閉";
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
