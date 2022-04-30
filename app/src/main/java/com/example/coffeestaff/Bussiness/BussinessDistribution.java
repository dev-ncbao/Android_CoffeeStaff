package com.example.coffeestaff.Bussiness;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.coffeestaff.Data.DbHelper;

public class BussinessDistribution {
    private SQLiteDatabase _db;

    public BussinessDistribution(SQLiteDatabase db) {
        _db = db;
    }

    public BussinessDistribution(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        _db = dbHelper.getWritableDatabase();
    }

    public StaffBussiness getStaffBussiness() {
        return new StaffBussiness(_db);
    }

    public TableBussiness getTableBussiness() {
        return new TableBussiness(_db);
    }

    public DrinkBussiness getDrinkBussiness() {
        return new DrinkBussiness(_db);
    }

    public SignedInBussiness getSignedInBussiness() {
        return new SignedInBussiness(_db);
    }

    public BillBussiness getBillBussiness() {
        return new BillBussiness(_db);
    }

    public BillDetailBussiness getBillDetailBussiness() {
        return new BillDetailBussiness(_db);
    }
}
