package com.example.project2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.project2.DB.AppDataBase;
import com.example.project2.DB.InventoryDAO;

import java.util.List;

public class CartActivity extends AppCompatActivity {
    private static final String USER_ID_KEY = "com.example.project2.userIdKey";

    private TextView mCartText;
    private Button mPurchase;
    private Button mRemove;
    private InventoryDAO mInventoryDAO;
    private List<Inventory> mInventory;
    private List<User> mUsers;
    private User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_cart);

        getDatabase();

    }

    private void getDatabase() {
        mInventoryDAO = (InventoryDAO) Room.databaseBuilder(this, AppDataBase.class, AppDataBase.DB_NAME)
                .allowMainThreadQueries()
                .build()
                .getInventoryDAO();
    }


    public static Intent intentFactory(Context context, int userId){
        Intent intent = new Intent(context, CartActivity.class);
        intent.putExtra(USER_ID_KEY, userId);
        return intent;
    }

}
