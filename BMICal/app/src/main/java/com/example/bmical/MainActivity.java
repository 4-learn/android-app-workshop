package com.example.bmical;

import android.os.Bundle;
import android.widget.Toast;
import android.widget.Button;
import java.text.DecimalFormat;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Intent;
import android.content.IntentFilter;

public class MainActivity extends AppCompatActivity {

    private AirplaneModeReceiver airplaneReceiver = new AirplaneModeReceiver();

    @Override
    protected void onResume() {
        super.onResume();
        // 動態註冊 Receiver
        IntentFilter filter = new IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        registerReceiver(airplaneReceiver, filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // 記得解除註冊，避免 memory leak
        unregisterReceiver(airplaneReceiver);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        findViewById(R.id.btn_start_service).setOnClickListener(v ->
                startService(new Intent(this, MyService.class)));

        findViewById(R.id.btn_stop_service).setOnClickListener(v ->
                stopService(new Intent(this, MyService.class)));

        // Dashboard
        Button buttonDashboard = findViewById(R.id.btn_dashboard);
        buttonDashboard.setOnClickListener(goToTODOsToast);

        // 設置首頁的按鈕
        Button button1 = findViewById(R.id.btn_home);
        button1.setOnClickListener(goToBMIcal);

        // 設置 Profile 頁面的按鈕
        Button button2 = findViewById(R.id.btn_about_me);
        button2.setOnClickListener(goToProfile);
    }

    // btn_home - 切換到計算BMI的佈局
    private View.OnClickListener goToBMIcal = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            setContentView(R.layout.cal);

            // 在新佈局中再次綁定視圖並設置事件處理器
            Button calculateButton = findViewById(R.id.button);
            calculateButton.setOnClickListener(calcBMI);
        }
    };

    // BMI計算邏輯
    private View.OnClickListener calcBMI = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            DecimalFormat nf = new DecimalFormat("0.00");
            EditText fieldheight = findViewById(R.id.height);
            EditText fieldweight = findViewById(R.id.weight);

            double height = Double.parseDouble(fieldheight.getText().toString()) / 100;   // 身高
            double weight = Double.parseDouble(fieldweight.getText().toString());         // 體重
            double BMI = weight / (height * height);                                      // BMI 值

            // 顯示結果
            TextView result = findViewById(R.id.result);
            result.setText(getText(R.string.bmi_result) + nf.format(BMI));

            // 顯示建議
            TextView fieldsuggest = findViewById(R.id.suggest);
            if (BMI > 25.0D) {         // 太重了
                fieldsuggest.setText(R.string.advice_heavy);
            } else if (BMI < 20.0D) {  // 太輕了
                fieldsuggest.setText(R.string.advice_light);
            } else {                    // 剛剛好
                fieldsuggest.setText(R.string.advice_average);
            }
        }
    };

    private View.OnClickListener goToTODOsToast = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(getApplicationContext(), "此功能仍在開發中", Toast.LENGTH_SHORT).show();
        }
    };

    // btn_home - 切換到 goToProfile 的佈局
    private View.OnClickListener goToProfile = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            setContentView(R.layout.profile);
        }
    };
}