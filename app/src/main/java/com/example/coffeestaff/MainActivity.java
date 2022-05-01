package com.example.coffeestaff;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.coffeestaff.Bussiness.BussinessDistribution;
import com.example.coffeestaff.Bussiness.SignedInBussiness;
import com.example.coffeestaff.Data.DbHelper;
import com.example.coffeestaff.Data.DefaultConfig;
import com.example.coffeestaff.Data.SignedIns;
import com.example.coffeestaff.Data.Staffs;
import com.example.coffeestaff.Presentation.HomeActivity;
import com.example.coffeestaff.Presentation.LoginActivity;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // declare
        DbHelper db = new DbHelper(this);
        /*DefaultConfig.dropAllTable(db.getWritableDatabase()); // Xóa toàn bộ bảng
        db.onCreate(db.getReadableDatabase()); // khởi tạo lại csdl với bộ dữ liệu mặc định*/
        BussinessDistribution bd = new BussinessDistribution(this);
        SignedInBussiness signedInBussiness = bd.getSignedInBussiness();
        SignedIns signedIns = signedInBussiness.select();
        Timer timer = new Timer("setTimeout");
        timer.schedule(new TimerTask(){
            public void run(){
                Intent intent = new Intent(MainActivity.this, signedIns == null ? LoginActivity.class : HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        },1000);
    }
}