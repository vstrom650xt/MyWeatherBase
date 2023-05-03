package com.example.myweatherbase.activities.model;


import com.example.myweatherbase.R;

import java.util.ArrayList;
import java.util.List;

public class RepositorioCiudad {
    private ArrayList<Ciudad> ciudades;
    private  static  RepositorioCiudad instance;

    public RepositorioCiudad() {
        this.ciudades = new ArrayList<>();
        addCiudades();
    }



    public List<Ciudad> addCiudades(){

        ciudades.add(new Ciudad("VLC","&lat=-0.3773900&lon=39.4697500", R.mipmap.vlc_foreground));
        ciudades.add(new Ciudad("BCN","&lat=41.38879&lon=2.15899",R.mipmap.ic_launcherbcn));
        ciudades.add(new Ciudad("MDD","&lat=41.38879&lon=2.15899",R.mipmap.ic_launchermdd_foreground));

        return ciudades;
    }
    public static RepositorioCiudad getInstance(){
        if (instance == null)
            instance = new RepositorioCiudad();
        return instance;

    }
    public List<Ciudad> getAll(){
        return  new ArrayList<>(ciudades);

    }
    @Override
    public String toString() {
        return ciudades+"";
    }
}
