package com.example.coffeestaff.Commons.Models;

public class Order {
    private Integer drinkId;
    private Integer amount;

    public Order(Integer drinkId, Integer amount) {
        this.drinkId = drinkId;
        this.amount = amount;
    }

    public Integer getDrinkId() {
        return drinkId;
    }

    public void setDrinkId(Integer drinkId) {
        this.drinkId = drinkId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}

