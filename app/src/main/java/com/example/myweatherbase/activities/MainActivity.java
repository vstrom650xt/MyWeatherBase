package com.example.myweatherbase.activities;

import android.graphics.Path;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myweatherbase.API.Connector;
import com.example.myweatherbase.R;
import com.example.myweatherbase.activities.model.Root;
import com.example.myweatherbase.base.BaseActivity;
import com.example.myweatherbase.base.CallInterface;
import com.example.myweatherbase.base.ImageDownloader;
import com.example.myweatherbase.base.Parameters;
import com.example.myweatherbase.logic.Adaptador;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends BaseActivity implements CallInterface {


    private Root root;
    private RecyclerView recyclerView;
    private TextView textCiudad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Mostramos la barra de progreso y ejecutamos la llamada a la API
        showProgress();
        executeCall(this);
    }

    // Realizamos la llamada y recogemos los datos en un objeto Root
    @Override
    public void doInBackground() {
        root = Connector.getConector().get(Root.class,"&lat=59.5862518&lon=-0.5411163");
        //root.list.get(0).wind;
    }

    // Una vez ya se ha realizado la llamada, ocultamos la barra de progreso y presentamos los datos
    @Override
    public void doInUI() {
        hideProgress();

        //recycleview con  linear layout manager
        recyclerView = findViewById(R.id.recycler);
        textCiudad = findViewById(R.id.textoCiudad);

        textCiudad.setText(root.city.name);

        Adaptador adaptador = new Adaptador(this,root);
        recyclerView.setAdapter(adaptador);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }
}