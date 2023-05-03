package com.example.myweatherbase.activities;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
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
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.myweatherbase.R;
import com.example.myweatherbase.activities.model.Ciudad;
import com.example.myweatherbase.activities.model.RepositorioCiudad;
import com.example.myweatherbase.activities.model.Root;
import com.example.myweatherbase.logic.Adaptador;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

public class SecondActivity extends AppCompatActivity {
    private Spinner spinnerciudad;
    private Button btnciudad;
    private ImageView imagenciudad;
    private Root root;
    private Adaptador adaptador;
    private Ciudad ciudad;
    private FusedLocationProviderClient fusedLocationClient;
    private  Double latitude;
    private  Double longitude;

    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_second);
        btnciudad = findViewById(R.id.buttonCiudad);
        imagenciudad = findViewById(R.id.imageViewCiudad);
        spinnerciudad = findViewById(R.id.spinnerCiudades);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);



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
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                i.putExtra("coord", ciudad);
                activityResultLauncher.launch(i);}
        });


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
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(location -> {
                    if (location != null) {
                        latitude = location.getLatitude();
                        longitude = location.getLongitude();
                        System.out.println("wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwdsadasdasdasdasasd"+latitude + "" + longitude);
                        //rellenar aqui con el set
                        RepositorioCiudad.getInstance().getAll().get(3).setPath("&lat="+latitude+"lon="+longitude);

                    }
                });

    }


}


/*
// Importar las siguientes clases
import android.Manifest;
        import android.content.Context;
        import android.content.pm.PackageManager;
        import android.location.Location;
        import android.location.LocationManager;
        import androidx.core.app.ActivityCompat;

// Obtener una instancia del LocationManager
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

// Verificar si se tienen los permisos necesarios
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

        // Obtener la última ubicación conocida
        Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        if (lastKnownLocation != null) {
        // Obtener la latitud y longitud
        double latitude = lastKnownLocation.getLatitude();
        double longitude = lastKnownLocation.getLongitude();
        }
        }
*/