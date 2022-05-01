package com.example.coffeestaff.Presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.coffeestaff.Bussiness.BussinessDistribution;
import com.example.coffeestaff.Bussiness.SignedInBussiness;
import com.example.coffeestaff.Bussiness.StaffBussiness;
import com.example.coffeestaff.Data.SignedIns;
import com.example.coffeestaff.Data.Staffs;
import com.example.coffeestaff.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // get views
        EditText edtUsername = findViewById(R.id.edtUsername);
        EditText edtPassword = findViewById(R.id.edtPassword);
        Button btnLogin = findViewById(R.id.btnLogin);
        // declare
        BussinessDistribution bd = new BussinessDistribution(this);
        StaffBussiness staffBussiness = bd.getStaffBussiness();
        SignedInBussiness signedInBussiness = bd.getSignedInBussiness();
        // button login click event
        btnLogin.setOnClickListener(view -> {
            Staffs staff = staffBussiness.select(edtUsername.getText().toString(), edtPassword.getText().toString());
            if(staff == null) Toast.makeText(this, "Tài khoản hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
            else {
                SignedIns signedIn = new SignedIns(staff.getId());
                signedInBussiness.insert(signedIn);
                Intent intent = new Intent(this, HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }
}