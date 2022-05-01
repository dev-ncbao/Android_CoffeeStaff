package com.example.coffeestaff.Bussiness;

import static com.example.coffeestaff.Commons.Constants.StaffConstants.ALL_COL;
import static com.example.coffeestaff.Commons.Constants.StaffConstants.COL_ID;
import static com.example.coffeestaff.Commons.Constants.StaffConstants.COL_NAME;
import static com.example.coffeestaff.Commons.Constants.StaffConstants.COL_PASSWORD;
import static com.example.coffeestaff.Commons.Constants.StaffConstants.COL_USERNAME;
import static com.example.coffeestaff.Commons.Constants.StaffConstants.NAME;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.coffeestaff.Data.DbHelper;
import com.example.coffeestaff.Data.Staffs;

public class StaffBussiness {
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
        Cursor cursor = _db.query(NAME, ALL_COL, COL_ID + "=" + id, null, null, null, null);
        cursor.moveToFirst();
        Staffs staff = new Staffs(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3)
        );
        return staff;
    }

    public Staffs select(String username, String password){
        Cursor cursor = _db.query(NAME, ALL_COL, String.format("%s=\"%s\" AND %s=\"%s\"", COL_USERNAME, username, COL_PASSWORD, password), null, null, null, null);
        cursor.moveToFirst();
        if(cursor.getCount() == 0) return null;
        Staffs staff = new Staffs(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3)
        );
        return staff;
    }
}
