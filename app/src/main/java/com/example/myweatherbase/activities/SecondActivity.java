package com.example.myweatherbase.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myweatherbase.R;
import com.example.myweatherbase.activities.model.RepositorioCiudad;
import com.example.myweatherbase.activities.model.Root;
import com.example.myweatherbase.logic.Ciudades;

import java.util.Arrays;
import java.util.List;

public class SecondActivity extends AppCompatActivity {
    private Spinner spinnerciudad;
    private Button btnciudad;
    private ImageView imagenciudad;
    private Root root;
    private String[] todas = {"VLC", "MADRID", "BCN"};
    private RepositorioCiudad repositorioCiudad = new RepositorioCiudad();


    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_second);
        btnciudad = findViewById(R.id.buttonCiudad);
        imagenciudad = findViewById(R.id.imageViewImg);
        spinnerciudad = findViewById(R.id.spinnerCiudades);


        btnciudad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,  todas);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerciudad.setAdapter(adapter);

        spinnerciudad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            String a;
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (adapterView.getSelectedItem().toString().equals("VLC"))
                     a ="&lat=-0.3773900&lon=39.4697500";
                System.out.println(adapterView.getSelectedItem().toString()  + a  + "dsasadasdsdasdsadasdsadasdasdasd");

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}
