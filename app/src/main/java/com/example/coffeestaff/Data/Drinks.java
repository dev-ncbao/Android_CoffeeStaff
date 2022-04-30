package com.example.coffeestaff.Data;

public class Drinks {
    public static final String NAME = "Drinks";
    public static final String COL_ID = "ID";
    public static final String COL_NAME = "Name";
    public static final String COL_PRICE = "Price";
    public static final String COL_IMAGE = "Image";
    public static final String CREATE =
            String.format("Create Table %s(" +
                            "%s Integer Primary Key Autoincrement," +
                            "%s Text," +
                            "%s Text," +
                            "%s Real);",
                    NAME, COL_ID, COL_NAME, COL_IMAGE, COL_PRICE);
    public static final String[] ALL_COL = {
            COL_ID, COL_NAME, COL_PRICE, COL_IMAGE
    };
    //
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
