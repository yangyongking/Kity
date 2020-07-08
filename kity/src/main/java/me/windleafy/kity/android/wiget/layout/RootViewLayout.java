package me.windleafy.kity.android.wiget.layout;

import android.app.Activity;
import android.app.Fragment;
import android.view.View;

/**
 * 让view依附在rootview上
 */
public class RootViewLayout {

    private Activity activity;
    private RootViewContent content;


    /**
     * @param activity 展示的activity
     */
    public RootViewLayout(Activity activity) {
        this.activity = activity;
        this.content = new RootViewContent(activity);
    }

    /**
     * @param fragment 展示的fragment
     */
    public RootViewLayout(Fragment fragment) {
        this.activity = fragment.getActivity();
        this.content = new RootViewContent(activity);
    }

    /**
     * @param view 显示view
     */
    public void show(View view) {
        if (content != null)
            content.add(view);
    }

    /**
     * @param view 隐藏view
     */
    public void hide(View view) {
        if (content != null)
            content.remove(view);
    }

    /**
     * 隐藏所有view
     */
    public void hide() {
        if (content != null)
            content.removeAll();
    }

    /**
     * 获取Activity
     *
     * @return
     */
    public Activity getActivity() {
        return activity;
    }
}
