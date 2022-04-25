package com.example.coffeestaff.Data.ModelHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.coffeestaff.Data.DbHelper;
import com.example.coffeestaff.Data.Models.Tables;

import java.util.ArrayList;

public class TablesHelper {
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
    private SQLiteDatabase _db;
    // Helpers
    public TablesHelper(Context context) {
        DbHelper helper = new DbHelper(context);
        _db = helper.getWritableDatabase();
    }

    public TablesHelper(SQLiteDatabase db){
        _db = db;
    }

    public long insert(Tables table){
        ContentValues values = new ContentValues();
        values.put(COL_STATUS, table.getStatus());
        values.put(COL_NAME, table.getName());
        return _db.insert(NAME, null, values);
    }

    public Tables select(int id){
        Cursor cursor = _db.query(NAME, ALL_COL, String.format("%s = %d", COL_ID, id), null, null, null, null);
        cursor.moveToFirst();
        Tables table = new Tables(
                cursor.getInt(0),
                cursor.getInt(1),
                cursor.getString(2)
        );
        return table;
    }

    public ArrayList<Tables> selectAll(){
        Cursor cursor = _db.query(NAME, ALL_COL, null, null, null, null, COL_ID);
        ArrayList<Tables> listTable = new ArrayList<>();
        cursor.moveToFirst();
        do {
            Tables table = new Tables(
                    cursor.getInt(0),
                    cursor.getInt(1),
                    cursor.getString(2)
            );
            listTable.add(table);
        } while(cursor.moveToNext());
        return listTable;
    }

    public int updateStatus(Tables table){
        ContentValues values = new ContentValues();
        values.put(COL_STATUS, table.getStatus());
        values.put(COL_NAME, table.getName());
        return _db.update(NAME, values, "? = ?", new String[]{ COL_ID, table.getId() + "" });
    }
}
