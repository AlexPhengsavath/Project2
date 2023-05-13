package com.example.project2;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project2.DB.InventoryDAO;

public class LoginPage extends AppCompatActivity {
    private TextView mLoginText;

    private EditText mUsernameText;
    private EditText mPasswordText;

    private Button mLoginButton;

    private Bundle mRegisterButton;

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
    }

}
