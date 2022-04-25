package com.example.coffeestaff;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.coffeestaff.Data.DbHelper;
import com.example.coffeestaff.Data.DefaultConfig;
import com.example.coffeestaff.Data.ModelHelper.StaffsHelper;
import com.example.coffeestaff.Data.Models.Staffs;
import com.example.coffeestaff.Presentation.ChooseDrinksActivity;
import com.example.coffeestaff.Presentation.HomeActivity;
import com.example.coffeestaff.Presentation.LoginActivity;
import com.example.coffeestaff.Presentation.ServingAcvitity;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        DbHelper db = new DbHelper(this);
        DefaultConfig.dropAllTable(db.getWritableDatabase());
        db.onCreate(db.getReadableDatabase());
        Timer timer = new Timer("setTimeout");
        timer.schedule(new TimerTask(){
            public void run(){
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        },1000);
    }
}