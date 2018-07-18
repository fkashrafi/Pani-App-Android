package com.example.fahadkhanashrafi.paniapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class Guest extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.fahadkhanashrafi.paniapp";
    public static final String EXTRA_MESSAGE1 = "com.example.fahadkhanashrafi.paniapp";
    Button btn20L,btn12L,btn6L;
    boolean isLocationOn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest);
        btn20L = (Button) findViewById(R.id.btn20L);
        btn12L = (Button) findViewById(R.id.btn12L);
        btn6L = (Button) findViewById(R.id.btn6L);

        /*20 Liter can Order On click*/
        btn20L.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Guest.this, GuestBooking.class);
                intent.putExtra("key1","20 Liter");
                intent.putExtra("key2","200");
                startActivity(intent);
            }
        });
        /*12 Liter can Order On click*/
        btn12L.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Guest.this, GuestBooking.class);
                intent.putExtra("key1","12 Liter");
                intent.putExtra("key2","150");
                startActivity(intent);
            }
        });
        /*6 Liter can Order On click*/
        btn6L.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Guest.this, GuestBooking.class);
                intent.putExtra("key1","6 Liter");
                intent.putExtra("key2","75");
                startActivity(intent);
            }
        });
    }
    private void checkLocation(){
        LocationManager lm = (LocationManager)getApplication().getSystemService(Context.LOCATION_SERVICE);
        try
        {
            isLocationOn = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        }
        catch(Exception ex){

        }
        if(isLocationOn)
        {
            if (btn6L.findViewById(R.id.btn6L).equals(btn6L.findViewById(R.id.btn6L)))
            {
                btn6L.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Guest.this, GuestBooking.class);
                        intent.putExtra("key1","6 Liter");
                        intent.putExtra("key2","75");
                        startActivity(intent);
                    }
                });

            }
            else if (btn12L.findViewById(R.id.btn12L).equals(btn12L.findViewById(R.id.btn12L)))
            {
                btn12L.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Guest.this, GuestBooking.class);
                        intent.putExtra("key1","12 Liter");
                        intent.putExtra("key2","150");
                        startActivity(intent);
                    }
                });

            }
            else if (btn20L.findViewById(R.id.btn20L).equals(btn20L.findViewById(R.id.btn20L)))
            {
                btn20L.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Guest.this, GuestBooking.class);
                        intent.putExtra("key1","20 Liter");
                        intent.putExtra("key2","200");
                        startActivity(intent);
                    }
                });

            }

        }
        else

        {
            AlertDialog.Builder alert = new AlertDialog.Builder(getApplication());
            alert.setTitle("Location service GPS is off");
            alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                }
            });
            alert.show();
        }


    }
}
