package com.example.coffeestaff.Commons.Helpers;

import android.content.Context;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;

import com.example.coffeestaff.Commons.Models.Action;

import java.util.concurrent.Callable;

public class Generate {
    public static AlertDialog buildAlertDialog(Context context, String title, String message, String negative, String positive, Action negativeAction, Action positiveAction){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setNegativeButton(negative, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                negativeAction.setDialogInterface(dialogInterface);
                negativeAction.setWhich(i);
                try {
                    negativeAction.call();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        builder.setPositiveButton(positive, ( dialogInterface, i ) -> {
            positiveAction.setDialogInterface(dialogInterface);
            positiveAction.setWhich(i);
            try {
                positiveAction.call();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return builder.create();
    }

    public static AlertDialog buildAlertDialog(Context context, String title, String message, String positive, Action positiveAction){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(positive, ( dialogInterface, i ) -> {
            positiveAction.setDialogInterface(dialogInterface);
            positiveAction.setWhich(i);
            try {
                positiveAction.call();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return builder.create();
    }
}
