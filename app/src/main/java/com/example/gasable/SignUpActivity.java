package com.example.gasable;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {
    EditText fullname;
    EditText username;
    EditText email;
    EditText password;
    EditText mobilenumber;
    Button SignUp ;
    Button Cancel;
    DatabaseHelper sqliteHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        fullname = findViewById(R.id.editText_fullname_SignUp);
        username = findViewById(R.id.EditText_UserName_SignUp);
        email = findViewById(R.id.editText_Password_SignUp);
        password = findViewById(R.id.editText_Password_SignUp);
        mobilenumber = findViewById(R.id.editText_Phone_SignUp);
        SignUp = findViewById(R.id.btn_SingUp);
        Cancel = findViewById(R.id.btn_cancel);
        sqliteHelper = new DatabaseHelper(this);



        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate())
                {
                    String UserName = username.getText().toString();
                    String Email = email.getText().toString();
                    String Password = password.getText().toString();
                    String name = fullname.getText().toString();
                    String phoneNumber = mobilenumber.getText().toString();
                    if (!sqliteHelper.isExists(UserName)) {
                        //Email does not exist now add new user to database
                        sqliteHelper.addUser(new User(null, UserName, name, Email,Password,phoneNumber));
                        Toast.makeText(SignUpActivity.this, "User created successfully! Please Login \"", Toast.LENGTH_SHORT).show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                finish();
                            }
                        }, Toast.LENGTH_LONG);
                    }else {

                        //Email exists with email input provided so show error user already exist
                        Toast.makeText(SignUpActivity.this, "User already exists with same email", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });



    }


    public boolean validate()
    {
        boolean valid = false;

        String UserName = username.getText().toString();
        String Email = email.getText().toString();
        String Password = password.getText().toString();
        String name = fullname.getText().toString();
        String phoneNumber = mobilenumber.getText().toString();

        //Handling validation for UserName field
        if (UserName.isEmpty()) {
            valid = false;
            fullname.setError("Please enter valid username!");
        } else {
            if (UserName.length() > 5) {
                valid = true;
                fullname.setError(null);
            } else {
                valid = false;
                fullname.setError("Username is to short!");
            }
        }

        //Handling validation for Email field
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            valid = false;
            email.setError("Please enter valid email!");
        } else {
            valid = true;
            email.setError(null);
        }

        //Handling validation for Password field
        if (Password.isEmpty()) {
            valid = false;
            password.setError("Please enter valid password!");
        } else {
            if (Password.length() > 5) {
                valid = true;
                password.setError(null);
            } else {
                valid = false;
                password.setError("Password is to short!");
            }
        }

        if (phoneNumber.isEmpty()) {
            valid = false;
            password.setError("Please enter valid phonenumber!");
        } else {
           valid = true;
        }

        if (name.isEmpty()) {
            valid = false;
            password.setError("Please enter valid name!");
        } else {
            valid = true;
        }



        return valid;
    }

}