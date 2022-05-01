package com.example.coffeestaff.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.coffeestaff.Commons.Constants.BillConstants;
import com.example.coffeestaff.Commons.Constants.BillDetailConstants;
import com.example.coffeestaff.Commons.Constants.DrinkConstants;
import com.example.coffeestaff.Commons.Constants.SignedInConstants;
import com.example.coffeestaff.Commons.Constants.StaffConstants;
import com.example.coffeestaff.Commons.Constants.TableConstants;

public class DbHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "CoffeeStaff.db";
    private static final int DB_VERSION = 1;

    public DbHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(StaffConstants.CREATE);
        sqLiteDatabase.execSQL(DrinkConstants.CREATE);
        sqLiteDatabase.execSQL(TableConstants.CREATE);
        sqLiteDatabase.execSQL(BillConstants.CREATE);
        sqLiteDatabase.execSQL(BillDetailConstants.CREATE);
        sqLiteDatabase.execSQL(SignedInConstants.CREATE);
        DefaultConfig.initDefaultDB(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(String.format("Drop Table If Exists %s", BillDetailConstants.NAME));
        sqLiteDatabase.execSQL(String.format("Drop Table If Exists %s", BillConstants.NAME));
        sqLiteDatabase.execSQL(String.format("Drop Table If Exists %s", TableConstants.NAME));
        sqLiteDatabase.execSQL(String.format("Drop Table If Exists %s", DrinkConstants.NAME));
        sqLiteDatabase.execSQL(String.format("Drop Table If Exists %s", StaffConstants.NAME));
        sqLiteDatabase.execSQL(String.format("Drop Table If Exists %s", SignedInConstants.NAME));
        onCreate(sqLiteDatabase);
    }
}
