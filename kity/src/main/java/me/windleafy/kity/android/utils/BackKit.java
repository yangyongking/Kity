package me.windleafy.kity.android.utils;

import android.app.Activity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.widget.Toast;

import java.util.Date;

import me.windleafy.kity.java.callback.Call;

public class BackKit {

    private BackKit() {
        /**cannot be instantiated **/
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    private static long mPreTime;

    public static boolean setDoubleBackPressedFinish(Activity activity, String toastMsg, long time, int keyCode) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            long currentTime = new Date().getTime();
            // 如果时间间隔大于time, 不处理
            if ((currentTime - mPreTime) > time) {
                Toast.makeText(activity, TextUtils.isEmpty(toastMsg) ? "再按一次退出" : toastMsg, Toast.LENGTH_LONG).show();// 显示消息
                mPreTime = currentTime;// 更新时间
            } else {
                mPreTime = 0;
                activity.finish();
            }
        }
        return true;// 截获事件,不再处理
    }

    public static boolean setDoubleBackPressedFinish(Activity activity, Call call, long time, int keyCode) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            long currentTime = new Date().getTime();
            // 如果时间间隔大于time, 不处理
            if ((currentTime - mPreTime) > time) {
                if (call !=null){
                    call.back(null);
                }
                mPreTime = currentTime;// 更新时间
            } else {
                mPreTime = 0;
                activity.finish();
            }
        }
        return true;// 截获事件,不再处理
    }

}
