package com.example.coffeestaff.Data.Models;

import java.util.Date;

public class Bills {
    private int id;
    private int tableId;
    private int staffId;
    private String timeCreated;
    private int paid;

    public Bills(int id, int tableId, int staffId, String timeCreated, int paid) {
        this.id = id;
        this.tableId = tableId;
        this.staffId = staffId;
        this.timeCreated = timeCreated;
        this.paid = paid;
    }

    public int getPaid() {
        return paid;
    }

    public void setPaid(int paid) {
        this.paid = paid;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public String getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(String timeCreated) {
        this.timeCreated = timeCreated;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }
}
