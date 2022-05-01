package com.example.coffeestaff.Commons.Constants;

public class SignedInConstants {
    public static final String NAME = "SignedIn";
    public static final String COL_STAFF_ID = "SignedIn";
    public static final String CREATE =
            String.format("Create Table %s(" +
                            "%s Integer Primary Key);",
                    NAME, COL_STAFF_ID);
}
