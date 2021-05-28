package com.example.sd6501_assignment1_2192400;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AquariumInformation extends AppCompatActivity {

    //Declare UI elements.
    EditText editText, editText02, editText03;
    Button button, button02;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aquarium_information);

        editText = findViewById(R.id.name);
        editText02 = findViewById(R.id.type);
        editText03 = findViewById(R.id.volume);

        button = findViewById(R.id.button1);
        button02 = findViewById(R.id.button2);

        button02.setOnClickListener(v -> {
            Intent i = new Intent(getApplicationContext(), ViewList.class);
            startActivity(i);
        });
        button.setOnClickListener(v -> insert());
    }
    //Function to prevent user from logging out on accident.
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("Do you want to Exit?");
        builder.setPositiveButton("Yes", (dialog, which) -> {
            //if user pressed "yes", then he is allowed to exit from application
            startActivity(new Intent(AquariumInformation.this, HomePage.class));
        });
        builder.setNegativeButton("No", (dialog, which) -> {
            //if user select "No", just cancel this dialog and continue with app
            dialog.cancel();
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
    public void insert()
    {
        try
        {
            String name = editText.getText().toString();
            String course = editText02.getText().toString();
            String fee = editText03.getText().toString();

            SQLiteDatabase db = openOrCreateDatabase("SliteDb", Context.MODE_PRIVATE,null);
            db.execSQL("CREATE TABLE IF NOT EXISTS records(id INTEGER PRIMARY KEY AUTOINCREMENT,name VARCHAR,course VARCHAR,fee VARCHAR)");

            String sql = "insert into records(name,course,fee)values(?,?,?)";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1,name);
            statement.bindString(2,course);
            statement.bindString(3,fee);
            statement.execute();
            Toast.makeText(this,"Record added",Toast.LENGTH_LONG).show();

            editText.setText("");
            editText02.setText("");
            editText03.setText("");
            editText.requestFocus();
        }
        catch (Exception ex)
        {
            Toast.makeText(this,"Record Fail",Toast.LENGTH_LONG).show();
        }

    }
}
