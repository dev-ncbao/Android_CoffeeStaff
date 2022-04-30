package com.example.coffeestaff.Data;

import java.util.Date;

public class Bills {
    public static final String NAME = "Bills";
    public static final String COL_ID = "ID";
    public static final String COL_TABLE_ID = "TableId";
    public static final String COL_STAFF_ID = "StaffId";
    public static final String COL_TIME_CREATED = "TimeCreated";
    public static final String COL_PAID = "Paid";
    public static final String[] ALL_COL = {
            COL_ID, COL_STAFF_ID, COL_TABLE_ID, COL_TIME_CREATED, COL_PAID
    };
    public static final String CREATE =
            String.format("Create Table %s(" +
                            "%s Integer Primary Key Autoincrement," +
                            "%s Integer," +
                            "%s Integer," +
                            "%s Text," +
                            "%s Integer);",
                    NAME, COL_ID, COL_TABLE_ID, COL_STAFF_ID, COL_TIME_CREATED, COL_PAID);

    //
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
