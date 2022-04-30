package com.example.coffeestaff.Presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.coffeestaff.Bussiness.BillDetailBussiness;
import com.example.coffeestaff.Bussiness.BillBussiness;
import com.example.coffeestaff.Bussiness.DrinkBussiness;
import com.example.coffeestaff.Data.BillDetails;
import com.example.coffeestaff.Data.Drinks;
import com.example.coffeestaff.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class ServingActivity extends AppCompatActivity {
    private Integer tableId = -1;
    private ArrayList<Integer> drinksId = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servingacvitity);
        //
        Intent intent = getIntent();
        tableId = intent.getIntExtra("table", -1);
        Log.i("result-table", tableId.toString());
        ListView lsvOrdered = findViewById(R.id.lsvOrdered);
        BillBussiness billsHelper = new BillBussiness(this);
        Integer billId = billsHelper.selectBillId(tableId);
        BillDetailBussiness billDetailsHelper = new BillDetailBussiness(this);
        ArrayList<BillDetails> billDetails = billDetailsHelper.selectByBill(billId);
        ListViewAdapter adapter = new ListViewAdapter(billDetails);
        lsvOrdered.setAdapter(adapter);
        TextView txtTableId = findViewById(R.id.txtTableId);
        txtTableId.setText("Bàn " + tableId);
    }

    class ListViewAdapter extends BaseAdapter {
        private ArrayList<BillDetails> _billDetails;

        public ListViewAdapter(ArrayList<BillDetails> billDetails) {
            _billDetails = billDetails;
        }

        @Override
        public int getCount() {
            return _billDetails.size();
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
                TextView txtSTT = convertView.findViewById(R.id.txtSTT);
                TextView txtDrinkName = convertView.findViewById(R.id.txtDrinkName);
                TextView txtDrinkPrice = convertView.findViewById(R.id.txtDrinkPrice);
                TextView txtAmount = convertView.findViewById(R.id.txtAmount);
                ImageView imvDrink = convertView.findViewById(R.id.imvDrink);
                //
                DrinkBussiness drinksHelper = new DrinkBussiness(ServingActivity.this);
                Drinks drink = drinksHelper.select(_billDetails.get(i).getDrinksId());
                txtSTT.setText(i + 1 + "");
                txtDrinkName.setText(drink.getName());
                txtDrinkPrice.setText(NumberFormat.getCurrencyInstance(new Locale("vi", "VN")).format(drink.getPrice()));
                txtAmount.setText(_billDetails.get(i).getAmount().toString());
                imvDrink.setImageResource(drink.getImage());
            return convertView;
        }
    }
}