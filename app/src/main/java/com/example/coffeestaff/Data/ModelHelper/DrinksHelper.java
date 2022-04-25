package com.example.coffeestaff.Data.ModelHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.coffeestaff.Data.DbHelper;
import com.example.coffeestaff.Data.Models.Drinks;
import com.example.coffeestaff.R;

import java.util.ArrayList;

public class DrinksHelper {
    public static final String NAME = "Drinks";
    public static final String COL_ID = "ID";
    public static final String COL_NAME = "Name";
    public static final String COL_PRICE = "Price";
    public static final String COL_IMAGE = "Image";
    public static final String CREATE =
            String.format("Create Table %s(" +
                            "%s Integer Primary Key Autoincrement," +
                            "%s Text," +
                            "%s Text," +
                            "%s Real);",
                    NAME, COL_ID, COL_NAME, COL_IMAGE, COL_PRICE);
    public static final String[] ALL_COL = {
            COL_ID, COL_NAME, COL_PRICE, COL_IMAGE
    };
    private SQLiteDatabase _db;

    // Helpers
    public DrinksHelper(Context context) {
        DbHelper helper = new DbHelper(context);
        _db = helper.getWritableDatabase();
    }

    public DrinksHelper(SQLiteDatabase db){
        _db = db;
    }

    public long insert(Drinks drinks) {
        ContentValues values = new ContentValues();
        values.put(COL_NAME, drinks.getName());
        values.put(COL_PRICE, drinks.getPrice());
        values.put(COL_IMAGE, drinks.getImage());
        return _db.insert(NAME, null, values);
    }

    public Drinks select(int id) {
        Cursor cursor = _db.query(NAME, ALL_COL, String.format("%s = %d", COL_ID, id), null, null, null, null);
        cursor.moveToFirst();
        Drinks drink = new Drinks(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getDouble(2),
                cursor.getInt(3)
        );
        return drink;
    }

    public ArrayList<Drinks> selectAll() {
        ArrayList<Drinks> listDrinks = new ArrayList<>();
        Cursor cursor = _db.query(NAME, ALL_COL, null, null, null, null, null);
        cursor.moveToFirst();
        do {
            Drinks drink = new Drinks(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getDouble(2),
                    cursor.getInt(3)
            );
            listDrinks.add(drink);
        } while(cursor.moveToNext());
        return listDrinks;
    }
}

