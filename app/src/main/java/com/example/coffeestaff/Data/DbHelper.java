package com.example.coffeestaff.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v4.os.IResultReceiver;
import android.util.Log;

import com.example.coffeestaff.Data.ModelHelper.BillDetailsHelper;
import com.example.coffeestaff.Data.ModelHelper.BillsHelper;
import com.example.coffeestaff.Data.ModelHelper.DrinksHelper;
import com.example.coffeestaff.Data.ModelHelper.SignedInHelper;
import com.example.coffeestaff.Data.ModelHelper.StaffsHelper;
import com.example.coffeestaff.Data.ModelHelper.TablesHelper;

public class DbHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "CoffeeStaff.db";
    private static final int DB_VERSION = 1;

    public DbHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(StaffsHelper.CREATE);
        sqLiteDatabase.execSQL(DrinksHelper.CREATE);
        sqLiteDatabase.execSQL(TablesHelper.CREATE);
        sqLiteDatabase.execSQL(BillsHelper.CREATE);
        sqLiteDatabase.execSQL(BillDetailsHelper.CREATE);
        sqLiteDatabase.execSQL(SignedInHelper.CREATE);
        DefaultConfig.initDefaultDB(sqLiteDatabase);
        Log.i("result-initDB", "Created database");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(String.format("Drop Table If Exists %s", BillDetailsHelper.NAME));
        sqLiteDatabase.execSQL(String.format("Drop Table If Exists %s", BillsHelper.NAME));
        sqLiteDatabase.execSQL(String.format("Drop Table If Exists %s", TablesHelper.NAME));
        sqLiteDatabase.execSQL(String.format("Drop Table If Exists %s", DrinksHelper.NAME));
        sqLiteDatabase.execSQL(String.format("Drop Table If Exists %s", StaffsHelper.NAME));
        sqLiteDatabase.execSQL(String.format("Drop Table If Exists %s", SignedInHelper.NAME));
        onCreate(sqLiteDatabase);
    }
}
