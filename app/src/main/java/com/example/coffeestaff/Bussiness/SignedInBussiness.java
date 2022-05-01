package com.example.coffeestaff.Bussiness;

import static com.example.coffeestaff.Commons.Constants.SignedInConstants.COL_STAFF_ID;
import static com.example.coffeestaff.Commons.Constants.SignedInConstants.NAME;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.coffeestaff.Data.DbHelper;
import com.example.coffeestaff.Data.SignedIns;

public class SignedInBussiness {

    private SQLiteDatabase _db;

    public SignedInBussiness(Context context){
        DbHelper helper = new DbHelper(context);
        _db = helper.getWritableDatabase();
    }

    public SignedInBussiness(SQLiteDatabase sqLiteDatabase){
        _db = sqLiteDatabase;
    }

    public long insert(SignedIns signedIn){
        ContentValues values = new ContentValues();
        values.put(COL_STAFF_ID, signedIn.getStaffId());
        return _db.insert(NAME, null, values);
    }

    public int delete(Integer id){
        return _db.delete(NAME, COL_STAFF_ID + "=" + id.intValue(), null);
    }

    public SignedIns select(){
        Cursor cursor = _db.query(NAME, new String[]{ COL_STAFF_ID }, null, null, null, null, null);
        cursor.moveToFirst();
        if(cursor.getCount() == 0) return null;
        SignedIns signedIn = new SignedIns(cursor.getInt(0));
        return signedIn;
    }
}
