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

import com.example.coffeestaff.R;

public class HomeActivity extends AppCompatActivity {

    private Integer[] menuIcons = {
            R.drawable.ic_martini_glass_solid,
            R.drawable.ic_file_invoice_solid,
            R.drawable.ic_baseline_amp_stories_24,
            R.drawable.ic_right_from_bracket_solid
    };
    private String[] menuLabels = {
            "Đặt món",
            "Hóa đơn",
            "Danh sách bàn",
            "Đăng xuất"
    };
    private String[] menuIconBackgrounds = {
            "#16A8EA",
            "#C99451",
            "#713800",
            "#717DD5"
    };
    private Intent[] intents = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //
        intents = new Intent[]{
            new Intent(HomeActivity.this, ChooseTableActivity.class),
                    null,
                    null
        };
        GridView gdvMenu = findViewById(R.id.gdvMenu);
        GridViewAdapter adapter = new GridViewAdapter(menuIcons, menuLabels, menuIconBackgrounds);
        gdvMenu.setAdapter(adapter);
        gdvMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                HomeActivity.this.startActivity(intents[i]);
            }
        });
    }

    class GridViewAdapter extends BaseAdapter {
        private Integer[] icons = null;
        private String[] labels = null;
        private String[] iconBackgronuds = null;

        public GridViewAdapter(Integer[] _icons, String[] _labels, String[] _iconBackgrounds) {
            icons = _icons;
            labels = _labels;
            iconBackgronuds = _iconBackgrounds;
        }

        @Override
        public int getCount() {
            return icons.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        public Integer _getIcon(int i) {
            return icons[i];
        }

        public String _getBackground(int i){
            return iconBackgronuds[i];
        }

        public String _getLabel(int i) {
            return labels[i];
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
                convertView = View.inflate(viewGroup.getContext(), R.layout.layout_homemenu, null);
                ImageView imvIcon = (ImageView) convertView.findViewById(R.id.imvIcon);
                TextView txtLabel = (TextView) convertView.findViewById(R.id.txtLabel);
                imvIcon.setImageResource(_getIcon(i));
                Drawable background = getResources().getDrawable(R.drawable.menu_item_shape);
                DrawableCompat.setTint(background, Color.parseColor(_getBackground(i)));
                imvIcon.setBackground(background);
                txtLabel.setText(_getLabel(i));
            }
            return convertView;
        }
    }
}