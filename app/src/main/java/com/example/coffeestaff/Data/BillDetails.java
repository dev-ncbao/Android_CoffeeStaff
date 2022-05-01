package com.example.coffeestaff.Data;

public class BillDetails {
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
