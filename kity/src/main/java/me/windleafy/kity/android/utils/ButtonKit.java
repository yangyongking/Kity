package me.windleafy.kity.android.utils;

import android.view.MotionEvent;
import android.view.View;

import me.windleafy.kity.java.timer.Timery;

public class ButtonKit {

    private ButtonKit() {
        /**cannot be instantiated **/
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public interface OnLongTouchListener {

        /**
         * 触摸开始
         */
        void touchDown();

        /**
         * 触摸中断
         */
        void touchUp(boolean isArrivalTime);

        /**
         * 定时时间到
         */
        void timeOut();

    }

    /**
     * 长按监听
     *
     * @param view
     * @param time
     * @param listener
     */
    public static void setLongTouchListener(View view, int time, final OnLongTouchListener listener) {

        final boolean[] isTimeOut = {false};

        final Timery timery = new Timery(new Timery.Task() {
            @Override
            public void end(long time) {
                if (listener != null) {
                    isTimeOut[0] = true;
                    listener.timeOut();
                }
            }
        }, time);

        view.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        isTimeOut[0] = false;
                        if (!timery.isRunning())
                            timery.start();
                        if (listener != null) {
                            listener.touchDown();
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL: //长按可能执行cancel，而不是up
                        if (listener != null) {
                            listener.touchUp(isTimeOut[0]);
                        }
                        timery.stop();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        break;
                }
//                LogKit.d("event = " + event.getAction());
                return true;
            }
        });
    }

}