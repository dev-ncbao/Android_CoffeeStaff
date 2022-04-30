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

import com.example.coffeestaff.Commons.Models.Order;
import com.example.coffeestaff.Bussiness.DrinkBussiness;
import com.example.coffeestaff.Data.Drinks;
import com.example.coffeestaff.R;
import com.google.gson.Gson;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class ChooseDrinksActivity extends AppCompatActivity {
    private ArrayList<Drinks> _drinks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choosedrinks);
        //
        ListView lsvDrink = findViewById(R.id.lsvDrinks);
        Button btnConfirm = findViewById(R.id.btnConfirm);
        Button btnBack = findViewById(R.id.btnBack);
        //
        DrinkBussiness drinksHelper = new DrinkBussiness(this);
        _drinks = drinksHelper.selectAll();
        ListViewAdapter adapter = new ListViewAdapter(_drinks);
        lsvDrink.setAdapter(adapter);

        //
        btnBack.setOnClickListener(view -> ChooseDrinksActivity.this.finish());
        //
        btnConfirm.setOnClickListener(view -> {
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            ArrayList<Order> orders = adapter.getOrders();
            if (orders.size() > 0) {
                ArrayList<String> ordersSerialize = new ArrayList<>();
                Gson gson = new Gson();
                for (int i = 0; i < orders.size(); i++) {
                    ordersSerialize.add(gson.toJson(orders.get(i)));
                }
                bundle.putStringArrayList("orders", ordersSerialize);
                intent.putExtra("orders-result", bundle);
                setResult(RESULT_OK, intent);
                ChooseDrinksActivity.this.finish();
            }
            else Toast.makeText(ChooseDrinksActivity.this, "Số lượng order không hợp lệ", Toast.LENGTH_SHORT).show();
        });
    }

    class ListViewAdapter extends BaseAdapter {
        private ArrayList<Drinks> _drinks;
        private ArrayList<Integer> _amounts = new ArrayList<>();

        public ListViewAdapter(ArrayList<Drinks> drinks) {
            _drinks = drinks;
            for (int i = 0; i < _drinks.size(); i++) {
                _amounts.add(0);
            }
        }

        @Override
        public int getCount() {
            return _drinks.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        public String getDrinkName(int i) {
            return _drinks.get(i).getName();
        }

        public double getDrinkPrice(int i) {
            return _drinks.get(i).getPrice();
        }

        public Integer getPicture(int i) {
            return _drinks.get(i).getImage();
        }

        public ArrayList<Order> getOrders() {
            ArrayList<Order> orders = new ArrayList<>();
            for (int i = 0; i < _drinks.size(); i++) {
                if (_amounts.get(i) > 0)
                    orders.add(new Order(_drinks.get(i).getId(), _amounts.get(i)));
            }
            return orders;
        }

        private boolean decreasableOrder(int i) {
            return _amounts.get(i) > 0 ? true : false;
        }

        private void decreaseOrder(int i) {
            _amounts.set(i, _amounts.get(i) - 1);
            notifyDataSetChanged();
        }

        private void increaseOrder(int i) {
            _amounts.set(i, _amounts.get(i) + 1);
            notifyDataSetChanged();
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
            TextView txtDrinkName = convertView.findViewById(R.id.txtDrinkName);
            TextView txtDrinkPrice = convertView.findViewById(R.id.txtDrinkPrice);
            TextView txtAmount = convertView.findViewById(R.id.txtAmount);
            ImageView imvDrink = convertView.findViewById(R.id.imvDrink);
            ImageButton btnDecrease = convertView.findViewById(R.id.btnDecrease);
            ImageButton btnIncrease = convertView.findViewById(R.id.btnIncrease);
            //
            txtDrinkName.setText(getDrinkName(i));
            txtDrinkPrice.setText(NumberFormat.getCurrencyInstance(new Locale("vi", "VN")).format(getDrinkPrice(i)));
            txtAmount.setText(_amounts.get(i).toString());
            imvDrink.setImageResource(getPicture(i));
            btnDecrease.setOnClickListener(v -> {
                if (decreasableOrder(i)) decreaseOrder(i);
            });
            btnIncrease.setOnClickListener(v -> increaseOrder(i));
            return convertView;
        }
    }
}