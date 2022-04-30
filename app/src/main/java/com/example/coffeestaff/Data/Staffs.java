package com.example.coffeestaff.Data;

public class Staffs {
    public static final String NAME = "Staffs";
    public static final String COL_ID = "ID";
    public static final String COL_NAME = "Name";
    public static final String COL_USERNAME = "Username";
    public static final String COL_PASSWORD = "Password";
    public static final String[] ALL_COL = {
            COL_ID, COL_NAME, COL_USERNAME, COL_PASSWORD
    };
    public static final String CREATE =
            String.format("Create Table %s(" +
                            "%s Integer Primary Key Autoincrement," +
                            "%s Text," +
                            "%s Text," +
                            "%s Text);",
                    NAME, COL_ID, COL_NAME, COL_USERNAME, COL_PASSWORD);

    //
    private Integer id;
    private String name;
    private String username;
    private String password;

    public Staffs(Integer id, String name, String username, String password) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
