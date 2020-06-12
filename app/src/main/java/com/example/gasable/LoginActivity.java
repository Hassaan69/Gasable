package com.example.gasable;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    EditText username;
    EditText password;
    DatabaseHelper databaseHelper;
    Button LogIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.EditText_UserName_Login);
        password = findViewById(R.id.EditText_Password_Login);
        LogIn = findViewById(R.id.btn_Log_In);
        databaseHelper = new DatabaseHelper(this);

        LogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    String name = username.getText().toString().trim();
                    String Password = password.getText().toString().trim();

                    //Authenticate user
                    User currentUser = databaseHelper.Authenticate(new User(null, null, name, null, Password, null));

                    //Check Authentication is successful or not
                    if (currentUser != null) {
                        Toast.makeText(LoginActivity.this, "Successfully Logged in!", Toast.LENGTH_SHORT).show();
                        //User Logged in Successfully Launch You home screen activity
                        Intent intent = new Intent(LoginActivity.this, CylinderActivity.class);
                        startActivity(intent);
                    } else {

                        //User Logged in Failed
                        Toast.makeText(LoginActivity.this, "Failed to log in , please try again", Toast.LENGTH_SHORT).show();

//                        Intent intent = new Intent(LoginActivity.this, CylinderActivity.class);
//                        startActivity(intent);
                    }

                }
            }
        });

    }


    public boolean validate() {
        boolean valid = false;

        //Get values from EditText fields
        String name = username.getText().toString();
        String Password = password.getText().toString();

        //Handling validation for Email field
        if (name.isEmpty()) {
            valid = false;
            username.setError("Please enter valid username!");
        } else {
            valid = true;
            username.setError(null);
        }

        //Handling validation for Password field
        if (Password.isEmpty()) {
            valid = false;
            password.setError("Please enter valid password!");
        } else {
            valid = true;
            username.setError(null);

        }


        return valid;
    }
}