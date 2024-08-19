package com.example.gallery;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class FullImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_image);

        // 獲取從 MainActivity 傳遞過來的圖片資源 ID
        int imageResId = getIntent().getIntExtra("imageResId", R.drawable.sample_image);

        // 設置全屏圖片
        ImageView fullImageView = findViewById(R.id.fullImageView);
        fullImageView.setImageResource(imageResId);
    }
}