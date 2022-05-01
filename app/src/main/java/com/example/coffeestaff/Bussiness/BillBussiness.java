package com.example.coffeestaff.Bussiness;

import static com.example.coffeestaff.Commons.Constants.BillConstants.ALL_COL;
import static com.example.coffeestaff.Commons.Constants.BillConstants.COL_ID;
import static com.example.coffeestaff.Commons.Constants.BillConstants.COL_PAID;
import static com.example.coffeestaff.Commons.Constants.BillConstants.COL_STAFF_ID;
import static com.example.coffeestaff.Commons.Constants.BillConstants.COL_TABLE_ID;
import static com.example.coffeestaff.Commons.Constants.BillConstants.COL_TIME_CREATED;
import static com.example.coffeestaff.Commons.Constants.BillConstants.NAME;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.example.coffeestaff.Data.DbHelper;
import com.example.coffeestaff.Data.Bills;

public class BillBussiness {
    private SQLiteDatabase _db;

    public BillBussiness(Context context) {
        DbHelper helper = new DbHelper(context);
        _db = helper.getWritableDatabase();
    }

    public BillBussiness(SQLiteDatabase db) {
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
        cursor.moveToFirst();
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
        if (cursor.getCount() > 0) {
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
        }
        return listBill;
    }

    public ArrayList<Bills> selectAllNotPaidYet() {
        ArrayList<Bills> listBill = new ArrayList<>();
        Cursor cursor = _db.query(NAME, ALL_COL, COL_PAID + "=" + 1, null, null, null, null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
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
        }
        return listBill;
    }

    public Integer selectBillId(Integer tableId) {
        Cursor cursor = _db.query(NAME, ALL_COL, COL_TABLE_ID + "=" + tableId + " AND " + COL_PAID + "=" + 0, null, null, null, null);
        cursor.moveToFirst();
        return cursor.getInt(0);
    }

    public Integer updatePaid(Bills bill) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy - hh:mm:ss a");
        ContentValues values = new ContentValues();
        values.put(COL_STAFF_ID, bill.getStaffId());
        values.put(COL_TABLE_ID, bill.getTableId());
        values.put(COL_TIME_CREATED, sdf.format(new Date()));
        values.put(COL_PAID, 1);
        return _db.update(NAME, values, COL_ID + "=" + bill.getId(), null);
    }
}
