package com.example.coffeestaff.Data;

public class Drinks {
    private Integer id;
    private String name;
    private double price;
    private Integer image;

    public Drinks(Integer id, String name, double price, Integer image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
