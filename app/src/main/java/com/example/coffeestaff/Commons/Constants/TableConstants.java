package com.example.coffeestaff.Commons.Constants;

public class TableConstants {
    public static final String NAME = "Tables";
    public static final String COL_ID = "ID";
    public static final String COL_NAME = "Name";
    public static final String COL_STATUS = "Status";
    public static final String[] ALL_COL = {
            COL_ID, COL_STATUS, COL_NAME
    };
    public static final String CREATE =
            String.format("Create Table %s(" +
                            "%s Integer Primary Key Autoincrement," +
                            "%s Integer," +
                            "%s Text);",
                    NAME, COL_ID, COL_STATUS, COL_NAME);
}
