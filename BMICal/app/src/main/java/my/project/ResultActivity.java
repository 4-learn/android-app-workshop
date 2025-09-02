package com.example.bmical;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        // 1. onCreate()
        Toast.makeText(this, "1. ResultActivity onCreate()", Toast.LENGTH_SHORT).show();

        // 用來再次啟動 ResultActivity 的按鈕
        Button relaunchButton = findViewById(R.id.relaunch_button);
        relaunchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this, ResultActivity.class);
                startActivity(intent);
            }
        });

        // 啟動 MainActivity 的按鈕
        Button launchMainButton = findViewById(R.id.btn_launch_main);
        launchMainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        // 2. onStart()
        Toast.makeText(this, "2. ResultActivity onStart()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 3. onResume()
        Toast.makeText(this, "3. ResultActivity onResume()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        // 4. onPause()
        Toast.makeText(this, "4. ResultActivity onPause()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        // 5. onStop()
        Toast.makeText(this, "5. ResultActivity onStop()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 6. onDestroy()
        Toast.makeText(this, "6. ResultActivity onDestroy()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Toast.makeText(this, "ResultActivity onNewIntent()", Toast.LENGTH_SHORT).show();
    }
}