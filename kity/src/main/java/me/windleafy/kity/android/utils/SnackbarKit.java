package me.windleafy.kity.android.utils;

import android.app.Activity;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.ViewGroup;

public class SnackbarKit {

    public static void show(View view, String msg) {
        Snackbar.make(view, msg, Snackbar.LENGTH_LONG).show();
    }

    public static void show(Activity activity, String msg) {
        Snackbar.make(((ViewGroup) activity.findViewById(android.R.id.content)).getChildAt(0), msg, Snackbar.LENGTH_LONG).show();
    }

    public static void showShort(View view, String msg) {
        Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).show();
    }

    public static void showShort(Activity activity, String msg) {
        Snackbar.make(((ViewGroup) activity.findViewById(android.R.id.content)).getChildAt(0), msg, Snackbar.LENGTH_SHORT).show();
    }

}
