package me.windleafy.kity.android.wiget.layout;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * 让view依附在rootview上
 */
public class LoadingLayout  extends RootViewLayout{

    public LoadingLayout(Activity activity) {
        super(activity);
    }

    public LoadingLayout(Fragment fragment) {
        super(fragment);
    }


    /**
     * @param view 显示view
     */
    @SuppressLint("ResourceType")
    public void show(final View view) {
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(view instanceof ViewGroup){
                    ViewGroup vg = (ViewGroup) view;
                    int count = vg.getChildCount();
                    for (int i = 0;i<count;i++){
                        if (v == vg.getChildAt(i))
                            return false;
                    }

                }

                return true;
            }
        });
        super.show(view);
    }


}
