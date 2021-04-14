package com.tpnet.patchbooheevip;

import android.widget.Toast;

public class ToastUtils {


    private static ToastUtils instance = new ToastUtils();

    private ToastUtils() {
    }

    public static ToastUtils getInstance() {
        return instance;
    }

    public void ShowToast(String text) {
        this.ShowToast(text, Toast.LENGTH_SHORT);
    }

    public void ShowToast(String text, int lenght) {
        if(Main.mContext != null){
            Toast.makeText(Main.mContext, text, lenght).show();
        }
    }

}
