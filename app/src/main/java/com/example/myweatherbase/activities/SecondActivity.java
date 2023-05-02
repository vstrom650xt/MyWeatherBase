package com.example.myweatherbase.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myweatherbase.R;
import com.example.myweatherbase.activities.model.Ciudad;
import com.example.myweatherbase.activities.model.RepositorioCiudad;
import com.example.myweatherbase.activities.model.Root;

public class SecondActivity extends AppCompatActivity {
    private Spinner spinnerciudad;
    private Button btnciudad;
    private ImageView imagenciudad;
    private Root root;
    private RepositorioCiudad repositorioCiudad ;

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



        ArrayAdapter<Ciudad> adapter = new ArrayAdapter<Ciudad>(this,
                android.R.layout.simple_spinner_item,RepositorioCiudad.getInstance().getAll());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerciudad.setAdapter(adapter);

        spinnerciudad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            String a;
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i==0){
                    imagenciudad.setImageResource(R.);
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


}
