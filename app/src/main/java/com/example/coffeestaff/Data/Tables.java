package com.example.coffeestaff.Data;

public class Tables {
    public static final String NAME = "Tables";
    public static final String COL_ID = "ID";
    public static final String COL_NAME = "Name";
    public static final String COL_STATUS = "Status";
    public static final String[] ALL_COL = {
            COL_ID, COL_STATUS, COL_NAME
    };
    public static final String CREATE =
            String.format("Create Table %s(" +
                            "%s Integer Primary Key Autoincrement," +
                            "%s Integer," +
                            "%s Text);",
                    NAME, COL_ID, COL_STATUS, COL_NAME);
    //
    private Integer id;
    private String name;
    private Integer status;

    public Tables(Integer id, Integer status, String name) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
