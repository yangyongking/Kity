package me.windleafy.kity.android.wiget.toast;

import android.app.Activity;
import android.content.Context;
import android.view.View;

/**
 * 多次点击只会显示最近的内容
 */
public class Toaster {

    private Toaster() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 使用前先初始化
     * 初始化：Application -> onCreate -> Toaster.init(context);
     */
    public static void init(Context c) {
        ToastEnum.holder.init(c);
    }

    public static void show(Activity activity, final CharSequence text) {
        ToastEnum.holder.show(activity, text);
    }

    public static void show(CharSequence text) {
        ToastEnum.holder.show(text);
    }

    public static void show(CharSequence text, int duration) {
        ToastEnum.holder.show(text, duration);
    }

    public static void show(Activity activity, final int resid) {
        ToastEnum.holder.show(activity, resid);
    }

    public static void show(int resid) {
        ToastEnum.holder.show(resid);
    }

    public static void show(int resid, int duration) {
        ToastEnum.holder.show(resid, duration);
    }


}
