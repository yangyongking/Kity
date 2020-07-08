package me.windleafy.kity.android.utils;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class InflaterKit {

    private InflaterKit() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }


    /**
     * @param context
     * @param resId
     * @return
     */
    public static View inflate(Context context, int resId) {
        return LayoutInflater.from(context).inflate(resId, null);
    }

    /**
     * @param activity
     * @param resId
     * @return
     */
    public static View inflate(Activity activity, int resId) {
        return LayoutInflater.from(activity).inflate(resId, null);
    }

    /**
     * @param fragment
     * @param resId
     * @return
     */
    public static View inflate(Fragment fragment, int resId) {
        return LayoutInflater.from(fragment.getContext()).inflate(resId, null);
    }

    /**
     * @param activity
     * @param fragment
     * @param resId
     * @return
     */
    public static View inflate(Activity activity, Fragment fragment, int resId) {
        return inflate(activity, fragment, resId, null);
    }

    /**
     * @param activity
     * @param fragment
     * @param resId
     * @return
     */
    public static View inflate(Activity activity, Fragment fragment, int resId, ViewGroup root) {
        if (activity != null) {
            return LayoutInflater.from(activity).inflate(resId, root);
        }
        if (fragment != null && fragment.getContext() != null) {
            return LayoutInflater.from(fragment.getContext()).inflate(resId, root);
        }
        return null;
    }

}
