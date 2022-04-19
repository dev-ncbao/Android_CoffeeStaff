package com.example.coffeestaff;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.coffeestaff.Presentation.ChooseDrinksActivity;
import com.example.coffeestaff.Presentation.HomeActivity;
import com.example.coffeestaff.Presentation.LoginActivity;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        Timer timer = new Timer("setTimeout");
        timer.schedule(new TimerTask(){
            public void run(){
                Intent intent = new Intent(MainActivity.this, ChooseDrinksActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        },1000);
    }
}