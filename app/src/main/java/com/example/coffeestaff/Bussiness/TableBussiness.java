package com.example.coffeestaff.Bussiness;

import static com.example.coffeestaff.Commons.Constants.TableConstants.ALL_COL;
import static com.example.coffeestaff.Commons.Constants.TableConstants.COL_ID;
import static com.example.coffeestaff.Commons.Constants.TableConstants.COL_NAME;
import static com.example.coffeestaff.Commons.Constants.TableConstants.COL_STATUS;
import static com.example.coffeestaff.Commons.Constants.TableConstants.NAME;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.coffeestaff.Data.DbHelper;
import com.example.coffeestaff.Data.Tables;

import java.util.ArrayList;

public class TableBussiness {

    private SQLiteDatabase _db;

    // Helpers
    public TableBussiness(Context context) {
        DbHelper helper = new DbHelper(context);
        _db = helper.getWritableDatabase();
    }

    public TableBussiness(SQLiteDatabase db) {
        _db = db;
    }

    public long insert(Tables table) {
        ContentValues values = new ContentValues();
        values.put(COL_STATUS, table.getStatus());
        values.put(COL_NAME, table.getName());
        return _db.insert(NAME, null, values);
    }

    public Tables select(int id) {
        Cursor cursor = _db.query(NAME, ALL_COL, String.format("%s = %d", COL_ID, id), null, null, null, null);
        cursor.moveToFirst();
        Tables table = new Tables(
                cursor.getInt(0),
                cursor.getInt(1),
                cursor.getString(2)
        );
        return table;
    }

    public ArrayList<Tables> selectAll() {
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
        } while (cursor.moveToNext());
        return listTable;
    }

    public ArrayList<Tables> selectBeingServed() {
        Cursor cursor = _db.query(NAME, ALL_COL, null, null, null, null, COL_ID);
        ArrayList<Tables> listTable = new ArrayList<>();
        cursor.moveToFirst();
        do {
            if(cursor.getInt(1) == 0) continue;
            Tables table = new Tables(
                    cursor.getInt(0),
                    cursor.getInt(1),
                    cursor.getString(2)
            );
            listTable.add(table);
        } while (cursor.moveToNext());
        return listTable;
    }

    public int updateStatus(Tables table) {
        ContentValues values = new ContentValues();
        values.put(COL_STATUS, table.getStatus());
        values.put(COL_NAME, table.getName());
        return _db.update(NAME, values, COL_ID + "=" + table.getId().toString(), null);
    }
}
