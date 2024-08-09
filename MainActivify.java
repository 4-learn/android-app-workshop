package my.project;

import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) ->
        {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        getSupportActionBar().setTitle("MyBMI");

        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(calcBMI);
    }

    private View.OnClickListener calcBMI = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            DecimalFormat nf = new DecimalFormat("0.00");
            EditText fieldheight = (EditText)findViewById(R.id.height);
            EditText fieldweight = (EditText)findViewById(R.id.weight);

            double height = Double.parseDouble(fieldheight.getText().toString()) / 100;   // 身高（以公尺計算）
            double weight = Double.parseDouble(fieldweight.getText().toString());         // 體重
            double BMI = weight / (height*height);                                        // 計算 BMI 值

            // 顯示 BMI 值
            TextView result = (TextView)findViewById(R.id.result);
            result.setText(getText(R.string.bmi_result) + nf.format(BMI));

            // 顯示建議
            TextView fieldsuggest = (TextView)findViewById(R.id.suggest);

            if(BMI > 24.0D)         // 太重了，計算需要減少的體重
            {
                double targetWeight = 24.0 * (height * height);   // 目標體重
                double weightToLose = weight - targetWeight;      // 需要減少的體重
                fieldsuggest.setText("你需要減少 " + nf.format(weightToLose) + " 公斤");
            }
            else if(BMI < 18.5D)    // 太輕了，計算需要增加的體重
            {
                double targetWeight = 18.5 * (height * height);   // 目標體重
                double weightToGain = targetWeight - weight;      // 需要增加的體重
                fieldsuggest.setText("你需要增加 " + nf.format(weightToGain) + " 公斤");
            }
            else                    // 體重正常
            {
                fieldsuggest.setText(R.string.advice_average);
            }
        }
    };
}
