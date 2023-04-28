package com.example.myweatherbase.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myweatherbase.R;
import com.example.myweatherbase.activities.model.Root;

public class SecondActivity extends AppCompatActivity {
    private Spinner spinnerciudad;
    private Button btnciudad;
    private ImageView imagenciudad;
    private Root root;


    public void  onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_second);
        btnciudad = findViewById(R.id.buttonCiudad);
        imagenciudad = findViewById(R.id.imageViewImg);
        spinnerciudad = findViewById(R.id.spinnerCiudades);



        btnciudad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });



    }
}
