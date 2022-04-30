package com.example.coffeestaff.Commons.Models;

import android.content.Intent;

import com.example.coffeestaff.R;

public class HomeMenuItem {
    private Integer icon;
    private String label;
    private String backgroundColor;

    public HomeMenuItem(Integer icon, String label, String backgroundColor) {
        this.icon = icon;
        this.label = label;
        this.backgroundColor = backgroundColor;
    }

    public Integer getIcon() {
        return icon;
    }

    public void setIcon(Integer icon) {
        this.icon = icon;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }
}
