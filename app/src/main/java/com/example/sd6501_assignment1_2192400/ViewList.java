package com.example.sd6501_assignment1_2192400;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ViewList extends AppCompatActivity {

    //Declare UI elements.
    Button homeButton;
    ListView list;
    ArrayList<String> titles = new ArrayList<String>();
    ArrayAdapter arrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Get XML values.
        setContentView(R.layout.view_list);
        homeButton = findViewById(R.id.homeButton);

        //Declare database.
        SQLiteDatabase db = openOrCreateDatabase("SliteDb", Context.MODE_PRIVATE,null);

        list = findViewById(R.id.list);
        final Cursor c = db.rawQuery("select * from records",null);
        int id = c.getColumnIndex("id");
        int name = c.getColumnIndex("name");
        int course = c.getColumnIndex("course");
        int fee = c.getColumnIndex("fee");
        titles.clear();

        //get layout and set adapter for list.
        arrayAdapter = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,titles);
        list.setAdapter(arrayAdapter);

        final  ArrayList<aquarium> stud = new ArrayList<aquarium>();

        if(c.moveToFirst())
        {
            do{
                aquarium stu = new aquarium();
                stu.id = c.getString(id);
                stu.name = c.getString(name);
                stu.course = c.getString(course);
                stu.fee = c.getString(fee);
                stud.add(stu);

                titles.add(c.getString(id) + " \t " + c.getString(name) + " \t "  + c.getString(course) + " \t "  + c.getString(fee) );

            } while(c.moveToNext());
            arrayAdapter.notifyDataSetChanged();
            list.invalidateViews();

        }

        //Button that takes user back to the add aquarium page.
        //Prevents endless loop between list view and edit page when only using the back button.
        homeButton.setOnClickListener(v -> startActivity(new Intent(ViewList.this, AquariumInformation.class)));

        //On click listener that moves user to an edit page for the list item they clicked.
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String aa = titles.get(position).toString();
                aquarium stu = stud.get(position);
                Intent i = new Intent(getApplicationContext(), EditListItem.class);
                i.putExtra("id",stu.id);
                i.putExtra("name",stu.name);
                i.putExtra("course",stu.course);
                i.putExtra("fee",stu.fee);
                startActivity(i);
            }
    });
}}
