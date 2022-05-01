package com.example.coffeestaff.Presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.DrawableCompat;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.coffeestaff.Bussiness.BussinessDistribution;
import com.example.coffeestaff.Bussiness.SignedInBussiness;
import com.example.coffeestaff.Bussiness.StaffBussiness;
import com.example.coffeestaff.Commons.Models.HomeMenuItem;
import com.example.coffeestaff.Data.SignedIns;
import com.example.coffeestaff.Data.Staffs;
import com.example.coffeestaff.R;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    private ArrayList<HomeMenuItem> menuItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        // initial datas
        menuItems.add(new HomeMenuItem(R.drawable.ic_martini_glass_solid, "Đặt món", "#16A8EA"));
        menuItems.add(new HomeMenuItem(R.drawable.ic_file_invoice_solid, "Hóa đơn", "#C99451"));
        menuItems.add(new HomeMenuItem(R.drawable.ic_baseline_amp_stories_24, "Bàn đang phục vụ", "#713800"));
        menuItems.add(new HomeMenuItem(R.drawable.ic_right_from_bracket_solid, "Đăng xuất", "#717DD5"));
        // declare
        BussinessDistribution bd = new BussinessDistribution(this);
        SignedInBussiness signedInBussiness = bd.getSignedInBussiness();
        StaffBussiness staffBussiness = bd.getStaffBussiness();
        // get views
        TextView txtWelcome = findViewById(R.id.txtWelcome);
        GridView gdvMenu = findViewById(R.id.gdvMenu);
        GridViewAdapter adapter = new GridViewAdapter(menuItems);
        gdvMenu.setAdapter(adapter);
        gdvMenu.setOnItemClickListener((adapterView, view, i, l) -> {
            Intent intent = null;
            switch (i) {
                case 0:
                    intent = new Intent(HomeActivity.this, ChooseTableActivity.class);
                    break;
                case 1:
                    intent = new Intent(HomeActivity.this, BillsActivity.class);
                    break;
                case 2:
                    intent = new Intent(HomeActivity.this, TablesBeingServedActivity.class);
                    break;
                case 3:
                    // remove user signed in
                    SignedIns signedIns = signedInBussiness.select();
                    signedInBussiness.delete(signedIns.getStaffId());
                    intent = new Intent(HomeActivity.this, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    break;
            }
            if (intent != null) startActivity(intent);
        });
        // set text welcome
        SignedIns signedIns = signedInBussiness.select();
        Staffs staff = staffBussiness.select(signedIns.getStaffId());
        txtWelcome.setText(txtWelcome.getText().toString() + staff.getName());
    }

    class GridViewAdapter extends BaseAdapter {
        private ArrayList<HomeMenuItem> menuItems;

        public GridViewAdapter(ArrayList<HomeMenuItem> menuItems) {
            this.menuItems = menuItems;
        }

        public Integer getIcon(int i) {
            return menuItems.get(i).getIcon();
        }

        public String getLabel(int i) {
            return menuItems.get(i).getLabel();
        }

        public String getBackgroundColor(int i) {
            return menuItems.get(i).getBackgroundColor();
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View convertView;
            if (view != null) convertView = view;
            else {
                convertView = View.inflate(viewGroup.getContext(), R.layout.layout_homemenu, null);
            }
            // get views
            ImageView imvIcon = convertView.findViewById(R.id.imvIcon);
            TextView txtLabel = convertView.findViewById(R.id.txtLabel);
            // update views properties
            Drawable background = getResources().getDrawable(R.drawable.menu_item_shape);
            DrawableCompat.setTint(background, Color.parseColor(getBackgroundColor(i)));
            imvIcon.setImageResource(getIcon(i));
            imvIcon.setBackground(background);

            txtLabel.setText(getLabel(i));

            return convertView;
        }

        @Override
        public int getCount() {
            return menuItems.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }
    }
}