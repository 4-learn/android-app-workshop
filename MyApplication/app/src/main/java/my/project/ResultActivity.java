package my.project;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;

public class ResultActivity extends AppCompatActivity {

    // 收到的鍵名
    private static final String KEY_NAME = "KEY_NAME";
    private static final String KEY_AGE = "KEY_AGE";
    private static final String KEY_HEIGHT = "KEY_HEIGHT";
    private static final String KEY_WEIGHT = "KEY_WEIGHT";

    // 回傳的鍵名
    private static final String EXTRA_BMI = "BMI";
    private static final String EXTRA_CATEGORY = "CATEGORY";
    private static final String EXTRA_ADVICE = "ADVICE";
    private static final String EXTRA_CHEER = "CHEER";

    private double result_BMI = -1.0D;
    private String category = "";
    private String advice = "";
    private String cheer = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.result_activity);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        DecimalFormat nf = new DecimalFormat("0.00");

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String name = bundle.getString(KEY_NAME, "");
            String ageStr = bundle.getString(KEY_AGE, "");
            String heightStr = bundle.getString(KEY_HEIGHT, "");
            String weightStr = bundle.getString(KEY_WEIGHT, "");

            double height = 0, weight = 0;
            try {
                height = Double.parseDouble(heightStr) / 100.0; // m
                weight = Double.parseDouble(weightStr);         // kg
            } catch (Exception ignored) {}

            double BMI = (height > 0) ? (weight / (height * height)) : -1.0;
            result_BMI = BMI;

            // 分類與建議（沿用你在 Main 的邏輯）
            if (BMI > 25.0D) {
                category = "偏重";
                advice = getString(R.string.advice_heavy);
                cheer = name.isEmpty() ? "從今天開始，每天快走15分鐘就很棒！" : (name + "，從今天開始，每天快走15分鐘就很棒！");
            } else if (BMI >= 0 && BMI < 20.0D) {
                category = "偏輕";
                advice = getString(R.string.advice_light);
                cheer = name.isEmpty() ? "補充蛋白質、多睡覺，慢慢養回來！" : (name + "，補充蛋白質、多睡覺，慢慢養回來！");
            } else if (BMI >= 0) {
                category = "正常";
                advice = getString(R.string.advice_average);
                cheer = name.isEmpty() ? "保持目前的生活習慣，太棒了！" : (name + "，保持目前的生活習慣，太棒了！");
            } else {
                category = "-";
                advice = "資料不完整，無法計算。";
                cheer = "回上一頁再確認一次資料吧～";
            }

            // 畫面呈現「基本資料 + BMI」
            ((TextView) findViewById(R.id.tv_name_value)).setText("姓名：" + name);
            ((TextView) findViewById(R.id.tv_age_value)).setText("年齡：" + ageStr);
            ((TextView) findViewById(R.id.tv_height_value)).setText("身高：" + heightStr + " cm");
            ((TextView) findViewById(R.id.tv_weight_value)).setText("體重：" + weightStr + " kg");
            ((TextView) findViewById(R.id.tv_bmi_value)).setText(BMI >= 0 ? nf.format(BMI) : "-");
            ((TextView) findViewById(R.id.tv_category_value)).setText("分類：" + category);
            ((TextView) findViewById(R.id.tv_advice_value)).setText("建議：" + advice);
        }

        // 返回時把 BMI + 分類 + 建議 + 鼓勵 一起回傳給 Main
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Intent data = new Intent();
                data.putExtra(EXTRA_BMI, result_BMI >= 0 ? new DecimalFormat("0.00").format(result_BMI) : "-");
                data.putExtra(EXTRA_CATEGORY, category);
                data.putExtra(EXTRA_ADVICE, advice);
                data.putExtra(EXTRA_CHEER, cheer);
                setResult(RESULT_OK, data);
                finish();
            }
        });
    }
}
