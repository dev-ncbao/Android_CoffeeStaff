package com.example.coffeestaff.Commons.Constants;

public class BillDetailConstants {
    public static final String NAME = "BillDetails";
    public static final String COL_BILL_ID = "BillId";
    public static final String COL_DRINKS_ID = "DrinksId";
    public static final String COL_AMOUNT = "Amount";
    public static final String COL_PRICE = "Price";
    public static final String[] ALL_COL = {
            COL_BILL_ID, COL_DRINKS_ID, COL_AMOUNT, COL_PRICE
    };
    public static final String CREATE =
            String.format("Create Table %s(" +
                            "%s Integer," +
                            "%s Integer," +
                            "%s Integer," +
                            "%s Real);",
                    NAME, COL_BILL_ID, COL_DRINKS_ID, COL_AMOUNT, COL_PRICE);

}
