package com.example.coffeestaff.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.coffeestaff.Bussiness.BillDetailBussiness;
import com.example.coffeestaff.Bussiness.BillBussiness;
import com.example.coffeestaff.Bussiness.DrinkBussiness;
import com.example.coffeestaff.Bussiness.SignedInBussiness;
import com.example.coffeestaff.Bussiness.StaffBussiness;
import com.example.coffeestaff.Bussiness.TableBussiness;

public class DbHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "CoffeeStaff.db";
    private static final int DB_VERSION = 1;

    public DbHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(Staffs.CREATE);
        sqLiteDatabase.execSQL(Drinks.CREATE);
        sqLiteDatabase.execSQL(Tables.CREATE);
        sqLiteDatabase.execSQL(Bills.CREATE);
        sqLiteDatabase.execSQL(BillDetails.CREATE);
        sqLiteDatabase.execSQL(SignedIns.CREATE);
        DefaultConfig.initDefaultDB(sqLiteDatabase);
        Log.i("result-initDB", "Created database");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(String.format("Drop Table If Exists %s", BillDetails.NAME));
        sqLiteDatabase.execSQL(String.format("Drop Table If Exists %s", Bills.NAME));
        sqLiteDatabase.execSQL(String.format("Drop Table If Exists %s", Tables.NAME));
        sqLiteDatabase.execSQL(String.format("Drop Table If Exists %s", Drinks.NAME));
        sqLiteDatabase.execSQL(String.format("Drop Table If Exists %s", Staffs.NAME));
        sqLiteDatabase.execSQL(String.format("Drop Table If Exists %s", SignedIns.NAME));
        onCreate(sqLiteDatabase);
    }
}
