package com.example.coffeestaff.Data.CommonModels;

import com.example.coffeestaff.Data.Models.Drinks;

import java.util.ArrayList;

public class DrinkOrders {
    private Integer drinkId;
    private Integer amount;

    public DrinkOrders(Integer drinkId, Integer amount) {
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

