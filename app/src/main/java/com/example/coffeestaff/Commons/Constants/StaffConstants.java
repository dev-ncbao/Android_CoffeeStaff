package com.example.coffeestaff.Commons.Constants;

public class StaffConstants {
    public static final String NAME = "Staffs";
    public static final String COL_ID = "ID";
    public static final String COL_NAME = "Name";
    public static final String COL_USERNAME = "Username";
    public static final String COL_PASSWORD = "Password";
    public static final String[] ALL_COL = {
            COL_ID, COL_NAME, COL_USERNAME, COL_PASSWORD
    };
    public static final String CREATE =
            String.format("Create Table %s(" +
                            "%s Integer Primary Key Autoincrement," +
                            "%s Text," +
                            "%s Text," +
                            "%s Text);",
                    NAME, COL_ID, COL_NAME, COL_USERNAME, COL_PASSWORD);

}
