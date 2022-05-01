package com.example.coffeestaff.Data;

import android.database.sqlite.SQLiteDatabase;

import com.example.coffeestaff.Bussiness.BillDetailBussiness;
import com.example.coffeestaff.Bussiness.BillBussiness;
import com.example.coffeestaff.Bussiness.BussinessDistribution;
import com.example.coffeestaff.Bussiness.DrinkBussiness;
import com.example.coffeestaff.Bussiness.SignedInBussiness;
import com.example.coffeestaff.Bussiness.StaffBussiness;
import com.example.coffeestaff.Bussiness.TableBussiness;
import com.example.coffeestaff.Commons.Constants.BillConstants;
import com.example.coffeestaff.Commons.Constants.BillDetailConstants;
import com.example.coffeestaff.Commons.Constants.DrinkConstants;
import com.example.coffeestaff.Commons.Constants.SignedInConstants;
import com.example.coffeestaff.Commons.Constants.StaffConstants;
import com.example.coffeestaff.Commons.Constants.TableConstants;
import com.example.coffeestaff.R;

public class DefaultConfig {
    public static void dropAllTable(SQLiteDatabase db){
        db.execSQL(String.format("Drop Table If Exists %s", BillDetailConstants.NAME));
        db.execSQL(String.format("Drop Table If Exists %s", BillConstants.NAME));
        db.execSQL(String.format("Drop Table If Exists %s", TableConstants.NAME));
        db.execSQL(String.format("Drop Table If Exists %s", DrinkConstants.NAME));
        db.execSQL(String.format("Drop Table If Exists %s", StaffConstants.NAME));
        db.execSQL(String.format("Drop Table If Exists %s", SignedInConstants.NAME));
    }

    public static void initDefaultDB(SQLiteDatabase db){
        BussinessDistribution bussinessDistribution = new BussinessDistribution(db);
        StaffBussiness staffBussiness = bussinessDistribution.getStaffBussiness();
        SignedInBussiness signedInBussiness = bussinessDistribution.getSignedInBussiness();
        DrinkBussiness drinkBussiness = bussinessDistribution.getDrinkBussiness();
        TableBussiness tableBussiness = bussinessDistribution.getTableBussiness();
        // staffs
        Long signedInId = staffBussiness.insert(new Staffs(0, "Nguyễn Chí Bảo", "admin", "admin"));
        staffBussiness.insert(new Staffs(0, "Trần Văn Chí", "staff123", "staff123"));
        staffBussiness.insert(new Staffs(0, "Lê Thị Hương", "staff456", "staff456"));
        // signed in
        //signedInBussiness.insert(new SignedIns(signedInId.intValue()));
        // drinks
        drinkBussiness.insert(new Drinks(0, "Cà phê", 17000, R.drawable.cafe));
        drinkBussiness.insert(new Drinks(0, "Cà phê sữa", 20000, R.drawable.cafe_sua));
        drinkBussiness.insert(new Drinks(0, "Bạc xỉu", 20000, R.drawable.bac_xiu));
        drinkBussiness.insert(new Drinks(0, "Trà đường", 15000, R.drawable.tra_duong));
        // tables
        tableBussiness.insert(new Tables(0, 0, "Bàn 1"));
        tableBussiness.insert(new Tables(0, 0, "Bàn 2"));
        tableBussiness.insert(new Tables(0, 0, "Bàn 3"));
        tableBussiness.insert(new Tables(0, 0, "Bàn 4"));
        tableBussiness.insert(new Tables(0, 0, "Bàn 5"));
        tableBussiness.insert(new Tables(0, 0, "Bàn 6"));
        tableBussiness.insert(new Tables(0, 0, "Bàn 7"));
        tableBussiness.insert(new Tables(0, 0, "Bàn 8"));
        tableBussiness.insert(new Tables(0, 0, "Bàn 9"));
        tableBussiness.insert(new Tables(0, 0, "Bàn 10"));
    }
}
