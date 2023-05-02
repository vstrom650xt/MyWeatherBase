package com.example.myweatherbase.activities.model;

import java.util.ArrayList;
import java.util.List;

public class RepositorioCiudad {
    private List<Ciudad> ciudades;

    public RepositorioCiudad() {
        this.ciudades = new ArrayList<>();
        addCiudades();
    }



    public List<Ciudad> addCiudades(){

        ciudades.add(new Ciudad("VLC","&lat=-0.3773900&lon=39.4697500"));
        ciudades.add(new Ciudad("BCN","&lat=41.38879&lon=2.15899"));
        return ciudades;
    }

    @Override
    public String toString() {
        return ciudades+"";
    }
}
