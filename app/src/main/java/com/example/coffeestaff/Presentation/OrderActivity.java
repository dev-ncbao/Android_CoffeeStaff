package com.example.coffeestaff.Presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.example.coffeestaff.R;

public class OrderActivity extends AppCompatActivity {
    private Integer[] tableIds = {
            1,2,3,4,5,6,7,8,9,10
    };

    private String colorServing = "#16a8ea";
    private String colorEmpty = "#849BA2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        //
        GridView gdvTable = findViewById(R.id.gdvTable);
        GridViewAdapter adapter = new GridViewAdapter(tableIds);
        gdvTable.setAdapter(adapter);
        gdvTable.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
    }

    class GridViewAdapter extends BaseAdapter {
        private Integer[] tableIds;

        public GridViewAdapter(Integer[] _tableIds){
            tableIds = _tableIds;
        }

        @Override
        public int getCount() {
            return tableIds.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        public Integer getId(int i){
            return tableIds[i];
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View convertView;
            if(view != null) convertView = view;
            else {
                convertView = View.inflate(viewGroup.getContext(), R.layout.layout_ordertable, null);
                TextView txtLabel = (TextView)convertView.findViewById(R.id.txtLabel);
                txtLabel.setText(getId(i).toString());
            }
            return convertView;
        }
    }
}