package me.windleafy.kity.android.wiget.button;

import android.view.View;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;


/**
 * 防止空间过快点击造成多次事件
 * <p>
 * https://blog.csdn.net/barryhappy/article/details/44501359
 */
public abstract class NoDoubleClickListener implements View.OnClickListener {

    private int delay = 1000; //两次点击必须大于的间隔时间（ms）
    private boolean clickable = true; //在间隔时间内是否可点击
    private long lastClickTime = 0;

    /**
     * 间隔1000ms，可点击
     */
    public NoDoubleClickListener() {
    }

    /**
     * @param delay 两次点击必须大于的间隔时间(ms)
     */
    public NoDoubleClickListener(int delay) {
        this.delay = delay;
    }

    /**
     * @param clickable 在间隔时间内是否可点击
     */
    public NoDoubleClickListener(boolean clickable) {
        this.clickable = clickable;
    }

    /**
     * @param delay     两次点击必须大于的间隔时间(ms)
     * @param clickable 在间隔时间内是否可点击
     */
    public NoDoubleClickListener(int delay, boolean clickable) {
        this.delay = delay;
        this.clickable = clickable;
    }

    @Override
    public void onClick(final View v) {
        long currentTime = Calendar.getInstance().getTimeInMillis();
        if (currentTime - lastClickTime > delay) {
            lastClickTime = currentTime;
            onNoDoubleClick(v);
            if (!clickable) {
                v.setClickable(false);
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        v.setClickable(true);
                    }
                }, delay);
            }
        }
    }

    protected abstract void onNoDoubleClick(View v);

    //usage

    //button.setOnClickListener(new NoDoubleClickListener(1000, false) {
    //            @Override
    //            protected void onNoDoubleClick(View v) {
    //                startFirstRequest();
    //            }
    //        });

}