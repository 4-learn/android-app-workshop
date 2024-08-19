package com.example.gallery;

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
            R.drawable.sample_image,
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

    private void setThumbnailClickListener(int imageViewId, int imageResId) {
        ImageView imageView = findViewById(imageViewId);
        imageView.setOnClickListener(v -> {
            // 切換到顯示全屏圖片的布局
            setContentView(R.layout.activity_full_image);
            ImageView fullImageView = findViewById(R.id.fullImageView);
            fullImageView.setImageResource(imageResId);

            // 設置全屏圖片的點擊事件，點擊後回到縮圖顯示
            fullImageView.setOnClickListener(view -> {
                // 切換回縮圖布局並重新設置事件
                setContentView(R.layout.activity_main);
                setupGalleryView(); // 再次設置縮圖的點擊事件
            });
        });
    }
}