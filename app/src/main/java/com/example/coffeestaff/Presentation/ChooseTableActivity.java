package com.example.coffeestaff.Presentation;

import androidx.activity.result.ActivityResultCallback;
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

import com.example.coffeestaff.Data.CommonModels.DrinkOrders;
import com.example.coffeestaff.Data.ModelHelper.BillDetailsHelper;
import com.example.coffeestaff.Data.ModelHelper.BillsHelper;
import com.example.coffeestaff.Data.ModelHelper.DrinksHelper;
import com.example.coffeestaff.Data.ModelHelper.SignedInHelper;
import com.example.coffeestaff.Data.ModelHelper.TablesHelper;
import com.example.coffeestaff.Data.Models.BillDetails;
import com.example.coffeestaff.Data.Models.Bills;
import com.example.coffeestaff.Data.Models.SignedIn;
import com.example.coffeestaff.Data.Models.Tables;
import com.example.coffeestaff.R;
import com.google.gson.Gson;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.function.Consumer;

public class ChooseTableActivity extends AppCompatActivity {
    private ArrayList<Tables> tables;
    private String colorServing = "#16a8ea";
    private String colorEmpty = "#849BA2";
    private Integer choosedTable = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choosetable);
        //
        TablesHelper tablesHelper = new TablesHelper(this);
        tables = tablesHelper.selectAll();
        GridViewAdapter adapter = new GridViewAdapter(tables);
        GridView gdvTable = findViewById(R.id.gdvTable);
        Button btnCancel = findViewById(R.id.btnCancel);
        //
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
                        SignedInHelper signedInHelper = new SignedInHelper(ChooseTableActivity.this);
                        BillsHelper billsHelper = new BillsHelper(ChooseTableActivity.this);
                        BillDetailsHelper billDetailsHelper = new BillDetailsHelper(ChooseTableActivity.this);
                        DrinksHelper drinksHelper = new DrinksHelper(ChooseTableActivity.this);
                        table.setStatus(1);
                        tables.get(choosedTable).setStatus(1);
                        adapter.update(tables);
                        SignedIn signedIn = signedInHelper.select();
                        Bills bill = new Bills(0, choosedTable, signedIn.getStaffId(), DateFormat.getDateTimeInstance().format(new Date()), 0);
                        Long billId = billsHelper.insert(bill);
                        tablesHelper.updateStatus(table);
                        for (int i = 0; i < result.size(); i++) {
                            DrinkOrders order = gson.fromJson(result.get(i), DrinkOrders.class);
                            BillDetails billDetail = new BillDetails(billId.intValue(), order.getDrinkId(), order.getAmount(), drinksHelper.select(order.getDrinkId()).getPrice());
                            billDetailsHelper.insert(billDetail);
                        }
                    }
                });
        //
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
        private ArrayList<Tables> _tables;

        public GridViewAdapter(ArrayList<Tables> tables) {
            _tables = tables;
        }

        public void update(ArrayList<Tables> tables) {
            _tables = tables;
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return _tables.size();
        }

        public int getId(int i) {
            return _tables.get(i).getId();
        }

        public int getStatus(int i) {
            return _tables.get(i).getStatus();
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
            TextView txtLabel = convertView.findViewById(R.id.txtLabel);
            TextView txtContainer = convertView.findViewById(R.id.txtContainer);
            if (getStatus(i) == 0) {
                txtContainer.setBackgroundColor(Color.parseColor(colorEmpty));
            } else txtContainer.setBackgroundColor(Color.parseColor(colorServing));
            txtLabel.setText(getId(i) + "");
            return convertView;
        }
    }
}