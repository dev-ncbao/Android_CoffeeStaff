package com.example.coffeestaff.Data.Models;

public class Tables {
    private int id;
    private String name;
    private int status;

    public Tables(int id, int status, String name) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
