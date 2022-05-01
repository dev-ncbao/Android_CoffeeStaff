package com.example.coffeestaff.Presentation;

import static com.example.coffeestaff.Commons.Helpers.Calculate.calculateTotalAmount;
import static com.example.coffeestaff.Commons.Helpers.Calculate.calculateTotalPrice;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
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

import com.example.coffeestaff.Bussiness.BillDetailBussiness;
import com.example.coffeestaff.Bussiness.BillBussiness;
import com.example.coffeestaff.Bussiness.BussinessDistribution;
import com.example.coffeestaff.Bussiness.DrinkBussiness;
import com.example.coffeestaff.Bussiness.TableBussiness;
import com.example.coffeestaff.Commons.Helpers.Generate;
import com.example.coffeestaff.Commons.Models.Action;
import com.example.coffeestaff.Data.BillDetails;
import com.example.coffeestaff.Data.Bills;
import com.example.coffeestaff.Data.Drinks;
import com.example.coffeestaff.Data.Tables;
import com.example.coffeestaff.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.Callable;

public class ServingDetailActivity extends AppCompatActivity {
    private Integer tableId = -1;
    private ArrayList<Integer> drinksId = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servingacvitity);
        // receive data
        Intent intent = getIntent();
        tableId = intent.getIntExtra("table", -1);
        // get views
        ListView lsvOrdered = findViewById(R.id.lsvOrdered);
        TextView txtTableId = findViewById(R.id.txtTableId);
        Button btnBack = findViewById(R.id.btnBack);
        TextView txtAmount = findViewById(R.id.txtAmount);
        TextView txtTotalPrice = findViewById(R.id.txtTotalPrice);
        Button btnCalculate = findViewById(R.id.btnCalculate);
        // declare
        BussinessDistribution bussinessDistribution = new BussinessDistribution(this);
        BillBussiness billsBussiness = bussinessDistribution.getBillBussiness();
        TableBussiness tableBussiness = bussinessDistribution.getTableBussiness();
        //
        Integer billId = billsBussiness.selectBillId(tableId);
        BillDetailBussiness billDetailsBussiness = new BillDetailBussiness(this);
        ArrayList<BillDetails> billDetails = billDetailsBussiness.selectByBill(billId);
        // set list view adapter
        ListViewAdapter adapter = new ListViewAdapter(billDetails);
        lsvOrdered.setAdapter(adapter);
        //
        txtTableId.setText("Bàn " + tableId);
        // button back click event
        btnBack.setOnClickListener(view -> {
            ServingDetailActivity.this.finish();
        });
        // button calculate click event
        btnCalculate.setOnClickListener(view -> {
            AlertDialog dialog = Generate.buildAlertDialog(this, "Xác nhận", "Tính tiền và xuất hóa đơn bàn " + tableId, "Trở lại", "Xác nhận",
                    new Action() {
                        @Override
                        public Void call() throws Exception {
                            DialogInterface dialogInterface = this.getDialogInterface();
                            dialogInterface.cancel();
                            return super.call();
                        }
                    },
                    new Action() {
                        @Override
                        public Void call() throws Exception {
                            Tables table = tableBussiness.select(tableId);
                            table.setStatus(0);
                            tableBussiness.updateStatus(table);

                            Bills bill = billsBussiness.select(billId);
                            billsBussiness.updatePaid(bill);
                            setResult(RESULT_OK);
                            DialogInterface dialogInterface = this.getDialogInterface();
                            dialogInterface.cancel();
                            ServingDetailActivity.this.finish();
                            return super.call();
                        }
                    });
            dialog.show();
        });
        // set total price
        txtTotalPrice.setText(NumberFormat.getCurrencyInstance(new Locale("vi", "VN")).format(calculateTotalPrice(billDetails)));
        // set total amount
        txtAmount.setText(calculateTotalAmount(billDetails).toString());
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
            BussinessDistribution bussinessDistribution = new BussinessDistribution(ServingDetailActivity.this);
            DrinkBussiness drinkBussiness = bussinessDistribution.getDrinkBussiness();
            Drinks drink = drinkBussiness.select(billDetails.get(i).getDrinksId());
            //
            txtSTT.setText(i + 1 + "");

            txtDrinkName.setText(drink.getName());

            txtDrinkPrice.setText(NumberFormat.getCurrencyInstance(new Locale("vi", "VN")).format(drink.getPrice()));

            txtAmount.setText(billDetails.get(i).getAmount().toString());

            imvDrink.setImageResource(drink.getImage());
            return convertView;
        }
    }
}