package com.example.coffeestaff.Presentation;

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

import com.example.coffeestaff.Bussiness.TableBussiness;
import com.example.coffeestaff.Data.Tables;
import com.example.coffeestaff.R;

import java.util.ArrayList;

public class TablesBeingServedActivity extends AppCompatActivity {
    private ArrayList<Tables> tables;
    private String colorServing = "#16a8ea";
    private Integer choosedTable = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablesbeingserved);
        //
        TableBussiness tablesHelper = new TableBussiness(this);
        tables = tablesHelper.selectBeingServed();
        GridViewAdapter adapter = new GridViewAdapter(tables);
        GridView gdvTable = findViewById(R.id.gdvTable);
        TextView txtEmpty = findViewById(R.id.txtEmpty);
        Button btnCancel = findViewById(R.id.btnCancel);
        if(tables.size() > 0) {
            txtEmpty.setVisibility(View.GONE);
            gdvTable.setVisibility(View.VISIBLE);
        }
        //
        ActivityResultLauncher<Intent> showDetailLauncher = registerForActivityResult(
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
                    Log.i("result-calculate", result.toString());
                });
        //
        gdvTable.setAdapter(adapter);
        gdvTable.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(TablesBeingServedActivity.this, ServingActivity.class);
                intent.putExtra("table", tables.get(i).getId());
                showDetailLauncher.launch(intent);
            }
        });
        //
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TablesBeingServedActivity.this.finish();
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

        public Integer getId(int i) {
            return _tables.get(i).getId();
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
                convertView = View.inflate(viewGroup.getContext(), R.layout.layout_tablebeingservedlist, null);
            }
            TextView txtLabel = convertView.findViewById(R.id.txtLabel);
            TextView txtContainer = convertView.findViewById(R.id.txtContainer);
            txtContainer.setBackgroundColor(Color.parseColor(colorServing));
            txtLabel.setText(getId(i).toString());
            return convertView;
        }
    }
}