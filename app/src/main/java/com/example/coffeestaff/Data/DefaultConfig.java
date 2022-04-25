package com.example.coffeestaff.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.coffeestaff.Data.ModelHelper.BillDetailsHelper;
import com.example.coffeestaff.Data.ModelHelper.BillsHelper;
import com.example.coffeestaff.Data.ModelHelper.DrinksHelper;
import com.example.coffeestaff.Data.ModelHelper.SignedInHelper;
import com.example.coffeestaff.Data.ModelHelper.StaffsHelper;
import com.example.coffeestaff.Data.ModelHelper.TablesHelper;
import com.example.coffeestaff.Data.Models.Drinks;
import com.example.coffeestaff.Data.Models.SignedIn;
import com.example.coffeestaff.Data.Models.Staffs;
import com.example.coffeestaff.Data.Models.Tables;
import com.example.coffeestaff.R;

public class DefaultConfig {
    public static void dropAllTable(SQLiteDatabase db){
        db.execSQL(String.format("Drop Table If Exists %s", BillDetailsHelper.NAME));
        db.execSQL(String.format("Drop Table If Exists %s", BillsHelper.NAME));
        db.execSQL(String.format("Drop Table If Exists %s", TablesHelper.NAME));
        db.execSQL(String.format("Drop Table If Exists %s", DrinksHelper.NAME));
        db.execSQL(String.format("Drop Table If Exists %s", StaffsHelper.NAME));
        db.execSQL(String.format("Drop Table If Exists %s", SignedInHelper.NAME));
    }

    public static void initDefaultDB(SQLiteDatabase db){
        StaffsHelper staffsHelper = new StaffsHelper(db);
        DrinksHelper drinksHelper = new DrinksHelper(db);
        TablesHelper tablesHelper = new TablesHelper(db);
        SignedInHelper signedInHelper = new SignedInHelper(db);
        /*BillsHelper billsHelper = new BillsHelper(db);
        BillDetailsHelper billDetailsHelper = new BillDetailsHelper(db);*/
        // staffs
        Long signedInId = staffsHelper.insert(new Staffs(0, "Nguyễn Chí Bảo", "admin", "admin"));
        staffsHelper.insert(new Staffs(0, "Trần Văn Chí", "staff123", "staff123"));
        staffsHelper.insert(new Staffs(0, "Lê Thị Hương", "staff456", "staff456"));
        // signed in
        signedInHelper.insert(new SignedIn(signedInId.intValue()));
        // drinks
        drinksHelper.insert(new Drinks(0, "Cà phê", 17000, R.drawable.cafe));
        drinksHelper.insert(new Drinks(0, "Cà phê sữa", 20000, R.drawable.cafe_sua));
        drinksHelper.insert(new Drinks(0, "Bạc xỉu", 20000, R.drawable.bac_xiu));
        drinksHelper.insert(new Drinks(0, "Trà đường", 15000, R.drawable.tra_duong));
        // tables
        tablesHelper.insert(new Tables(0, 0, "Bàn 1"));
        tablesHelper.insert(new Tables(0, 0, "Bàn 2"));
        tablesHelper.insert(new Tables(0, 0, "Bàn 3"));
        tablesHelper.insert(new Tables(0, 0, "Bàn 4"));
        tablesHelper.insert(new Tables(0, 0, "Bàn 5"));
        tablesHelper.insert(new Tables(0, 0, "Bàn 6"));
        tablesHelper.insert(new Tables(0, 0, "Bàn 7"));
        tablesHelper.insert(new Tables(0, 0, "Bàn 8"));
        tablesHelper.insert(new Tables(0, 0, "Bàn 9"));
        tablesHelper.insert(new Tables(0, 0, "Bàn 10"));
    }
}
