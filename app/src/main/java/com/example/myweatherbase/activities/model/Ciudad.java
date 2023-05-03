package com.example.myweatherbase.activities.model;

import java.io.Serializable;
import java.nio.file.Path;

public class Ciudad implements Serializable {
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

    public int getImg() {
        return img;
    }

    @Override
    public String toString() {
        return name + "";
    }
}
