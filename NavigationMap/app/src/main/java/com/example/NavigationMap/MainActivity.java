package com.example.NavigationMap;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static final int ERROR_DIALOG_REQUEST = 9001;
    Button Other,Favr;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Favr = (Button) findViewById(R.id.Favourite_Logb);
        Other = (Button) findViewById(R.id.logbac);

        if(isServicesOK()){
            init();
        }
        Other.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openActivityOther();}
        });
        Favr.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openActivityfav();}
        });
    }



    private void init(){
        Button btnMap = (Button) findViewById(R.id.btnMap);
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent =  new Intent(MainActivity.this, MapActivity.class);
                startActivity(intent);
            }
        });
    }

    public boolean isServicesOK() {
        Log.d(TAG, "isServicesOK: checking google services version");
        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(MainActivity.this);

        if (available == ConnectionResult.SUCCESS) {
            //everything is fine and the user can make map requests
            Log.d(TAG, "isServicesOK: Google Play Services is working");
            return true;
        } else if (GoogleApiAvailability.getInstance().isUserResolvableError(available)) {
            //an error has occured but can be resolved
            Log.d(TAG, "isServicesOK: an error occured but we can fix it");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(MainActivity.this, available, ERROR_DIALOG_REQUEST);
            dialog.show();

        }else{
            Toast.makeText(this, "We can't make map requests",Toast.LENGTH_LONG).show();
        }
        return false;
    }
    private void openActivityOther() {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }
    private void openActivityfav() {
        Intent intent = new Intent(this, FavouriteL.class);
        startActivity(intent);
    }
}
