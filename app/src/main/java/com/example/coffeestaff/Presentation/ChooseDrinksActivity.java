package com.example.coffeestaff.Presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.coffeestaff.R;

public class ChooseDrinksActivity extends AppCompatActivity {
    private String[] drinks = {
            "Cà phê",
            "Cà phê sữa",
            "Trà đường",
            "Bạc xĩu"
    };
    private Integer[] prices = {
            12000, 15000, 10000, 15000
    };
    private Integer[] pictures = {
            R.drawable.cafe,
            R.drawable.cafe_sua,
            R.drawable.tra_duong,
            R.drawable.bac_xiu
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choosedrinks);
        //
        ListView lsvDrink = findViewById(R.id.lsvDrinks);
        ListViewAdapter adapter = new ListViewAdapter(drinks, prices, pictures);
        lsvDrink.setAdapter(adapter);
    }

    class ListViewAdapter extends BaseAdapter {
        private String[] drinks = null;
        private Integer[] prices = null;
        private Integer[] pictures = null;

        public ListViewAdapter(String[] _drinks, Integer[] _prices, Integer[] _pictures) {
            drinks = _drinks;
            prices = _prices;
            pictures = _pictures;
        }

        @Override
        public int getCount() {
            return drinks.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        public String getDrinkName(int i){
            return drinks[i];
        }

        public Integer getDrinkPrice(int i){
            return prices[i];
        }

        public Integer getPicture(int i){
            return pictures[i];
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
                //TextView txtSTT = (TextView)convertView.findViewById(R.id.txtSTT);
                TextView txtDrinkName = (TextView)convertView.findViewById(R.id.txtDrinkName);
                TextView txtDrinkPrice = (TextView)convertView.findViewById(R.id.txtDrinkPrice);
                TextView txtAmount = (TextView)convertView.findViewById(R.id.txtAmount);
                ImageView imvDrink = (ImageView) convertView.findViewById(R.id.imvDrink);
                ImageButton btnDecrease = (ImageButton) convertView.findViewById(R.id.btnDecrease);
                ImageButton btnIncrease = (ImageButton) convertView.findViewById(R.id.btnIncrease);
                //
                //txtSTT.setText(i + 1 + "");
                txtDrinkName.setText(getDrinkName(i));
                txtDrinkPrice.setText(getDrinkPrice(i) + "đ");
                txtAmount.setText(0 + "");
                imvDrink.setImageResource(getPicture(i));
            }
            return convertView;
        }
    }
}