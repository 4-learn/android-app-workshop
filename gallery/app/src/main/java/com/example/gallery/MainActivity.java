package com.example.gallery;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private int[] imageIds = {
            R.drawable.sample_image,
            R.drawable.sample_image,
            R.drawable.sample_image,
            R.drawable.sample_image,
            R.drawable.sample_image,
            R.drawable.a1,
            R.drawable.sample_image,
            R.drawable.sample_image,
            R.drawable.sample_image
    };

    // 定義 ImageView 的資源 ID 數組
    private int[] imageViewIds = {
            R.id.imageView1,
            R.id.imageView2,
            R.id.imageView3,
            R.id.imageView4,
            R.id.imageView5,
            R.id.imageView6,
            R.id.imageView7,
            R.id.imageView8,
            R.id.imageView9
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupGalleryView();
    }

    private void setupGalleryView() {
        // 設置所有縮圖的點擊事件
        for (int i = 0; i < imageIds.length; i++) {
            setThumbnailClickListener(imageViewIds[i], imageIds[i]);
        }
    }

    private void setThumbnailClickListener(int imageViewId, final int imageResId) {
        ImageView imageView = findViewById(imageViewId);
        imageView.setOnClickListener(v -> {
            // 啟動 FullImageActivity 並傳遞圖片資源 ID
            Intent intent = new Intent(MainActivity.this, FullImageActivity.class);
            intent.putExtra("imageResId", imageResId);
            startActivity(intent);
        });
    }
}