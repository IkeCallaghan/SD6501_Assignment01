package com.example.sd6501_assignment1_2192400;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditListItem extends AppCompatActivity {
    //Declare UI elements.
    EditText editText, editText1, editText2, editText3;
    Button button, button1, button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_list_item);

        //Refer to XML file, define EditListItem texts.
        editText = findViewById(R.id.name);
        editText1 = findViewById(R.id.type);
        editText2 = findViewById(R.id.volume);
        editText3 = findViewById(R.id.id);

        //Refer to XML file, define buttons.
        button = findViewById(R.id.button1);
        button1 = findViewById(R.id.button2);
        button2 = findViewById(R.id.button3);

        Intent i = getIntent();

        String t1 = i.getStringExtra("id").toString();
        String t2 = i.getStringExtra("name").toString();
        String t3 = i.getStringExtra("course").toString();
        String t4 = i.getStringExtra("fee").toString();

        //Set text input.
        editText3.setText(t1);
        editText.setText(t2);
        editText1.setText(t3);
        editText2.setText(t4);

        //Set onclicklistener for button that deletes item.
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Delete();
            }
        });

        //Set onClickListener for button that opens the view list class
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), ViewList.class);
                startActivity(i);

            }
        });

        //set onclicklistener for button that edits item.
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Edit();
            }
        });

    }

    //Function to Delete added item (row) from database.
    public void Delete()
    {
        try
        {
            String id = editText3.getText().toString();

            SQLiteDatabase db = openOrCreateDatabase("SliteDb",Context.MODE_PRIVATE,null);


            String sql = "delete from records where id = ?";
            SQLiteStatement statement = db.compileStatement(sql);

            statement.bindString(1,id);
            statement.execute();
            Toast.makeText(this,"Aquarium Deleted",Toast.LENGTH_LONG).show();


            editText.setText("");
            editText1.setText("");
            editText2.setText("");
            editText.requestFocus();


        }
        //Display fail message if action deletion from database fails.
        catch (Exception ex)
        {
            Toast.makeText(this,"Fail",Toast.LENGTH_LONG).show();
        }
    }

    //Function to update information within item (row) of database.
    public void Edit()
    {
        try
        {
            //Get user input, convert to string.
            String name = editText.getText().toString();
            String course = editText1.getText().toString();
            String fee = editText2.getText().toString();
            String id = editText3.getText().toString();

            //Open DB, create if doesn't exist.
            SQLiteDatabase db = openOrCreateDatabase("SliteDb", Context.MODE_PRIVATE,null);


            String sql = "update records set name = ?,course=?,fee=? where id= ?";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1,name);
            statement.bindString(2,course);
            statement.bindString(3,fee);
            statement.bindString(4,id);
            statement.execute();
            Toast.makeText(this,"Aquarium Updated",Toast.LENGTH_LONG).show();

            editText.setText("");
            editText1.setText("");
            editText2.setText("");
            editText.requestFocus();
        }

        //Display fail if function to EditListItem (Update row), fails.
        catch (Exception ex)
        {
            Toast.makeText(this,"Fail", Toast.LENGTH_LONG).show();
        }

    }

}