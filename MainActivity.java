package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button button1;
    Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1=(Button)findViewById(R.id.login_btn);
        button2=(Button)findViewById(R.id.regbutton);

        // Capture button clicks
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start login.class
                Intent myIntent = new Intent(MainActivity.this,login.class);
                startActivity(myIntent);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start login.class
                Intent myIntent2 = new Intent(MainActivity.this,
                        register.class);
                startActivity(myIntent2);
            }
        });
    }
}
