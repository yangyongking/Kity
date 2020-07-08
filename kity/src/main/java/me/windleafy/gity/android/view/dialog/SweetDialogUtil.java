package me.windleafy.gity.android.view.dialog;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;

import cn.pedant.SweetAlert.SweetAlertDialog;


public class SweetDialogUtil {


    @SuppressLint("StaticFieldLeak")
    private static SweetAlertDialog dialog;

    public static void showLoading(Context context, boolean cancelable) {
        if (dialog != null && dialog.isShowing())
            return;
        dialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
        dialog.setTitleText("加载中");
        dialog.setCancelable(cancelable);
        dialog.show();
    }

    public static void showLoading(Context context, String text, boolean cancelable) {
        if (dialog != null && dialog.isShowing())
            return;
        dialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
        dialog.setTitleText(text);
        dialog.setCancelable(cancelable);
        dialog.show();
    }

    public static void showLoadingCancelable(Context context, String text, DialogInterface.OnCancelListener listener) {
        showLoading(context, text, true);
        dialog.setOnCancelListener(listener);
    }

    public static void dismissLoading() {
        dialog.dismiss();
    }



}
