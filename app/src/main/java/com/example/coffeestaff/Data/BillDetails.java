package com.example.coffeestaff.Data;

public class BillDetails {
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

    //
    private Integer billId;
    private Integer drinksId;
    private Integer amount;
    private double price;

    public BillDetails(Integer billId, Integer drinksId, Integer amount, double price) {
        this.billId = billId;
        this.drinksId = drinksId;
        this.amount = amount;
        this.price = price;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getBillId() {
        return billId;
    }

    public void setBillId(Integer billId) {
        this.billId = billId;
    }

    public Integer getDrinksId() {
        return drinksId;
    }

    public void setDrinksId(Integer drinksId) {
        this.drinksId = drinksId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
