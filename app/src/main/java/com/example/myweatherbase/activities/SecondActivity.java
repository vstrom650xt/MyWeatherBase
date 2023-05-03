package com.example.myweatherbase.activities;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.myweatherbase.R;
import com.example.myweatherbase.activities.model.Ciudad;
import com.example.myweatherbase.activities.model.RepositorioCiudad;
import com.example.myweatherbase.activities.model.Root;
import com.example.myweatherbase.logic.Adaptador;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class SecondActivity extends AppCompatActivity implements LocationListener {
    private Spinner spinnerciudad;
    private Button btnciudad;
    private ImageView imagenciudad;
    private Root root;
    private Adaptador adaptador;
    private Ciudad ciudad;
    private FusedLocationProviderClient fusedLocationClient;
    private Double latitude;
    private Double longitude;
    private static final int REQUEST_LOCATION_PERMISSION = 1;
    private LocationManager locationManager;
    private LocationListener locationListener;

    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_second);
        btnciudad = findViewById(R.id.buttonCiudad);
        imagenciudad = findViewById(R.id.imageViewCiudad);
        spinnerciudad = findViewById(R.id.spinnerCiudades);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        // Solicitar permiso de ubicación
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_LOCATION_PERMISSION);
        } else {
            // El permiso ya ha sido otorgado
            obtenerUbicacionActual();
        }



        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {

        });


        ArrayAdapter<Ciudad> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,
                RepositorioCiudad.getInstance().getAll());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerciudad.setAdapter(adapter);

        spinnerciudad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                imagenciudad.setImageResource(RepositorioCiudad.getInstance().getAll().get(i).getImg());
                ciudad = RepositorioCiudad.getInstance().getAll().get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnciudad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RepositorioCiudad.getInstance().getAll().get(3).setPath("&lat="+latitude.toString()+"&lon="+longitude.toString());
                RepositorioCiudad.getInstance().getAll().get(3).getPath();
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                i.putExtra("coord", ciudad);
                activityResultLauncher.launch(i);
            }
        });


    }


    @Override
    public void onLocationChanged(@NonNull Location location) {

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_LOCATION_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permiso otorgado
                obtenerUbicacionActual();
            } else {
                // Permiso denegado
                Toast.makeText(this, "Permiso de ubicación denegado", Toast.LENGTH_SHORT).show();
            }
        }
    }


    private void obtenerUbicacionActual() {
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                latitude = location.getLatitude();
               longitude = location.getLongitude();

                // Detener la escucha de actualizaciones de ubicación después de obtener la primera ubicación
                locationManager.removeUpdates(locationListener);
            }
        };

        // Solicitar actualizaciones de ubicación
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
    }

}


