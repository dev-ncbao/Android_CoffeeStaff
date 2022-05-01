package com.example.coffeestaff.Data;

public class Tables {
    private Integer id;
    private String name;
    private Integer status;

    public Tables(Integer id, Integer status, String name) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
