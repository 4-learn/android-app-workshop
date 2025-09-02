package com.example.bmical;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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
import android.content.Intent;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        getSupportActionBar().setTitle("MyBMI");

        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(calcBMI);

        // 1. onCreate()
        Toast.makeText(this, "1. MainActivity onCreate()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        // 2. onStart()
        Toast.makeText(this, "2. MainActivity onStart()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 3. onResume()
        Toast.makeText(this, "3. MainActivity onResume()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        // 4. onPause()
        Toast.makeText(this, "4. MainActivity onPause()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        // 5. onStop()
        Toast.makeText(this, "5. MainActivity onStop()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 6. onDestroy()
        Toast.makeText(this, "6. MainActivity onDestroy()", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            setContentView(R.layout.test);
        }
        else if(id == R.id.new_activity)
        {
            Intent intent = new Intent(MainActivity.this, ResultActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    private View.OnClickListener calcBMI = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            DecimalFormat nf = new DecimalFormat("0.00");
            EditText fieldheight = (EditText)findViewById(R.id.height);
            EditText fieldweight = (EditText)findViewById(R.id.weight);

            double height = Double.parseDouble(fieldheight.getText().toString()) / 100;   // 身高
            double weight = Double.parseDouble(fieldweight.getText().toString());         // 體重
            double BMI = weight / (height*height);                                        // BMI 值

            // 結果
            TextView result = (TextView)findViewById(R.id.result);
            result.setText(getText(R.string.bmi_result) + nf.format(BMI));

            // 建議
            TextView fieldsuggest = (TextView)findViewById(R.id.suggest);
            if(BMI > 25.0D)         // 太重了
                fieldsuggest.setText(R.string.advice_heavy);
            else if(BMI < 20.0D)    // 太輕了
                fieldsuggest.setText(R.string.advice_light);
            else                    // 剛剛好
                fieldsuggest.setText(R.string.advice_average);
        }
    };
}