package com.example.sd6501_assignment1_2192400;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//This class takes the user-input for UserName and Password and saves it to be used in the log-in
// page.
//User input is saved so it can be re-used after re-opening the application.
//As of now, there can only be one Username and Password saved in the system, I will change it in
// the future so the application can save various users with various user information.

public class Registration extends AppCompatActivity {

    //Declare variables
    private EditText registerName;
    private EditText registerPassword;
    private Button btnRegister;

    //Create UserInfo object, allowing for user information to be defined and edited.
    public static UserInfo userinfo;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor sharedPreferencesEditor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        //Implementing XML objects to class.
        registerName = findViewById(R.id.registerNameEnter);
        registerPassword = findViewById(R.id.registerPasswordEnter);
        btnRegister = findViewById(R.id.btnRegister);

        sharedPreferences = getApplicationContext().getSharedPreferences( "UserInfoDB", MODE_PRIVATE);
        sharedPreferencesEditor = sharedPreferences.edit();

        //On click function for register Button.
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Get user information from input fields.
                String registeredName = registerName.getText().toString();
                String registeredPassword = registerPassword.getText().toString();

                //Ensure input is valid.
                if(validate(registeredName, registeredPassword))
                {
                    //Add user inputted details to offline database.
                    userinfo = new UserInfo(registeredName, registeredPassword);

                    //Store User details.
                    //Using shared preferences means the inputted data will be stored in the system,
                    //allowing the user to re-use the same login even after closing the app.
                    sharedPreferencesEditor.putString("Username", registeredName);
                    sharedPreferencesEditor.putString("Password", registeredPassword);
                    sharedPreferencesEditor.apply();

                    //Push user registration notification.
                    Toast.makeText(Registration.this, "User has been registered.", Toast.LENGTH_SHORT).show();

                    //Move user to HomePage.
                    startActivity(new Intent(Registration.this, MainActivity.class));
                }
            }
        });
    }

    //Function that checks whether the username field is empty and/or the password input is less
    // than or 8 characters long.
    //Possibly remove.
    boolean validate(String name, String password)
    {
        //If Username field is empty or password length is the same or less than 8.
        //Push pop-up notification prompting user to input valid information.
        if(name.isEmpty() || (password.length() < 8))
        {
            Toast.makeText(this, "Please enter valid information! Password must be 8 characters or more.", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}