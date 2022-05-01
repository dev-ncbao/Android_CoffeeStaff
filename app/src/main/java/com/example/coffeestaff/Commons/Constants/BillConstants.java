package com.example.coffeestaff.Commons.Constants;

public class BillConstants {
    public static final String NAME = "Bills";
    public static final String COL_ID = "ID";
    public static final String COL_TABLE_ID = "TableId";
    public static final String COL_STAFF_ID = "StaffId";
    public static final String COL_TIME_CREATED = "TimeCreated";
    public static final String COL_PAID = "Paid";
    public static final String[] ALL_COL = {
            COL_ID, COL_STAFF_ID, COL_TABLE_ID, COL_TIME_CREATED, COL_PAID
    };
    public static final String CREATE =
            String.format("Create Table %s(" +
                            "%s Integer Primary Key Autoincrement," +
                            "%s Integer," +
                            "%s Integer," +
                            "%s Text," +
                            "%s Integer);",
                    NAME, COL_ID, COL_TABLE_ID, COL_STAFF_ID, COL_TIME_CREATED, COL_PAID);

}
