package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.project2.DB.InventoryDAO;

public class MainActivity extends AppCompatActivity {

    private static final String USER_ID_KEY = "com.example.project2.userIDKey";
    private static final  String PREFERENCES_KEY = "com.example.project2.PREFERENCES_KEY";

    private TextView mMainMenuTitle;

    private Button mShopButton;
    private Button mOrderHistoryButton;
    private Button mAdminButton;

    private InventoryDAO mStockDAO;

    private int mUserID = -1;

    private SharedPreferences mPreferences = null;
    private User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}