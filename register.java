package com.example.myapplication;

import android.content.Intent;
import android.content.Context;
import android.os.Bundle;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class register extends AppCompatActivity {

    Button button1;
    EditText uname,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        button1=(Button)findViewById(R.id.reg_btn);

        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                Toast.makeText(getApplicationContext(), "save started",Toast.LENGTH_LONG).show();
                uname = (EditText)findViewById(R.id.reguname2);
                password = (EditText)findViewById(R.id.regpass2);
                String str_uname=uname.getText().toString();
                String str_pass=password.getText().toString();
                SQLiteDatabase db;
                db=openOrCreateDatabase("NumDB", Context.MODE_PRIVATE, null);
                //Toast.makeText(getApplicationContext(), "db created",Toast.LENGTH_LONG).show();

                db.execSQL("CREATE TABLE IF NOT EXISTS details(uname VARCHAR,password VARCHAR);");
                //Toast.makeText(getApplicationContext(), "table created",Toast.LENGTH_LONG).show();

                Cursor c=db.rawQuery("SELECT * FROM details", null);
                if(c.getCount()<2)
                {
                    db.execSQL("INSERT INTO details VALUES('"+str_uname+"','"+str_pass+"');");
                    Toast.makeText(getApplicationContext(), "Successfully Saved",Toast.LENGTH_SHORT).show();
                }
                else {

                    db.execSQL("INSERT INTO details VALUES('"+str_uname+"','"+str_pass+"');");
                    Toast.makeText(getApplicationContext(), "Maximun Numbers limited reached. Previous numbers are replaced.",Toast.LENGTH_SHORT).show();

                }

                Intent myIntent = new Intent(register.this, login.class);
                startActivity(myIntent);

                db.close();

            }


        });
    }
}
