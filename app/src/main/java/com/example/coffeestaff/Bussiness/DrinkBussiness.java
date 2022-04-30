package com.example.coffeestaff.Bussiness;

import static com.example.coffeestaff.Data.Drinks.ALL_COL;
import static com.example.coffeestaff.Data.Drinks.COL_ID;
import static com.example.coffeestaff.Data.Drinks.COL_IMAGE;
import static com.example.coffeestaff.Data.Drinks.COL_NAME;
import static com.example.coffeestaff.Data.Drinks.COL_PRICE;
import static com.example.coffeestaff.Data.Drinks.NAME;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.coffeestaff.Data.DbHelper;
import com.example.coffeestaff.Data.Drinks;

import java.util.ArrayList;

public class DrinkBussiness {

    private SQLiteDatabase _db;

    public DrinkBussiness(Context context) {
        DbHelper helper = new DbHelper(context);
        _db = helper.getWritableDatabase();
    }

    public DrinkBussiness(SQLiteDatabase db){
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

