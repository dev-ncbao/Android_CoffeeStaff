package com.example.coffeestaff.Bussiness;

import static com.example.coffeestaff.Data.BillDetails.ALL_COL;
import static com.example.coffeestaff.Data.BillDetails.COL_AMOUNT;
import static com.example.coffeestaff.Data.BillDetails.COL_BILL_ID;
import static com.example.coffeestaff.Data.BillDetails.COL_DRINKS_ID;
import static com.example.coffeestaff.Data.BillDetails.COL_PRICE;
import static com.example.coffeestaff.Data.BillDetails.NAME;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.coffeestaff.Data.DbHelper;
import com.example.coffeestaff.Data.BillDetails;

import java.util.ArrayList;

public class BillDetailBussiness {
    /*public static final String NAME = "BillDetails";
    public static final String COL_BILL_ID = "BillId";
    public static final String COL_DRINKS_ID = "DrinksId";
    public static final String COL_AMOUNT = "Amount";
    public static final String COL_PRICE = "Price";
    public static final String[] ALL_COL = {
            COL_BILL_ID, COL_DRINKS_ID, COL_AMOUNT, COL_PRICE
    };
    public static final String CREATE =
            String.format("Create Table %s(" +
                            "%s Integer," +
                            "%s Integer," +
                            "%s Integer," +
                            "%s Real);",
                    NAME, COL_BILL_ID, COL_DRINKS_ID, COL_AMOUNT, COL_PRICE);*/
    private SQLiteDatabase _db;

    // Helpers
    public BillDetailBussiness(Context context) {
        DbHelper helper = new DbHelper(context);
        _db = helper.getWritableDatabase();
    }

    public BillDetailBussiness(SQLiteDatabase db){
        _db = db;
    }

    public long insert(BillDetails bd) {
        ContentValues values = new ContentValues();
        values.put(COL_BILL_ID, bd.getBillId());
        values.put(COL_DRINKS_ID, bd.getDrinksId());
        values.put(COL_AMOUNT, bd.getAmount());
        values.put(COL_PRICE, bd.getPrice());
        return _db.insert(NAME, null, values);
    }

    public ArrayList<BillDetails> selectByBill(int id) {
        ArrayList<BillDetails> listBD = new ArrayList<>();
        Cursor cursor = _db.query(NAME, ALL_COL, String.format("%s = %d", COL_BILL_ID, id), null, null, null, null);
        cursor.moveToFirst();
        do {
            BillDetails bd = new BillDetails(
                    cursor.getInt(0),
                    cursor.getInt(1),
                    cursor.getInt(2),
                    cursor.getDouble(3)
            );
            listBD.add(bd);
        } while (cursor.moveToNext());
        return listBD;
    }
}
