package com.example.bmical;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DemoProvider extends ContentProvider {

    // 對外公開的 authority，學生端會用 content://com.example.bmical.demoprovider
    public static final String AUTHORITY = "com.example.bmical.demoprovider";
    private static final String[] COLUMNS = new String[]{"content"};

    // 假資料（可以想像是 BMI 計算紀錄）
    private final List<String> data = new ArrayList<>(Arrays.asList(
            "2025-08-01 09:00 → BMI: 22.5",
            "2025-08-02 09:00 → BMI: 22.3",
            "Hello from DemoProvider!"
    ));

    @Override
    public boolean onCreate() {
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        MatrixCursor cursor = new MatrixCursor(COLUMNS);
        for (String row : data) {
            cursor.addRow(new Object[]{row});
        }
        return cursor;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        if (values != null && values.containsKey("content")) {
            data.add(values.getAsString("content"));
        }
        return uri;
    }

    // 這裡課堂不用，所以直接回傳預設值
    @Override public int delete(Uri uri, String selection, String[] selectionArgs) { return 0; }
    @Override public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) { return 0; }
    @Override public String getType(Uri uri) {
        return "vnd.android.cursor.dir/vnd." + AUTHORITY + ".content";
    }
}