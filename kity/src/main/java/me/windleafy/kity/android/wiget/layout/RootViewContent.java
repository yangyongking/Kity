package me.windleafy.kity.android.wiget.layout;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

import java.util.LinkedList;
import java.util.List;

/**
 * Activity的根视图
 */
 class RootViewContent {

    private ViewGroup root;//根视图
    private List<View> views;

    public RootViewContent(Activity activity) {
        root = getActivityRootView(activity);
        views = new LinkedList<>();
    }


    /**
     * 获取Activity显示区域的根视图
     *
     * @param activity
     * @return
     */
    public static ViewGroup getActivityRootView(Activity activity) {
        return activity.getWindow().getDecorView().findViewById(android.R.id.content);
    }


    /**
     * 添加view
     *
     * @param view
     */
    public void add(View view) {
        if(views.contains(view))
            return;
        root.addView(view);
        views.add(view);
    }

    /**
     * 移除view
     *
     * @param view
     */
    public void remove(View view) {
        if(!views.contains(view))
            return;
        root.removeView(view);
        views.remove(view);
    }

    /**
     * 移除已添加的view
     */
    public void removeAll() {
        for (View view : views) {
            root.removeView(view);
        }
        views.clear();
    }


}
