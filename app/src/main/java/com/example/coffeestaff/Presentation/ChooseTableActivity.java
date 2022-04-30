package com.example.coffeestaff.Presentation;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coffeestaff.Bussiness.BussinessDistribution;
import com.example.coffeestaff.Commons.Models.Order;
import com.example.coffeestaff.Bussiness.BillDetailBussiness;
import com.example.coffeestaff.Bussiness.BillBussiness;
import com.example.coffeestaff.Bussiness.DrinkBussiness;
import com.example.coffeestaff.Bussiness.SignedInBussiness;
import com.example.coffeestaff.Bussiness.TableBussiness;
import com.example.coffeestaff.Data.BillDetails;
import com.example.coffeestaff.Data.Bills;
import com.example.coffeestaff.Data.SignedIns;
import com.example.coffeestaff.Data.Tables;
import com.example.coffeestaff.R;
import com.google.gson.Gson;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ChooseTableActivity extends AppCompatActivity {
    private ArrayList<Tables> tables;
    private Integer choosedTable = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choosetable);
        // declare
        BussinessDistribution bussinessDistribution = new BussinessDistribution(this);
        TableBussiness tableBussiness = bussinessDistribution.getTableBussiness();
        // register activity for result
        ActivityResultLauncher<Intent> chooseDrinkLauncher = registerForActivityResult(
                new ActivityResultContract<Intent, ArrayList<String>>() {
                    @NonNull
                    @Override
                    public Intent createIntent(@NonNull Context context, Intent input) {
                        return input;
                    }

                    @Override
                    public ArrayList<String> parseResult(int resultCode, @Nullable Intent intent) {
                        switch (resultCode) {
                            case RESULT_CANCELED:
                                return null;
                            case RESULT_OK: {
                                Bundle bundle = intent.getBundleExtra("orders-result");
                                return bundle.getStringArrayList("orders");
                            }
                            default:
                                return null;
                        }
                    }
                },
                result -> {
                    if (result != null) {
                        Gson gson = new Gson();
                        Tables table = tables.get(choosedTable);
                        SignedInBussiness signedInHelper = new SignedInBussiness(ChooseTableActivity.this);
                        BillBussiness billsHelper = new BillBussiness(ChooseTableActivity.this);
                        BillDetailBussiness billDetailsHelper = new BillDetailBussiness(ChooseTableActivity.this);
                        DrinkBussiness drinksHelper = new DrinkBussiness(ChooseTableActivity.this);
                        table.setStatus(1);
                        tables.get(choosedTable).setStatus(1);
                        adapter.update(tables);
                        SignedIns signedIn = signedInHelper.select();
                        Bills bill = new Bills(0, table.getId(), signedIn.getStaffId(), DateFormat.getDateTimeInstance().format(new Date()), 0);
                        Long billId = billsHelper.insert(bill);
                        tableBussiness.updateStatus(table);
                        Log.i("result-bill", billId.toString());
                        for (int i = 0; i < result.size(); i++) {
                            Order order = gson.fromJson(result.get(i), Order.class);
                            BillDetails billDetail = new BillDetails(billId.intValue(), order.getDrinkId(), order.getAmount(), drinksHelper.select(order.getDrinkId()).getPrice());
                            billDetailsHelper.insert(billDetail);
                        }
                    }
                });
        // get views
        GridView gdvTable = findViewById(R.id.gdvTable);
        Button btnCancel = findViewById(R.id.btnCancel);
        // set grid view adapter
        tables = tableBussiness.selectAll();
        GridViewAdapter adapter = new GridViewAdapter(tables);
        gdvTable.setAdapter(adapter);
        gdvTable.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (tables.get(i).getStatus() == 0) {
                    choosedTable = i;
                    Intent intent = new Intent(ChooseTableActivity.this, ChooseDrinksActivity.class);
                    chooseDrinkLauncher.launch(intent);
                } else
                    Toast.makeText(ChooseTableActivity.this, String.format("%s đang phục vụ khách hàng", tables.get(i).getName()), Toast.LENGTH_SHORT).show();
            }
        });
        //
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChooseTableActivity.this.finish();
            }
        });
    }

    class GridViewAdapter extends BaseAdapter {
        private ArrayList<Tables> tables;

        public GridViewAdapter(ArrayList<Tables> tables) {
            this.tables = tables;
        }

        public void update(ArrayList<Tables> tables) {
            this.tables = tables;
            notifyDataSetChanged();
        }

        public Integer getStatus(int i){
            return tables.get(i).getStatus();
        }

        public Integer getId(int i){
            return tables.get(i).getId();
        }

        @Override
        public int getCount() {
            return tables.size();
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
                convertView = View.inflate(viewGroup.getContext(), R.layout.layout_tablelist, null);
            }
            // get views
            TextView txtLabel = convertView.findViewById(R.id.txtLabel);
            TextView txtContainer = convertView.findViewById(R.id.txtContainer);
            // update views properties
            if (getStatus(i) == 0) {
                txtContainer.setBackgroundColor(getResources().getColor(R.color.grey1));
            } else txtContainer.setBackgroundColor(getResources().getColor(R.color.primary));
            txtLabel.setText(getId(i).toString());
            return convertView;
        }
    }
}