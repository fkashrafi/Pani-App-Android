package com.example.fahadkhanashrafi.paniapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Handler;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class Splash extends AppCompatActivity {
    Button btn_guest,btn_login;
    Boolean isLocationOn = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Remove TitleBar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        btn_guest= (Button) findViewById(R.id.btnGuest);
        btn_login= (Button) findViewById(R.id.btnLogin);
        btn_guest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocationManager lm = (LocationManager)getApplication().getSystemService(Context.LOCATION_SERVICE);
                try
                {
                    isLocationOn = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
                }
                catch (Exception ex){
                    Toast.makeText(Splash.this, "Error: "+ex, Toast.LENGTH_SHORT).show();
                }
                if (isLocationOn){
                    startActivity(new Intent(Splash.this,Guest.class));
                }
                else{
                    AlertDialog.Builder alert =new AlertDialog.Builder(Splash.this);
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
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocationManager lm = (LocationManager)getApplication().getSystemService(Context.LOCATION_SERVICE);
                try
                {
                    isLocationOn = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
                }
                catch(Exception ex){

                }
                if (isLocationOn)
                {
                    startActivity(new Intent(Splash.this,Login.class));
                }
                else{
                    AlertDialog.Builder alert = new AlertDialog.Builder(Splash.this);
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
        });
        //Start Splash
      /*  new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(Splash.this,Login.class));
                finish();
            }
        },1500);*/

    }

}
