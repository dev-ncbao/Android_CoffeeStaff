package com.example.coffeestaff.Commons.Helpers;

import com.example.coffeestaff.Data.BillDetails;

import java.util.ArrayList;

public class Calculate {
    public static double calculateTotalPrice(ArrayList<BillDetails> billDetails){
        double total = 0;
        for (int i = 0; i < billDetails.size(); i++) {
            BillDetails bd = billDetails.get(i);
            total += bd.getAmount() * bd.getPrice();
        }
        return total;
    }

    public static Integer calculateTotalAmount(ArrayList<BillDetails> billDetails){
        Integer total = 0;
        for (int i = 0; i < billDetails.size(); i++) {
            BillDetails bd = billDetails.get(i);
            total += bd.getAmount();
        }
        return total;
    }
}
