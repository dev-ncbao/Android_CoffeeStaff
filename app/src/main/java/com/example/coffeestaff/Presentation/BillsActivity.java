package com.example.coffeestaff.Presentation;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.coffeestaff.Bussiness.BillBussiness;
import com.example.coffeestaff.Bussiness.BillDetailBussiness;
import com.example.coffeestaff.Bussiness.BussinessDistribution;
import com.example.coffeestaff.Commons.Helpers.DateTime;
import com.example.coffeestaff.Data.Bills;
import com.example.coffeestaff.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class BillsActivity extends AppCompatActivity {
    private ArrayList<Bills> bills;
    private Calendar calendar = Calendar.getInstance(new Locale("vi", "VN"));
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bills);
        // declare
        BussinessDistribution bd = new BussinessDistribution(this);
        BillBussiness billsBussiness = bd.getBillBussiness();
        bills = billsBussiness.selectAllNotPaidYet(DateTime.convertToDate(calendar.getTime()));
        // get views
        ListView lsvBill = findViewById(R.id.lsvBill);
        Button btnBack = findViewById(R.id.btnBack);
        TextView txtEmpty = findViewById(R.id.txtEmpty);
        LinearLayout layoutBills = findViewById(R.id.layoutBills);
        Button btnNext = findViewById(R.id.btnNext);
        Button btnPrev = findViewById(R.id.btnPrev);
        TextView txtDate = findViewById(R.id.txtDate);
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
        // button next click event
        btnNext.setOnClickListener(view -> {
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            showDate(getDate(calendar.getTime()));
            updateBills(adapter);
            setVisibility(layoutBills, txtEmpty);
        });
        // button prev click event
        btnPrev.setOnClickListener(view -> {
            calendar.add(Calendar.DAY_OF_MONTH, -1);
            showDate(getDate(calendar.getTime()));
            updateBills(adapter);
            setVisibility(layoutBills, txtEmpty);
        });
        // click on date event
        txtDate.setOnClickListener(view ->{
            DatePickerDialog datePicker = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                Locale locale = new Locale("vi", "VN");
                Locale.setDefault(locale);
                datePicker = new DatePickerDialog(this);
                datePicker.updateDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePicker.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        calendar.set(year, month, day);
                        showDate(getDate(calendar.getTime()));
                        updateBills(adapter);
                        setVisibility(layoutBills, txtEmpty);
                    }
                });
                datePicker.show();
            }
        });
        // set date
        showDate(getDate(calendar.getTime()));
        //
        setVisibility(layoutBills, txtEmpty);
    }

    private void updateBills(ListViewAdapter adapter){
        BussinessDistribution bd = new BussinessDistribution(this);
        BillBussiness billsBussiness = bd.getBillBussiness();
        bills = billsBussiness.selectAllNotPaidYet(DateTime.convertToDate(calendar.getTime()));
        adapter.update(bills);
    }

    private void showDate(String date){
        TextView txtDate = findViewById(R.id.txtDate);
        txtDate.setText(date);
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

    private String getDate(Date date){
        String today = DateTime.convertToDate(new Date());
        String convertDate = DateTime.convertToDate(date);
        if(DateTime.compareDate(convertDate, today))
            return "Hôm nay";
        return convertDate;
    }

    class ListViewAdapter extends BaseAdapter {
        private ArrayList<Bills> bills;

        public ListViewAdapter(ArrayList<Bills> bills) {
            this.bills = bills;
        }

        public void update(ArrayList<Bills> bills) {
            this.bills = bills;
            notifyDataSetChanged();
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