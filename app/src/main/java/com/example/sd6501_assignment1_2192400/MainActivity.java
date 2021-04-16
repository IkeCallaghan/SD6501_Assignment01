package com.example.sd6501_assignment1_2192400;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    //Get user information object from the UserInfo class.
    public static UserInfo UserInfo;
    SharedPreferences sharedPreferences;

    //Boolean value (True or False), used for checking validity of user input (Log-in field).
    boolean isValid = false;

    //Declare UI elements.
    private EditText Name;
    private EditText Password;
    private TextView AttemptsInfo;
    private Button Login;
    private Button RegisterMain;

    //Counter for log-in attempts, can be changed from here.
    private int counter = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Implementing XML elements to class
        Name = findViewById(R.id.nameEnter);
        Password = findViewById(R.id.passwordEnter);
        AttemptsInfo = findViewById(R.id.attempts);
        Login = findViewById(R.id.btnLogin);
        RegisterMain = findViewById(R.id.btnRegisterMain);

        sharedPreferences = getApplicationContext().getSharedPreferences("UserInfoDB", MODE_PRIVATE);

        //If User information exist, retrieve user information.
        if (sharedPreferences != null) {

            String savedUsername = sharedPreferences.getString("Username", "");
            String savedPassword = sharedPreferences.getString("Password", "");

            Registration.userinfo = new UserInfo(savedUsername, savedPassword);
        }

        RegisterMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Registration.class));
            }
        });

        //On click function for log-in button
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Retrieve user input
                String userName = Name.getText().toString();
                String userPassword = Password.getText().toString();

                //Check if input fields are empty
                if (userName.isEmpty() || userPassword.isEmpty()) {

                    //Display pop-up message prompting user to re-enter valid input.
                    Toast.makeText(MainActivity.this, "Please enter name and password!", Toast.LENGTH_LONG).show();

                } else {

                    //Validate user input
                    isValid = validate(userName, userPassword);

                    //If input is not valid, Decrease attempt counter.
                    if (!isValid) {

                        //Decrease counter
                        counter--;

                        //Update attempt count message on log-in page.
                        AttemptsInfo.setText("You have " + counter + " attempts remaining!");

                        //If counter reaches 0, disable log-in button.
                        //This can be overridden by the user simply clicking the register button or re-opening the application,
                        //I want to change this in the future so there is a log-in timer for too many incorrect attempts.
                        if (counter == 0) {
                            Login.setEnabled(false);
                            Toast.makeText(MainActivity.this, "You have used all your attempts try again later!", Toast.LENGTH_LONG).show();
                        }
                        //If input is wrong, display pop-up message informing the user and prompting them to re-enter.
                        else {
                            Toast.makeText(MainActivity.this, "Incorrect information entered, please try again!", Toast.LENGTH_LONG).show();
                        }
                    }
                    //else (if valid).
                    else {

                        //Accept user input, move to HomePage activity.
                        //Print personalized Welcome back message, including the users name.
                        startActivity(new Intent(MainActivity.this, HomePage.class));
                        Toast.makeText(MainActivity.this, "Welcome back " + userName + "!", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }

    //Validate user input from the UserInfo class.
    private boolean validate(String userName, String userPassword) {
        if (Registration.userinfo != null) {
            return userName.equals(Registration.userinfo.getUsername()) && userPassword.equals(Registration.userinfo.getUserPassword());
        }
        return false;
    }
}