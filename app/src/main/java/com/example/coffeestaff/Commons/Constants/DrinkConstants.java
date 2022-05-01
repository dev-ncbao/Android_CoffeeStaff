package com.example.coffeestaff.Commons.Constants;

public class DrinkConstants {
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
}
