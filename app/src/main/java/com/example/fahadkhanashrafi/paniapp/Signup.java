package com.example.fahadkhanashrafi.paniapp;

import android.Manifest;
import android.app.AlertDialog;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Signup extends AppCompatActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    EditText name,contact,pass,cpass;
    Button Reg;
    private String Name,Phone,Pass,Cpass;
    Double Lat,Log;
    private GoogleMap mMap;
    private GoogleApiClient client;
    private LocationRequest locationRequest;
    public static final int REQUEST_LOCATION_CODE = 99;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_signup);
         /*Start MAP on Creat*/
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            checkLocaationPermission();
        }
       /*End MAP on Creat*/
        /*Wegits*/
        name = findViewById(R.id.et_uname);
        contact = findViewById(R.id.et_phone);
        pass = findViewById(R.id.et_pass);
        cpass = findViewById(R.id.et_Rpass);
        Reg = findViewById(R.id.btnReg);

        /*OnClick btn Registration*/
        Reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Reg();

                }
        });

    }
/*START MAP WORK*/
    @Override
    public void onMapReady(GoogleMap googleMap) {
    if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
        mMap = googleMap;

        buildGoogleApiClient();
        mMap.setMyLocationEnabled(true);
        // TODO: Consider calling
        //    ActivityCompat#requestPermissions
        // here to request the missing permissions, and then overriding
        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
        //                                          int[] grantResults)
        // to handle the case where the user grants the permission. See the documentation
        // for ActivityCompat#requestPermissions for more details.

    }
}
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        locationRequest = new LocationRequest();
        locationRequest.setInterval(1000);
        locationRequest.setFastestInterval(1000);
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(client, locationRequest, this);
        }
    }
    @Override
    public void onConnectionSuspended(int i) {

    }
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
    @Override
    public void onLocationChanged(Location location) {
        mMap.clear();
        MarkerOptions mp = new MarkerOptions();
        mp.position(new LatLng(location.getLatitude(), location.getLongitude()));
        mp.title("my position");
        mp.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
        mMap.addMarker(mp);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                new LatLng(location.getLatitude(), location.getLongitude()), 16));
        Lat =location.getLatitude();
        Log =location.getLongitude();
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {
            case REQUEST_LOCATION_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    //premission is granted
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        if (client == null) {
                            buildGoogleApiClient();
                        }
                        mMap.setMyLocationEnabled(true);
                    }
                } else
                //permission is denied
                {
                    Toast.makeText(this, "permission is denied!", Toast.LENGTH_SHORT).show();
                }
                return;
        }

    }
    protected synchronized void buildGoogleApiClient() {
        client = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        client.connect();

    }
    public boolean checkLocaationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION_CODE);
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION_CODE);
            }
            return false;
        } else
            return true;
    }
/*End MAP WORK*/
/**/

/*get Edittext Text*/
    private void Reg()
    {
                            /*Get Text*/
        Name = name.getText().toString().trim();
        Phone = contact.getText().toString().trim();
        Pass = pass.getText().toString().trim();
        Cpass = cpass.getText().toString().trim();
        contact.setFilters(new InputFilter[]{new InputFilter.LengthFilter(5)});
        cpass.setFilters(new InputFilter[]{new InputFilter.LengthFilter(11)});
        pass.setFilters(new InputFilter[]{new InputFilter.LengthFilter(5)});

        if (TextUtils.isEmpty(Name)){
            name.setError("Please Enter Name");
            return;
        }
        else if (TextUtils.isEmpty(Phone)){
            contact.setError("Please Enter Contact");
            return;
        }
        else if (Phone.length()!= 11){
            contact.setError("Invalid Phone Number");
            return;
        }

        else if (TextUtils.isEmpty(Pass)){
            pass.setError("Please Enter Password\nmim character 5");
            return;
        }
        else if (!Cpass.equals(Pass)){
            pass.setError("Please Enter Same Password");
            cpass.setError("Please Enter Same Password");
            return;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(Signup.this);
        builder.setTitle("Detail Registration");
        builder.setMessage("Name:" + Name + "\nPhone:" + Phone + "\nPassword:" + Pass + "\nLat:" + Lat.toString() + "\nLong:" + Log.toString());
        AlertDialog alertDialog = builder.create();
        alertDialog.show();



    }


}
