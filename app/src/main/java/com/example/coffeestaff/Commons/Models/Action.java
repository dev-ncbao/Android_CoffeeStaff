package com.example.coffeestaff.Commons.Models;

import android.content.DialogInterface;

import java.util.concurrent.Callable;

public class Action implements Callable<Void> {
    private DialogInterface dialogInterface;
    private int which;

    public Action(){}

    public Action(DialogInterface dialogInterface, int which) {
        this.dialogInterface = dialogInterface;
        this.which = which;
    }

    public DialogInterface getDialogInterface() {
        return dialogInterface;
    }

    public void setDialogInterface(DialogInterface dialogInterface) {
        this.dialogInterface = dialogInterface;
    }

    public int getWhich() {
        return which;
    }

    public void setWhich(int which) {
        this.which = which;
    }

    @Override
    public Void call() throws Exception {
        return null;
    }
}
