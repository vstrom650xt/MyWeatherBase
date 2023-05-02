package com.example.myweatherbase.activities.model;

import java.nio.file.Path;

public class Ciudad {
    private String name;
    private String path;
    private int img;


    public Ciudad(String name, String path,int img) {
        this.img=img;
        this.name = name;
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    @Override
    public String toString() {
        return name + "";
    }
}
