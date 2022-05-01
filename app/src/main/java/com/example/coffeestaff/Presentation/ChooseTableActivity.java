package com.example.coffeestaff.Presentation;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coffeestaff.Bussiness.BussinessDistribution;
import com.example.coffeestaff.Commons.Helpers.ConvertDateTime;
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

import java.io.Serializable;
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
        // get views
        GridView gdvTable = findViewById(R.id.gdvTable);
        Button btnCancel = findViewById(R.id.btnCancel);
        // set grid view adapter
        tables = tableBussiness.selectAll();
        GridViewAdapter adapter = new GridViewAdapter(tables);
        // register activity for result
        ActivityResultLauncher<Intent> chooseDrinksLauncher = registerForChooseDrink(adapter);
        ActivityResultLauncher<Intent> servingDetailLauncher = registerForServingDetail(adapter);
        gdvTable.setAdapter(adapter);
        gdvTable.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                choosedTable = i;
                if (tables.get(i).getStatus() == 0) {
                    Intent intent = new Intent(ChooseTableActivity.this, ChooseDrinksActivity.class);
                    chooseDrinksLauncher.launch(intent);
                } else {
                    Intent intent = new Intent(ChooseTableActivity.this, ServingDetailActivity.class);
                    intent.putExtra("table", tables.get(i).getId());
                    servingDetailLauncher.launch(intent);
                }
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

    private ActivityResultLauncher<Intent> registerForServingDetail(GridViewAdapter adapter){
        ActivityResultLauncher<Intent> servingDetailLauncher = registerForActivityResult(
                new ActivityResultContract<Intent, Integer>() {
                    @NonNull
                    @Override
                    public Intent createIntent(@NonNull Context context, Intent input) {
                        return input;
                    }

                    @Override
                    public Integer parseResult(int resultCode, @Nullable Intent intent) {
                        return resultCode;
                    }
                },
                result -> {
                    if (result == RESULT_OK)
                    {
                        tables.get(choosedTable).setStatus(0);
                        adapter.update(tables);
                    }
                });
        return servingDetailLauncher;
    }

    private ActivityResultLauncher<Intent> registerForChooseDrink(GridViewAdapter adapter) {
        ActivityResultLauncher<Intent> launcher = registerForActivityResult(
                new ActivityResultContract<Intent, Serializable>() {
                    @NonNull
                    @Override
                    public Intent createIntent(@NonNull Context context, Intent input) {
                        return input;
                    }

                    @Override
                    public Serializable parseResult(int resultCode, @Nullable Intent intent) {
                        switch (resultCode) {
                            case RESULT_CANCELED:
                                return null;
                            case RESULT_OK: {
                                Bundle bundle = intent.getBundleExtra("orders-result");
                                return bundle.getSerializable("orders");
                            }
                            default:
                                return null;
                        }
                    }
                },
                result -> {
                    if (result != null) {
                        ArrayList<Order> orders = (ArrayList<Order>) result;
                        BussinessDistribution bussinessDistribution = new BussinessDistribution(ChooseTableActivity.this);
                        SignedInBussiness signedInBussiness = bussinessDistribution.getSignedInBussiness();
                        BillBussiness billsBussiness = bussinessDistribution.getBillBussiness();
                        BillDetailBussiness billDetailsBussiness = bussinessDistribution.getBillDetailBussiness();
                        DrinkBussiness drinksBussiness = bussinessDistribution.getDrinkBussiness();
                        TableBussiness tableBussiness = bussinessDistribution.getTableBussiness();
                        SignedIns signedIn = signedInBussiness.select();
                        tables.get(choosedTable).setStatus(1);
                        // update view
                        adapter.update(tables);
                        // insert new bill
                        Bills bill = new Bills(0, tables.get(choosedTable).getId(), signedIn.getStaffId(), ConvertDateTime.convert(new Date()), 0);
                        Long billId = billsBussiness.insert(bill);
                        // update table status
                        tableBussiness.updateStatus(tables.get(choosedTable));
                        // insert bill detail
                        for (int i = 0; i < orders.size(); i++) {
                            Order order = orders.get(i);
                            BillDetails billDetail = new BillDetails(billId.intValue(), order.getDrinkId(), order.getAmount(), drinksBussiness.select(order.getDrinkId()).getPrice());
                            billDetailsBussiness.insert(billDetail);
                        }
                    }
                });
        return launcher;
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

        public Integer getStatus(int i) {
            return tables.get(i).getStatus();
        }

        public Integer getId(int i) {
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
                txtContainer.setBackgroundColor(getResources().getColor(R.color.grey2));
            } else txtContainer.setBackgroundColor(getResources().getColor(R.color.primary));
            txtLabel.setText(getId(i).toString());
            return convertView;
        }
    }
}