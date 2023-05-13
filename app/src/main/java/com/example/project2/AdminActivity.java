package com.example.project2;


import androidx.room.Room;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project2.DB.AppDataBase;
import com.example.project2.DB.InventoryDAO;

import java.util.List;

public class AdminActivity extends AppCompatActivity{

    private static final String USER_ID_KEY = "com.example.project2.userIDKey";

    private TextView mStockDisplay;
    private EditText mEnterName;
    private EditText mEnterPrice;
    private EditText mEnterStock;
    private Button mSubmitButton;
    private TextView mUserDisplay;

    private InventoryDAO mInventoryDAO;
    private List<Inventory> mInventory;
    private List<User> mUsers;
    private int mUserID = -1;
    private User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        getDatabase();
        loginUser(mUserID);

        mStockDisplay = findViewById(R.id.textViewInventory);
        mEnterName = findViewById(R.id.editTextItemName);
        mEnterPrice = findViewById(R.id.editTextItemValue);
        mEnterStock = findViewById(R.id.editTextItemQuantity);
        mSubmitButton = findViewById(R.id.buttonSubmitButton);

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Inventory stock = getValuesFromDisplay();

                int stockQ = 0;
                try {
                    stockQ = Integer.parseInt(String.valueOf(mEnterStock.getText()));
                } catch (NumberFormatException e) {
                    Log.d("INVENTORY", "Couldn't Convert Inventory");
                }
                for (int i = 0; i < stockQ; i++) {
                    mInventoryDAO.insert(stock);
                }
            }
        });
    }

    private void getDatabase() {
        mInventoryDAO = (InventoryDAO) Room.databaseBuilder(this, AppDataBase.class, AppDataBase.DB_NAME)
                .allowMainThreadQueries()
                .build()
                .getInventoryDAO();
    }

    private void setContentView(int activity_admin) {
    }

    private void loginUser(int userID) {
        mUser = mInventoryDAO.getUserByUserId(userID);
//        invalidateOptionsMenu();
    }


    public static Intent intentFactory(Context context, int userID) {
        Intent intent = new Intent(context, AdminActivity.class);
        intent.putExtra(USER_ID_KEY, userID);

        return intent;
    }
}
