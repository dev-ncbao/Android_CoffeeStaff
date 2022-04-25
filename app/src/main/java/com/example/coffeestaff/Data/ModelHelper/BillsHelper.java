package com.example.coffeestaff.Data.ModelHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import com.example.coffeestaff.Data.DbHelper;
import com.example.coffeestaff.Data.Models.Bills;

public class BillsHelper {
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
    private SQLiteDatabase _db;

    // Helpers
    public BillsHelper(Context context) {
        DbHelper helper = new DbHelper(context);
        _db = helper.getWritableDatabase();
    }

    public BillsHelper(SQLiteDatabase db) {
        _db = db;
    }

    public long insert(Bills bill) {
        ContentValues values = new ContentValues();
        values.put(COL_TABLE_ID, bill.getTableId());
        values.put(COL_STAFF_ID, bill.getStaffId());
        values.put(COL_TIME_CREATED, bill.getTimeCreated());
        values.put(COL_PAID, bill.getPaid());
        return _db.insert(NAME, null, values);
    }

    public Bills select(int id) {
        Cursor cursor = _db.query(NAME, ALL_COL, String.format("%s = %d", COL_ID, id), null, null, null, null);
        Bills bill = null;
        bill = new Bills(
                cursor.getInt(0),
                cursor.getInt(1),
                cursor.getInt(2),
                cursor.getString(3),
                cursor.getInt(4)
        );
        return bill;
    }

    public ArrayList<Bills> selectAll() {
        ArrayList<Bills> listBill = new ArrayList<>();
        Cursor cursor = _db.query(NAME, ALL_COL, null, null, null, null, null);
        cursor.moveToFirst();
        do {
            Bills bill = null;
            bill = new Bills(
                    cursor.getInt(0),
                    cursor.getInt(1),
                    cursor.getInt(2),
                    cursor.getString(3),
                    cursor.getInt(4)
            );
            listBill.add(bill);
        } while (cursor.moveToNext());
        return listBill;
    }

    /*public ArrayList<Bills> selectByDate(Date date){
        ArrayList<Bills> listBill = new ArrayList<>();
        Cursor cursor = _db.query(NAME, ALL_COL, , null,null,null,null);
        cursor.moveToFirst();
        do {
            Bills bill = null;
            try{
                bill = new Bills(
                        cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getInt(2),
                        DateFormat.getDateInstance().parse(cursor.getString(3))
                );
                listBill.add(bill);
            }catch (ParseException e){
                e.printStackTrace();
            }
        }while(cursor.moveToNext());
        return listBill;
    }*/
}
