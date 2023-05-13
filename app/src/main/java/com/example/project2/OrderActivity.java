package com.example.project2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.project2.DB.AppDataBase;
import com.example.project2.DB.InventoryDAO;

import java.util.List;

public class OrderActivity extends AppCompatActivity {
    private static final String USER_ID_KEY = "com.example.project2.userIdKey";

    private TextView mDisplayItem;
    private AutoCompleteTextView mSearch;
    private Button mAddToCartButton;
    private InventoryDAO mInventoryDAO;
    private List<Inventory> mInventory;
    private List<User> mUsers;

    private User mUser;
    private int mUserId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        getDatabase();

        loginUser(mUserId);

        mDisplayItem = findViewById(R.id.textView_Inventory);
        mSearch = findViewById(R.id.autoCompleteTextView_Search);
        mAddToCartButton = findViewById(R.id.buttonAdd);

        mAddToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = CartActivity.intentFactory(getApplicationContext(), mUser.getUserID());
                startActivity(intent);
            }
        });
    }

    private void loginUser(int userId) {
        mUser = mInventoryDAO.getUserByUserId(userId);
        invalidateOptionsMenu();
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
