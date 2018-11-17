package com.example.loggerdoc;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class ActivityAddRecord extends AppCompatActivity {
    private FusedLocationProviderClient mFusedLocationClient;
    private int MY_PERMISSION_ACCESS_COARSE_LOCATION = 1;
    private EditText recordTitleText;
    private TextView geoLocationText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
    }

    @Override
    protected void onResume(){
        super.onResume();
        recordTitleText = (EditText) findViewById(R.id.record_title_text);
        geoLocationText = (TextView) findViewById(R.id.Geolocation_text);
    }

    public void createRecord (View v){

        //Check if the Record Title is empty
        boolean emptyTitle = checkEmptyString(recordTitleText.getText().toString());

        if (emptyTitle){
            showAlertDialog("Error: Empty Title","Please fill in the title field");
        }

        else{
            Record newRecord = new Record (recordTitleText.getText().toString());

            if (geoLocationText != null){
                //add the geolocation to the newRecord
            }
        }
    }


    //To be called when Add Geo-Location button is pressed
    public void addGeoLocation (View v){

        //Check if we have permission to access Location Services
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            ActivityCompat.requestPermissions( this, new String[] {  android.Manifest.permission.ACCESS_COARSE_LOCATION  },
                    MY_PERMISSION_ACCESS_COARSE_LOCATION );
        }

        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            // Logic to handle location object
                            geoLocationText.setText("Latitude: " + location.getLatitude() + ", Longitude: " + location.getLongitude());
                        }
                    }
                });
    }

    public void addBodyLocation (View v){

    }

    public void addPhoto (View v){

    }

    public boolean checkEmptyString(String string){
        if (string.length() == 0){
            return true;
        }
        return false;
    }

    private void showAlertDialog( String title, String message){
        Bundle messageArgs = new Bundle();
        messageArgs.putString(DialogProblem.TITLE_ID, title);
        messageArgs.putString(DialogProblem.MESSAGE_ID, message);

        DialogProblem dialog = new DialogProblem();
        dialog.setArguments(messageArgs);
        dialog.show(getSupportFragmentManager(), "error_dialog");
    }

}
