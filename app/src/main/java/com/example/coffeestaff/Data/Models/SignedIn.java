package com.example.coffeestaff.Data.Models;

public class SignedIn {
    private Integer staffId;

    public SignedIn(Integer staffId) {
        this.staffId = staffId;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }
}
