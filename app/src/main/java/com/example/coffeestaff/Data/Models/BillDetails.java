package com.example.coffeestaff.Data.Models;

public class BillDetails {
    private int billId;
    private int drinksId;
    private int amount;
    private double price;

    public BillDetails(int billId, int drinksId, int amount, double price) {
        this.billId = billId;
        this.drinksId = drinksId;
        this.amount = amount;
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public int getDrinksId() {
        return drinksId;
    }

    public void setDrinksId(int drinksId) {
        this.drinksId = drinksId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
