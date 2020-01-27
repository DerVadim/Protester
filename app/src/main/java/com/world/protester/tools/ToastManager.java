package com.world.protester.tools;

import android.content.Context;
import android.widget.Toast;

public class ToastManager {

    private static ToastManager instance;

    private ToastManager() {
    }

    public static ToastManager getInstance() {
        if (instance == null) {
            instance = new ToastManager();
        }
        return instance;
    }

    public void showToastById(int id, Context context){
        this.showToast(context.getResources().getString(id),context);
    }

    public void showToast(String messege,Context context){
        Toast.makeText(context,messege,Toast.LENGTH_LONG).show();
    }

}
