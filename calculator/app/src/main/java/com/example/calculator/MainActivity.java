package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView display;
    private double firstValue = 0;
    private double secondValue = 0;
    private String operator = "";
    private boolean operatorSelected = false;

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

        if (operatorSelected) {
            display.setText(value);
            operatorSelected = false;
        } else {
            if (display.getText().toString().equals("0")) {
                display.setText(value);
            } else {
                display.append(value);
            }
        }
    }

    // 處理操作符按鍵點擊事件
    public void onOperatorClick(View view) {
        Button button = (Button) view;
        operator = button.getText().toString();
        firstValue = Double.parseDouble(display.getText().toString());
        operatorSelected = true;
    }

    // 處理等於按鍵點擊事件
    public void onEqualClick(View view) {
        secondValue = Double.parseDouble(display.getText().toString());
        double result = 0;

        switch (operator) {
            case "+":
                result = firstValue + secondValue;
                break;
            case "-":
                result = firstValue - secondValue;
                break;
        }

        display.setText(String.valueOf(result));
        operator = "";
        operatorSelected = true;
    }

    // 處理清除按鍵點擊事件
    public void onClearClick(View view) {
        display.setText("0");
        firstValue = 0;
        secondValue = 0;
        operator = "";
        operatorSelected = false;
    }
}