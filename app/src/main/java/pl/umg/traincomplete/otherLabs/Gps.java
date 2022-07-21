package pl.umg.traincomplete.otherLabs;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Locale;

import pl.umg.traincomplete.R;

public class Gps extends AppCompatActivity {

    Location location;
    Location popLoc;
    double wide, length, distanceInM, fullDistance;
    int stepLength = 1;

    @SuppressLint("MissingPermission")
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps);

        TextView textView = findViewById(R.id.textView2);
        TextView textView2 = findViewById(R.id.textView3);

        ActivityResultLauncher<String[]> locationPermissionRequest =
                registerForActivityResult(new ActivityResultContracts
                                .RequestMultiplePermissions(), result -> {
                            Boolean fineLocationGranted = result.getOrDefault(
                                    Manifest.permission.ACCESS_FINE_LOCATION, false);
                            Boolean coarseLocationGranted = result.getOrDefault(
                                    Manifest.permission.ACCESS_COARSE_LOCATION, false);
                            if (fineLocationGranted != null && fineLocationGranted) {
                                // Precise location access granted.
                            } else if (coarseLocationGranted != null && coarseLocationGranted) {
                                // Only approximate location access granted.
                            } else {
                                textView.setText("No permission to GPS");
                            }
                        }
                );

        locationPermissionRequest.launch(new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
        });


        LocationManager locationManager = (LocationManager)
                getSystemService(Context.LOCATION_SERVICE);

        LocationListener listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location locationW) {
                location = locationW;
                if (popLoc == null) {
                    popLoc = locationW;
                }
                wide = locationW.getLatitude();
                length = locationW.getLongitude();
                distanceInM = locationW.distanceTo(popLoc);
                fullDistance += distanceInM;
                String odl;
                odl = "\nwidth " + String.format("%10.6f", wide) + "\nlength " +
                        String.format("%10.6f", length);
                odl += "\ndistance " +
                        String.format("%10.1f", distanceInM) + "\nfull distance" +
                        String.format("%10.1f", fullDistance) + "\nnumber of steps " +
                        String.format("%06d", (int) (fullDistance / stepLength));

                popLoc = locationW;

                Geocoder gcd = new Geocoder(getBaseContext(), Locale.getDefault());
                List<Address> addresses;
                try {
                    addresses = gcd.getFromLocation(locationW.getLatitude(), locationW.getLongitude(), 1);
                    if (addresses.size() > 0) {
                        System.out.println(addresses.get(0).getLocality());
                        String cityName = addresses.get(0).getLocality();
                        odl += "\nMiasto: " + cityName;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                textView.setText(odl);

            }
        };

        try {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10, listener);
        } catch (SecurityException se) {
            Log.v("no access", "security problems");
        }
    }
}