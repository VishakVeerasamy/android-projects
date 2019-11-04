package com.example.myapplication;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.database.Cursor;
import android.telephony.SmsManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class homescreen extends AppCompatActivity {
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS =0 ;
    Button button3, button4;
    Cursor c;
    Location l;
    String msg;
    String mob;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);
        button3 = findViewById(R.id.home_sett);
        button4 = findViewById(R.id.home_send);
        ActivityCompat.requestPermissions(homescreen.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 143);
        // Capture button clicks
        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start login.class
                Intent myIntent3 = new Intent(homescreen.this, settings.class);
                startActivity(myIntent3);
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                gpstrack g;
                g = new gpstrack(getApplicationContext());
                l = g.getLocation();
                SQLiteDatabase db;
                db = openOrCreateDatabase("NumDB", Context.MODE_PRIVATE, null);
                c = db.rawQuery("SELECT * FROM recipients", null);
                int n=c.getCount();
                if (n>0) {
                    double latitude = l.getLatitude();
                    double longitude = l.getLongitude();
                    LocationAddress locationAddress = new LocationAddress();
                    locationAddress.getAddressFromLocation(latitude, longitude, getApplicationContext(), new GeocoderHandler());
                }
                else
                {
                    Toast.makeText(homescreen.this,"ADD the Contacts!!!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

   public void del(View v)
   {
      SQLiteDatabase db;
      db = openOrCreateDatabase("NumDB", Context.MODE_PRIVATE, null);
      db.delete("details",null,null);
      db.delete("recipients",null,null);
      db.close();
      Toast.makeText(getApplicationContext(),"Delete Successful!!!",Toast.LENGTH_LONG).show();
      Intent h=new Intent(Intent.ACTION_MAIN);
      h.addCategory(Intent.CATEGORY_HOME);
      h.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
      startActivity(h);
   }
   private class GeocoderHandler extends Handler {
        @Override
        public void handleMessage(Message message) {
            String cur_loc;
            switch (message.what) {
                case 1:
                    Bundle bundle = message.getData();
                    cur_loc = bundle.getString("address");
                    break;
                default:
                    cur_loc = null;
            }

            EditText e1 = findViewById(R.id.e);
            String s = e1.getText().toString()+" @";
            SQLiteDatabase db;
            db = openOrCreateDatabase("NumDB", Context.MODE_PRIVATE, null);
            c = db.rawQuery("SELECT * FROM recipients", null);
            if (c.getCount() > 0) {
                int a=0;
                while (c.moveToNext()) {
                    msg = s + cur_loc;
                    mob = c.getString(1);
                    try{
                        SmsManager smgr = SmsManager.getDefault();
                        smgr.sendTextMessage(mob,null,msg,null,null);
                    }
                    catch (Exception e){
                        Toast.makeText(homescreen.this, "SMS Failed to Send, Please try again", Toast.LENGTH_SHORT).show();
                    }
                }
                if(a==0) {
                    Toast.makeText(homescreen.this, "Message sent", Toast.LENGTH_SHORT).show();
                }
            }
            else {
                Toast.makeText(homescreen.this, "Message Failed", Toast.LENGTH_SHORT).show();
            }
            db.close();
        }

    }

}
