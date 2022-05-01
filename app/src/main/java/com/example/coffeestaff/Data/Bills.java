package com.example.coffeestaff.Data;

import java.util.Date;

public class Bills {
    private Integer id;
    private Integer tableId;
    private Integer staffId;
    private String timeCreated;
    private Integer paid;

    public Bills(Integer id, Integer tableId, Integer staffId, String timeCreated, Integer paid) {
        this.id = id;
        this.tableId = tableId;
        this.staffId = staffId;
        this.timeCreated = timeCreated;
        this.paid = paid;
    }

    public Integer getPaid() {
        return paid;
    }

    public void setPaid(Integer paid) {
        this.paid = paid;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTableId() {
        return tableId;
    }

    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }

    public String getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(String timeCreated) {
        this.timeCreated = timeCreated;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }
}
