package com.example.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class settings extends AppCompatActivity {

    EditText name,number;
    String str_name;
    String str_num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    public void fun(View v)
    {
        name = (EditText)findViewById(R.id.name2);
        number = (EditText)findViewById(R.id.num2);
        str_name=name.getText().toString();
        str_num=number.getText().toString();
        SQLiteDatabase db;
        db=openOrCreateDatabase("NumDB", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS recipients(name VARCHAR,number VARCHAR);");
        db.execSQL("INSERT INTO recipients VALUES('"+str_name+"','"+str_num+"');");
        Toast.makeText(getApplicationContext(),"Contact Saved",Toast.LENGTH_LONG).show();
        db.close();
    }
}