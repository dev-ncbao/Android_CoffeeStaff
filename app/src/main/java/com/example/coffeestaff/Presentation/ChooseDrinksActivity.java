package com.example.coffeestaff.Presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coffeestaff.Bussiness.BussinessDistribution;
import com.example.coffeestaff.Commons.Models.Order;
import com.example.coffeestaff.Bussiness.DrinkBussiness;
import com.example.coffeestaff.Data.Drinks;
import com.example.coffeestaff.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class ChooseDrinksActivity extends AppCompatActivity {
    private ArrayList<Drinks> drinks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choosedrinks);
        // declare
        BussinessDistribution bussinessDistribution = new BussinessDistribution(this);
        DrinkBussiness drinkBussiness = bussinessDistribution.getDrinkBussiness();
        // get views
        ListView lsvDrink = findViewById(R.id.lsvDrinks);
        Button btnConfirm = findViewById(R.id.btnConfirm);
        Button btnBack = findViewById(R.id.btnBack);
        // get drinks & set list view adapter
        drinks = drinkBussiness.selectAll();
        ListViewAdapter adapter = new ListViewAdapter(drinks);
        lsvDrink.setAdapter(adapter);
        // button back click event
        btnBack.setOnClickListener(view -> ChooseDrinksActivity.this.finish());
        // button confirm click event
        btnConfirm.setOnClickListener(view -> {
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            ArrayList<Order> orders = adapter.getOrders();
            if (orders.size() > 0) {
                bundle.putSerializable("orders", orders);
                intent.putExtra("orders-result", bundle);
                setResult(RESULT_OK, intent);
                ChooseDrinksActivity.this.finish();
            }
            else Toast.makeText(ChooseDrinksActivity.this, "Bạn chưa chọn món", Toast.LENGTH_SHORT).show();
        });
    }

    class ListViewAdapter extends BaseAdapter {
        private ArrayList<Drinks> drinks;
        private ArrayList<Integer> amounts = new ArrayList<>();

        public ListViewAdapter(ArrayList<Drinks> drinks) {
            this.drinks = drinks;
            for (int i = 0; i < drinks.size(); i++) {
                amounts.add(0);
            }
        }

        public String getDrinkName(int i) {
            return drinks.get(i).getName();
        }

        public double getDrinkPrice(int i) {
            return drinks.get(i).getPrice();
        }

        public Integer getPicture(int i) {
            return drinks.get(i).getImage();
        }

        public ArrayList<Order> getOrders() {
            ArrayList<Order> orders = new ArrayList<>();
            for (int i = 0; i < drinks.size(); i++) {
                if (amounts.get(i) > 0)
                    orders.add(new Order(drinks.get(i).getId(), amounts.get(i)));
            }
            return orders;
        }

        private boolean decreasableOrder(int i) {
            return amounts.get(i) > 0 ? true : false;
        }

        private void decreaseOrder(int i) {
            amounts.set(i, amounts.get(i) - 1);
            notifyDataSetChanged();
        }

        private void increaseOrder(int i) {
            amounts.set(i, amounts.get(i) + 1);
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return drinks.size();
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
                convertView = View.inflate(viewGroup.getContext(), R.layout.layout_drinklist, null);
            }
            // get views
            TextView txtDrinkName = convertView.findViewById(R.id.txtDrinkName);
            TextView txtDrinkPrice = convertView.findViewById(R.id.txtDrinkPrice);
            TextView txtAmount = convertView.findViewById(R.id.txtAmount);
            ImageView imvDrink = convertView.findViewById(R.id.imvDrink);
            ImageButton btnDecrease = convertView.findViewById(R.id.btnDecrease);
            ImageButton btnIncrease = convertView.findViewById(R.id.btnIncrease);
            //
            txtDrinkName.setText(getDrinkName(i));

            txtDrinkPrice.setText(NumberFormat.getCurrencyInstance(new Locale("vi", "VN")).format(getDrinkPrice(i)));

            txtAmount.setText(amounts.get(i).toString());

            imvDrink.setImageResource(getPicture(i));

            btnDecrease.setOnClickListener(v -> {
                if (decreasableOrder(i)) decreaseOrder(i);
            });

            btnIncrease.setOnClickListener(v -> increaseOrder(i));
            return convertView;
        }
    }
}