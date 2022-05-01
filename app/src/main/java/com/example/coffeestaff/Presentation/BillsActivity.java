package com.example.coffeestaff.Presentation;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.coffeestaff.Bussiness.BillBussiness;
import com.example.coffeestaff.Bussiness.BillDetailBussiness;
import com.example.coffeestaff.Bussiness.BussinessDistribution;
import com.example.coffeestaff.Data.Bills;
import com.example.coffeestaff.R;

import java.util.ArrayList;

public class BillsActivity extends AppCompatActivity {
    private ArrayList<Bills> bills;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bills);
        // declare
        BussinessDistribution bd = new BussinessDistribution(this);
        BillBussiness billsBussiness = bd.getBillBussiness();
        bills = billsBussiness.selectAllNotPaidYet();
        // get views
        ListView lsvBill = findViewById(R.id.lsvBill);
        Button btnBack = findViewById(R.id.btnBack);
        TextView txtEmpty = findViewById(R.id.txtEmpty);
        LinearLayout layoutBills = findViewById(R.id.layoutBills);
        // set list view adapter
        ListViewAdapter adapter = new ListViewAdapter(bills);
        lsvBill.setAdapter(adapter);
        lsvBill.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(BillsActivity.this, BillDetailActivity.class);
                intent.putExtra("billId", bills.get(i).getId());
                startActivity(intent);
            }
        });
        // button back click event
        btnBack.setOnClickListener(view -> {
            this.finish();
        });
        //
        setVisibility(layoutBills, txtEmpty);
    }

    private void setVisibility(LinearLayout layoutBills, TextView txtEmpty) {
        if (bills.size() > 0) {
            txtEmpty.setVisibility(View.GONE);
            layoutBills.setVisibility(View.VISIBLE);
        }
        else{
            txtEmpty.setVisibility(View.VISIBLE);
            layoutBills.setVisibility(View.GONE);
        }
    }

    class ListViewAdapter extends BaseAdapter {
        private ArrayList<Bills> bills;

        public ListViewAdapter(ArrayList<Bills> bills) {
            this.bills = bills;
        }

        @Override
        public int getCount() {
            return bills.size();
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
                convertView = view.inflate(viewGroup.getContext(), R.layout.layout_billlist, null);
            }
            // get views
            TextView txtBillId = convertView.findViewById(R.id.txtBillId);
            TextView txtDateCreated = convertView.findViewById(R.id.txtDateCreated);
            TextView txtTimeCreated = convertView.findViewById(R.id.txtTimeCreated);
            //
            txtBillId.setText(bills.get(i).getId().toString());

            String[] dateTimeCreated = bills.get(i).getTimeCreated().split("-");
            txtDateCreated.setText(dateTimeCreated[0].trim());
            txtTimeCreated.setText(dateTimeCreated[1].trim());
            return convertView;
        }
    }
}