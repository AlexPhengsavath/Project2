package com.example.project2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.project2.DB.AppDataBase;
import com.example.project2.DB.InventoryDAO;

import java.util.List;

public class AdminActivity extends AppCompatActivity{

    private static final String USER_ID_KEY = "com.example.project2.userIDKey";
    private static final  String PREFERENCES_KEY = "com.example.project2.PREFERENCES_KEY";

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
    private SharedPreferences mPreferences = null;
    private User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        getDatabase();
        loginUser(mUserID);

        mStockDisplay = findViewById(R.id.textViewInventory);
        mStockDisplay.setMovementMethod(new ScrollingMovementMethod());
        mEnterName = findViewById(R.id.editTextItemName);
        mEnterPrice = findViewById(R.id.editTextItemValue);
        mEnterStock = findViewById(R.id.editTextItemQuantity);
        mSubmitButton = findViewById(R.id.buttonSubmitButton);
        mUserDisplay.setMovementMethod(new ScrollingMovementMethod());


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
                refreshDisplay();
            }
        });
    }

    private void getDatabase() {
        mInventoryDAO = (InventoryDAO) Room.databaseBuilder(this, AppDataBase.class, AppDataBase.DB_NAME)
                .allowMainThreadQueries()
                .build()
                .getInventoryDAO();
    }

//    private void setContentView(int activity_admin) {
//    }

    private void loginUser(int userID) {
        mUser = mInventoryDAO.getUserByUserId(userID);
//        invalidateOptionsMenu();
    }

    private Inventory getValuesFromDisplay() {
        String name = "Bottle";
        double value = 0.0;
        int inventory = 0;

        name = mEnterName.getText().toString();

        try {
            value = Double.parseDouble(mEnterPrice.getText().toString());
        } catch (NumberFormatException e) {
            Log.d("INVENTORY", "Couldn't Convert Value");
        }

        try {
            inventory = Integer.parseInt(mEnterStock.getText().toString());
        } catch (NumberFormatException e) {
            Log.d("INVENTORY", "Couldn't Convert Inventory");
        }

        Inventory newInventory = new Inventory(name, value, inventory, mUserID);

        return newInventory;
    }

    private void refreshDisplay() {
        mInventory = mInventoryDAO.getInventoryByUserId(mUserID);
        mUsers = mInventoryDAO.getAllUsers();

        if (mInventory.size() <= 0) {
            mStockDisplay.setText("No Stock");
            return;
        }

        if (mUsers.size() <= 0) {
            mUserDisplay.setText("No Users");
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (Inventory stock : mInventory) {
            sb.append(stock);
            sb.append("\n");
            sb.append("=-=-=-=-=-=-=-=-=");
            sb.append("\n");
        }
        mStockDisplay.setText(sb.toString());

        StringBuilder strB = new StringBuilder();
        for (User user : mUsers) {
            strB.append(user);
            strB.append("\n");
            strB.append("=-=-=-=-=-=-=-=-=");
            strB.append("\n");
        }
        mUserDisplay.setText(strB.toString());
    }

    public static Intent intentFactory(Context context, int userID) {
        Intent intent = new Intent(context, AdminActivity.class);
        intent.putExtra(USER_ID_KEY, userID);

        return intent;
    }
}
