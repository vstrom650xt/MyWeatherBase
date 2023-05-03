package com.example.myweatherbase.activities;

import android.app.Activity;
import android.content.Intent;
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

import com.example.myweatherbase.R;
import com.example.myweatherbase.activities.model.Ciudad;
import com.example.myweatherbase.activities.model.RepositorioCiudad;
import com.example.myweatherbase.activities.model.Root;
import com.example.myweatherbase.logic.Adaptador;

public class SecondActivity extends AppCompatActivity {
    private Spinner spinnerciudad;
    private Button btnciudad;
    private ImageView imagenciudad;
    private Root root;
    private Adaptador    adaptador;
     private Ciudad ciudad ;

    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_second);
        btnciudad = findViewById(R.id.buttonCiudad);
        imagenciudad = findViewById(R.id.imageViewCiudad);
        spinnerciudad = findViewById(R.id.spinnerCiudades);

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

    }
}
