package com.example.project2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.project2.DB.AppDataBase;
import com.example.project2.DB.InventoryDAO;

public class OrderActivity extends AppCompatActivity {
    private static final String USER_ID_KEY = "com.example.project2.userIdKey";
    private InventoryDAO mInventoryDAO;
    private User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getDatabase();

    }

    private void getDatabase() {
        mInventoryDAO = (InventoryDAO) Room.databaseBuilder(this, AppDataBase.class, AppDataBase.DB_NAME)
                .allowMainThreadQueries()
                .build()
                .getInventoryDAO();
    }

    public static Intent intentFactory(Context context, int userId){
        Intent intent = new Intent(context, OrderActivity.class);
        intent.putExtra(USER_ID_KEY, userId);
        return intent;
    }
}
