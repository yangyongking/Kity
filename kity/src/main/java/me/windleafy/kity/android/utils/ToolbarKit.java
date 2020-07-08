package me.windleafy.kity.android.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import me.windleafy.kity.R;

/**
 * Toolbar设置
 * <p>
 * 【view_toolbar.xml】
 * <?xml version="1.0" encoding="utf-8"?>
 * <android.support.v7.widget.Toolbar xmlns:android="http://schemas.android.com/apk/res/android"
 * xmlns:app="http://schemas.android.com/apk/res-auto"
 * android:id="@+id/tool_bar"
 * android:layout_width="match_parent"
 * android:layout_height="wrap_content"
 * android:fitsSystemWindows="true"
 * app:titleTextColor="@android:color/white"
 * android:background="?attr/colorPrimary"
 * android:minHeight="?attr/actionBarSize"
 * app:navigationIcon="@mipmap/icon_back_white"/>
 * <p>
 * 【Manifest.xml】
 * Application的主题引用注意theme
 * Activity设置属性
 * <activity
 * ...
 * android:windowSoftInputMode="adjustResize|stateHidden"
 * .../>
 * 【style.xml】
 * 取消沉浸状态栏
 * * <item name="android:windowIsTranslucent">true</item>
 * * <item name="android:windowTranslucentStatus">true</item>
 * 【layout】
 * 有Edittext布局的xml，需要在根布局中加入android:fitsSystemWindows="true"，以防Toolbar高度拉伸
 * <?xml version="1.0" encoding="utf-8"?>
 * <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
 * android:layout_width="match_parent"
 * android:layout_height="match_parent"
 * android:gravity="center_horizontal"
 * android:fitsSystemWindows="true"
 * android:orientation="vertical">
 * ......
 * </LinearLayout>
 * <p>
 * 【usage】
 */
public class ToolbarKit {

    private ToolbarKit() {
        /** cannot be instantiated**/
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 设置titlebar
     *
     * @param activity
     * @param toolBar
     * @param title    标题
     * @param listener 导航按钮监听，例如返回onBackPressed();
     */
    public static void setToolBar(AppCompatActivity activity, Toolbar toolBar, String title, View.OnClickListener listener) {
        activity.setSupportActionBar(toolBar);
        ActionBar actionBar = activity.getSupportActionBar();
        if (actionBar != null) {
            //去除默认Title显示
            actionBar.setDisplayShowTitleEnabled(TextUtils.isEmpty(title));
            actionBar.setDisplayHomeAsUpEnabled(listener != null);
        }
        if (listener != null)
            toolBar.setNavigationOnClickListener(listener);
        //设置标题
        setTitleCenter(activity, toolBar, title);
    }

    public static void setTitleCenter(Context context, Toolbar toolbar, String title) {
        TextView titleText = new TextView(context);
        titleText.setTextColor(ContextCompat.getColor(context, R.color.white));
        titleText.setText(title);
        titleText.setTextSize(18);
        titleText.setGravity(Gravity.CENTER);
        Toolbar.LayoutParams layoutParams = new Toolbar.LayoutParams(Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER;
        titleText.setLayoutParams(layoutParams);
        toolbar.addView(titleText);
    }


    /**
     * 设置状态栏颜色为透明色，这样能保证状态栏为沉浸式状态
     * 根据SDK >= 21时，标题栏高度设为statusBarHeight(25dp)+titlBarHeight(48dp)
     * 若SDK < 21,标题栏高度直接为titlBarHeight
     * <p>
     *
     * @param title_bar_main_color 页面标题栏主色调
     */
    public static void setStatusBar(Activity activity, int title_bar_main_color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0及以上
            View decorView = activity.getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            activity.getWindow().setStatusBarColor(activity.getResources().getColor(title_bar_main_color));
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4到5.0
            WindowManager.LayoutParams localLayoutParams = activity.getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
    }

    /**
     * 使用方法
     */
    private static class usage{
//        protected void setToolBar(Toolbar toolBar, String title) {
//            ToolbarKit.setStatusBar(this, R.color.colorPrimary);
//            ToolbarKit.setToolBar(this, toolBar, title, new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    onBackPressed();
//                }
//            });
//        }
    }
}
