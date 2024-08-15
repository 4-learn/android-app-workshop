package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.display);
    }

    // 處理數字按鍵點擊事件
    public void onNumberClick(View view) {
        Button button = (Button) view;
        String value = button.getText().toString();
        if (display.getText().toString().equals("0")) {
            display.setText(value);
        } else {
            display.append(value);
        }
    }
}