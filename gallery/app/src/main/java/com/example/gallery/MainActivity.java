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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupGalleryView();
    }

    private void setupGalleryView() {
        // 設置所有縮圖的點擊事件
        for (int i = 0; i < imageIds.length; i++) {
            int imageViewId = getResources().getIdentifier("imageView" + (i + 1), "id", getPackageName());
            setThumbnailClickListener(imageViewId, imageIds[i]);
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