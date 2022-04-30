package com.example.coffeestaff.Bussiness;

import static com.example.coffeestaff.Data.Staffs.ALL_COL;
import static com.example.coffeestaff.Data.Staffs.COL_ID;
import static com.example.coffeestaff.Data.Staffs.COL_NAME;
import static com.example.coffeestaff.Data.Staffs.COL_PASSWORD;
import static com.example.coffeestaff.Data.Staffs.COL_USERNAME;
import static com.example.coffeestaff.Data.Staffs.NAME;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.coffeestaff.Data.DbHelper;
import com.example.coffeestaff.Data.Staffs;

public class StaffBussiness {
    /*public static final String NAME = "Staffs";
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
*/
    private SQLiteDatabase _db;
    // Helpers
    public StaffBussiness(Context context) {
        DbHelper helper = new DbHelper(context);
        _db = helper.getWritableDatabase();
    }

    public StaffBussiness(SQLiteDatabase db){
        _db = db;
    }

    public long insert(Staffs staff){
        ContentValues values = new ContentValues();
        values.put(COL_NAME, staff.getName());
        values.put(COL_USERNAME, staff.getUsername());
        values.put(COL_PASSWORD, staff.getPassword());
        return _db.insert(NAME, null, values);
    }

    public Staffs select(int id){
        Cursor cursor = _db.query(NAME, ALL_COL, "? = ?", new String[]{ COL_ID, id + "" }, null, null, null);
        cursor.moveToFirst();
        Staffs staff = new Staffs(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3)
        );
        return staff;
    }
}
