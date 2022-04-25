package com.example.coffeestaff.Data.ModelHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.coffeestaff.Data.DbHelper;
import com.example.coffeestaff.Data.Models.SignedIn;

public class SignedInHelper {
    public static final String NAME = "SignedIn";
    public static final String COL_STAFF_ID = "SignedIn";
    public static final String CREATE =
            String.format("Create Table %s(" +
                    "%s Integer Primary Key);",
                    NAME, COL_STAFF_ID);

    private SQLiteDatabase _db;

    public SignedInHelper(Context context){
        DbHelper helper = new DbHelper(context);
        _db = helper.getWritableDatabase();
    }

    public SignedInHelper(SQLiteDatabase sqLiteDatabase){
        _db = sqLiteDatabase;
    }

    public long insert(SignedIn signedIn){
        ContentValues values = new ContentValues();
        values.put(COL_STAFF_ID, signedIn.getStaffId());
        return _db.insert(NAME, null, values);
    }

    public int delete(Integer id){
        return _db.delete(NAME, "? = ?", new String[]{ COL_STAFF_ID,  id.toString()});
    }

    public SignedIn select(){
        Cursor cursor = _db.query(NAME, new String[]{ COL_STAFF_ID }, null, null, null, null, null);
        cursor.moveToFirst();
        SignedIn signedIn = new SignedIn(cursor.getInt(0));
        return signedIn;
    }
}
