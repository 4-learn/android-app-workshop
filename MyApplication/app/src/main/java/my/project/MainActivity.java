package my.project;

import android.view.View;
import android.os.Bundle;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    // 回傳鍵名
    private static final String EXTRA_BMI = "BMI";
    private static final String EXTRA_CATEGORY = "CATEGORY";
    private static final String EXTRA_ADVICE = "ADVICE";
    private static final String EXTRA_CHEER = "CHEER";

    // 送出鍵名
    private static final String KEY_NAME = "KEY_NAME";
    private static final String KEY_AGE = "KEY_AGE";
    private static final String KEY_HEIGHT = "KEY_HEIGHT";
    private static final String KEY_WEIGHT = "KEY_WEIGHT";

    private ActivityResultLauncher<Intent> resultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 初始化 ActivityResultLauncher
        resultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Bundle bundle = result.getData().getExtras();
                        if (bundle != null) {
                            String bmi = bundle.getString(EXTRA_BMI, "-");
                            String category = bundle.getString(EXTRA_CATEGORY, "");
                            String advice = bundle.getString(EXTRA_ADVICE, "");
                            String cheer = bundle.getString(EXTRA_CHEER, "");

                            ((TextView) findViewById(R.id.result))
                                    .setText("Result: " + bmi + (category.isEmpty() ? "" : "（" + category + "）"));
                            ((TextView) findViewById(R.id.suggest))
                                    .setText(advice + (cheer.isEmpty() ? "" : "\n" + cheer));
                        }
                    }
                }
        );

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("MyBMI");
        }

        Button button = findViewById(R.id.button);
        button.setOnClickListener(calcBMI);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            openOptionsDialog();
        } else if (id == R.id.toast) {
            openOptionsToast();
        } else if (id == R.id.new_activity) {
            // 準備傳遞資料到 ResultActivity
            Intent intent = new Intent(MainActivity.this, ResultActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString(KEY_NAME, ((EditText) findViewById(R.id.name)).getText().toString());
            bundle.putString(KEY_AGE, ((EditText) findViewById(R.id.age)).getText().toString());
            bundle.putString(KEY_HEIGHT, ((EditText) findViewById(R.id.height)).getText().toString());
            bundle.putString(KEY_WEIGHT, ((EditText) findViewById(R.id.weight)).getText().toString());
            intent.putExtras(bundle);

            resultLauncher.launch(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    private void openOptionsDialog() {
        new AlertDialog.Builder(this)
                .setTitle("關於Android BMI")
                .setMessage("Android BMI 計算")
                .setPositiveButton("OK", (dialog, which) -> {})
                .show();
    }

    private void openOptionsToast() {
        Toast.makeText(this, "BMI計算機", Toast.LENGTH_SHORT).show();
    }

    private final View.OnClickListener calcBMI = v -> {
        DecimalFormat nf = new DecimalFormat("0.00");
        EditText fieldheight = findViewById(R.id.height);
        EditText fieldweight = findViewById(R.id.weight);

        try {
            double height = Double.parseDouble(fieldheight.getText().toString()) / 100.0; // 身高 m
            double weight = Double.parseDouble(fieldweight.getText().toString());         // 體重 kg
            double BMI = weight / (height * height);

            // 結果
            TextView result = findViewById(R.id.result);
            result.setText(getText(R.string.bmi_result) + nf.format(BMI));

            // 建議
            TextView fieldsuggest = findViewById(R.id.suggest);
            if (BMI > 25.0D)
                fieldsuggest.setText(R.string.advice_heavy);
            else if (BMI < 20.0D)
                fieldsuggest.setText(R.string.advice_light);
            else
                fieldsuggest.setText(R.string.advice_average);
        } catch (Exception e) {
            Toast msg = Toast.makeText(MainActivity.this, "要先輸入身高體重", Toast.LENGTH_SHORT);
            msg.show();
        }

    };
}
