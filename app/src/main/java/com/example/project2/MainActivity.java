package com.example.project2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.project2.DB.AppDataBase;
import com.example.project2.DB.InventoryDAO;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String USER_ID_KEY = "com.example.project2.userIDKey";
    private static final  String PREFERENCES_KEY = "com.example.project2.PREFERENCES_KEY";

    private TextView mMainMenuTitle;

    private Button mOrderButton;
    private Button mCartButton;
    private Button mAdminButton;

    private InventoryDAO mInventoryDAO;

    private int mUserID = -1;

    private SharedPreferences mPreferences = null;
    private User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        getDatabase();
//
//        checkForUser();
//        addUserToPreference(mUserID);
//
//        loginUser(mUserID);

        mMainMenuTitle = findViewById(R.id.EditTextMenuName);

        mOrderButton = findViewById(R.id.buttonOrder);
        mCartButton = findViewById(R.id.buttonCart);
        mAdminButton = findViewById(R.id.buttonAdmin);

        mOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = OrderActivity.intentFactory(getApplicationContext(), mUser.getUserID());
//                startActivity(intent);
            }
        });

        mCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = CartActivity.intentFactory(getApplicationContext(), mUser.getUserID());
//                startActivity(intent);
            }
        });

        mAdminButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = AdminActivity.intentFactory(getApplicationContext(), mUser.getUserID());
//                startActivity(intent);
            }
        });
    }

        private void getDatabase() {
            mInventoryDAO = Room.databaseBuilder(this, AppDataBase.class, AppDataBase.DB_NAME)
                    .allowMainThreadQueries()
                    .build()
                    .getStockDAO();
        }
        private void checkForUser() {
            mUserID = getIntent().getIntExtra(USER_ID_KEY, -1);

            if (mUserID != -1) {
                return;
            }

            if (mPreferences == null) {
                getPrefs();
            }

            mUserID = mPreferences.getInt(USER_ID_KEY, -1);

            if (mUserID != -1) {
                return;
            }

            List<User> users = mInventoryDAO.getAllUsers();
            if (users.size() <= 0) {
                User adminUser = new User("admin01", "admin01");
                User defaultUser = new User("testUser1", "tester123");
                mInventoryDAO.insert(adminUser, defaultUser);
            }

            Intent intent = LoginPage.intentFactory(this);
            startActivity(intent);
        }

    private void getPrefs() {
        mPreferences = this.getSharedPreferences(PREFERENCES_KEY, Context.MODE_PRIVATE);
    }

    public static Intent intentFactory(Context context, int userID) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(USER_ID_KEY, userID);

        return intent;
    }
}