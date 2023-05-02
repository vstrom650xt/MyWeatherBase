package com.example.myweatherbase.activities.model;

import java.nio.file.Path;

public class Ciudad {
    private String name;
    private String path;

    public Ciudad(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }
}
