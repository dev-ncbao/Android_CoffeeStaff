package com.example.coffeestaff.Data;

public class SignedIns {
    public static final String NAME = "SignedIn";
    public static final String COL_STAFF_ID = "SignedIn";
    public static final String CREATE =
            String.format("Create Table %s(" +
                            "%s Integer Primary Key);",
                    NAME, COL_STAFF_ID);
    //
    private Integer staffId;

    public SignedIns(Integer staffId) {
        this.staffId = staffId;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }
}
