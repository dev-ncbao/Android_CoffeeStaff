package com.example.coffeestaff.Presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.coffeestaff.Bussiness.BillBussiness;
import com.example.coffeestaff.Bussiness.BillDetailBussiness;
import com.example.coffeestaff.Bussiness.BussinessDistribution;
import com.example.coffeestaff.Bussiness.DrinkBussiness;
import com.example.coffeestaff.Bussiness.StaffBussiness;
import com.example.coffeestaff.Bussiness.TableBussiness;
import com.example.coffeestaff.Commons.Helpers.Calculate;
import com.example.coffeestaff.Commons.Helpers.ConvertDateTime;
import com.example.coffeestaff.Data.BillDetails;
import com.example.coffeestaff.Data.Bills;
import com.example.coffeestaff.Data.Drinks;
import com.example.coffeestaff.Data.Staffs;
import com.example.coffeestaff.Data.Tables;
import com.example.coffeestaff.R;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class BillDetailActivity extends AppCompatActivity {
    private ArrayList<BillDetails> billDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billdetail);
        // receive data
        Intent intent = getIntent();
        Integer billId = intent.getIntExtra("billId", -1);
        // declare
        BussinessDistribution bd = new BussinessDistribution(this);
        DrinkBussiness drinkBussiness = bd.getDrinkBussiness();
        BillDetailBussiness billDetailBussiness = bd.getBillDetailBussiness();
        StaffBussiness staffBussiness = bd.getStaffBussiness();
        TableBussiness tableBussiness = bd.getTableBussiness();
        BillBussiness billBussiness = bd.getBillBussiness();
        Bills bill = billBussiness.select(billId.intValue());
        Staffs staff = staffBussiness.select(bill.getStaffId().intValue());
        Tables table = tableBussiness.select(bill.getTableId().intValue());
        billDetails = billDetailBussiness.selectByBill(billId.intValue());
        // get views
        TextView txtBillId = findViewById(R.id.txtBillId);
        TextView txtTableId = findViewById(R.id.txtTableId);
        TextView txtTimeCreated = findViewById(R.id.txtTimeCreated);
        ListView lsvOrdered = findViewById(R.id.lsvOrdered);
        TextView txtStaff = findViewById(R.id.txtStaff);
        TextView txtAmount = findViewById(R.id.txtAmount);
        TextView txtTotalPrice = findViewById(R.id.txtTotalPrice);
        Button btnBack = findViewById(R.id.btnBack);
        // set list view adapter
        ListViewAdapter adapter = new ListViewAdapter(billDetails);
        lsvOrdered.setAdapter(adapter);
        // set total amount of drinks
        txtAmount.setText(Calculate.calculateTotalAmount(billDetails).toString());
        // set total price
        txtTotalPrice.setText(NumberFormat.getCurrencyInstance(new Locale("vi", "VN")).format(Calculate.calculateTotalPrice(billDetails)));
        // set staff name
        txtStaff.setText(String.format("%s (ID: %d)", staff.getName(), staff.getId()));
        // set bill id
        txtBillId.setText(String.format("%s%d", txtBillId.getText(), bill.getId()));
        // set table
        txtTableId.setText(table.getId().toString());
        // button back click event
        btnBack.setOnClickListener(view -> {
            this.finish();
        });
        // set time created
        txtTimeCreated.setText(bill.getTimeCreated());
    }

    class ListViewAdapter extends BaseAdapter {
        private ArrayList<BillDetails> billDetails;

        public ListViewAdapter(ArrayList<BillDetails> billDetails) {
            this.billDetails = billDetails;
        }

        @Override
        public int getCount() {
            return billDetails.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View convertView;
            if (view != null) convertView = view;
            else {
                convertView = View.inflate(viewGroup.getContext(), R.layout.layout_orderedlist, null);
            }
            // get views
            TextView txtSTT = convertView.findViewById(R.id.txtSTT);
            TextView txtDrinkName = convertView.findViewById(R.id.txtDrinkName);
            TextView txtDrinkPrice = convertView.findViewById(R.id.txtDrinkPrice);
            TextView txtAmount = convertView.findViewById(R.id.txtAmount);
            ImageView imvDrink = convertView.findViewById(R.id.imvDrink);
            //
            BussinessDistribution bussinessDistribution = new BussinessDistribution(BillDetailActivity.this);
            DrinkBussiness drinkBussiness = bussinessDistribution.getDrinkBussiness();
            BillDetails billDetail = billDetails.get(i);
            Drinks drink = drinkBussiness.select(billDetail.getDrinksId());
            //
            txtSTT.setText(i + 1 + "");

            txtDrinkName.setText(drink.getName());

            txtDrinkPrice.setText(NumberFormat.getCurrencyInstance(new Locale("vi", "VN")).format(billDetail.getPrice()));

            txtAmount.setText(billDetail.getAmount().toString());

            imvDrink.setImageResource(drink.getImage());
            return convertView;
        }
    }
}