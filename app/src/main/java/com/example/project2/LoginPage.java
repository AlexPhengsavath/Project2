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

import com.example.project2.DB.InventoryDAO;

public class LoginPage extends AppCompatActivity {
    private TextView mLoginText;

    private EditText mUsernameText;
    private EditText mPasswordText;

    private Button mLoginButton;

    private Button mRegisterButton;

    private InventoryDAO mInventoryDAO;
    
    private String mUsernameString;
    
    private String mPasswordString;
    
    private User mUser;
    
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        
        wireupDisplay();
        getDatabase();
        
    }

    private void getDatabase() {
    }

    private void wireupDisplay() {
        mLoginText = findViewById(R.id.textViewLoginText);

        mUsernameText = findViewById(R.id.editText_Username);

        mPasswordText = findViewById(R.id.editText_Password);

        mLoginButton = findViewById(R.id.buttonLogin);

        mRegisterButton = findViewById(R.id.buttonRegister);

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getValuesFromDisplay();
                if(checkForUserInDatabase()){
                    if(!validatePassword()){
                        Toast.makeText(LoginPage.this, "Invalid password", Toast.LENGTH_SHORT).show();
                    }else{
                        Intent intent = MainActivity.intentFactory(this, mUser.getUserID());
                        startActivity(intent);
                    }
                }
            }
        });

        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getValuesFromDisplay();
                if(checkForUserInDatabase() == false){
                    User user = new User(mUsernameString, mPasswordString);
                    mInventoryDAO.insert(user);

                    Intent intent = MainActivity.intentFactory(this, mUser.getUserID());
                    startActivity(intent);
                }
            }
        });
    }

    private boolean validatePassword() {
        return false; //implement
    }

    private boolean checkForUserInDatabase() {
        return false; //implement
    }

    private void getValuesFromDisplay() {
    }

    public static Intent intentFactory(Context context){
        Intent intent = new Intent(context, LoginPage.class);
        return intent;
    }

}
