package me.windleafy.kity.android.wiget.toast;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Toast;

/**
 * 单列模式
 * 替代Toast，需要先初始化
 * 初始化：Application -> onCreate -> ToastEnum.holder.init(context);
 */
enum ToastEnum {

    holder;

    private Toast it;

    public void init(Context c) {
        View v = Toast.makeText(c, "", Toast.LENGTH_SHORT).getView();
        it = new Toast(c);
        it.setView(v);
    }

    /**
     * UI显示
     */
    public void show(Activity activity, final int resid) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                show(resid);
            }
        });
    }

    public void show(int resid) {
        show(resid, Toast.LENGTH_SHORT);
    }

    public void show(int resid, int duration) {
        it.setText(resid);
        it.setDuration(duration);
        it.show();
    }

    /**
     * UI显示
     */
    public void show(Activity activity, final CharSequence text) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                show(text);
            }
        });
    }

    public void show(CharSequence text) {
        show(text, Toast.LENGTH_SHORT);
    }

    public void show(CharSequence text, int duration) {
        it.setText(text);
        it.setDuration(duration);
        it.show();
    }


}
