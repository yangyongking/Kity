package me.windleafy.kity.android.utils;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

public class ContextKit {

    private ContextKit() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * @param activity
     * @param fragment
     * @return
     */
    public static Context get(Activity activity, Fragment fragment) {
        if (activity != null) {
            return activity.getApplicationContext();
        }
        if (fragment != null && fragment.getContext() != null) {
            return fragment.getContext();
        }
        return null;
    }

}
