package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.database.Cursor;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class login extends AppCompatActivity {

    Button button1;
    EditText uname;
    EditText password;
    Cursor c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        button1=(Button)findViewById(R.id.lgn_btn);
        uname=(EditText) findViewById(R.id.uname2);
        password=(EditText)findViewById(R.id.pass2);

        // Capture button clicks
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                int a=0;
                SQLiteDatabase db;
                db=openOrCreateDatabase("NumDB", Context.MODE_PRIVATE, null);
                c=db.rawQuery("SELECT * FROM details", null);
                String str_uname=uname.getText().toString();
                String str_pass=password.getText().toString();
                while(c.moveToNext())
                {
                    if (str_uname.equals(c.getString(0)))
                    {
                        if (str_pass.equals(c.getString(1)))
                        {   a=1;
                            Toast.makeText(getApplicationContext(), "Login Suceesfull",Toast.LENGTH_SHORT).show();
                            db.close();
                            Intent myIntent = new Intent(login.this, homescreen.class);
                            startActivity(myIntent);
                        }
                    }
                }
                if(a!=1)
                Toast.makeText(getApplicationContext(), "username/password incorrect",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
