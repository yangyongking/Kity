package me.windleafy.kity;

import android.app.Activity;
import android.content.Context;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;

import java.util.List;

import me.windleafy.kity.android.utils.DensityKit;
import me.windleafy.kity.android.utils.InflaterKit;
import me.windleafy.kity.android.utils.KeyboardKit;
import me.windleafy.kity.android.utils.ScreenKit;
import me.windleafy.kity.java.judge.BoolKit;
import me.windleafy.kity.java.judge.JudgeKit;


/**
 * kity目录为kit相关文件类
 */
public final class Kit {

    private Kit() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /*************************************************************************************
     *********************************** me.windleafy.kity *******************************
     *************************************************************************************/

    /*
     *********************************** Empty ***********************************
     */
    public static boolean notEmpty(String str) {
        return !isEmpty(str);
    }

    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static <T> boolean notEmpty(List<T> list) {
        return !isEmpty(list);
    }

    public static <T> boolean isEmpty(List<T> list) {
        if (list == null || list.size() == 0) {
            return true;
        }
        return false;
    }

    public static String stringNullToEmpty(String str) {
        return str == null ? "" : str;
    }


    /*
     *********************************** InflaterKit ***********************************
     */
    public static View inflate(Context context, int resId) {
        return InflaterKit.inflate(context, resId);
    }

    /*
     *********************************** KeyboardKit ***********************************
     */

    /**
     * 弹出软键盘
     *
     * @param activity
     */
    public static void showKeyboard(Activity activity) {
        KeyboardKit.showKeyboard(activity);
    }

    /**
     * 隐藏软键盘
     *
     * @param activity
     */
    public static void closeKeyboard(Activity activity) {
        KeyboardKit.closeKeyboard(activity);
    }


    /*
     *********************************** ScreenKit ***********************************
     */

    /**
     * 获取屏幕信息
     *
     * @param context
     * @return
     */
    public static String getScreenInfo(Context context) {
        return ScreenKit.info(context);
    }


    /*
     *********************************** Judge ***********************************
     */

    /**
     * 是否集合里是否包含element值
     *
     * @param elements
     * @param <E>
     * @return
     */
    @SafeVarargs
    public static <E> boolean contain(E element, E... elements) {
        return JudgeKit.contain(element, elements);
    }

    /**
     * 是否所有元素都与element相同
     *
     * @param element
     * @param elements
     * @param <E>
     * @return
     */
    @SafeVarargs
    public static <E> boolean equal(E element, E... elements) {
        return JudgeKit.equals(element, elements);
    }


    /*
     *********************************** Bool ***********************************
     */


    /**
     * 所有元素为true
     *
     * @param bools
     * @return
     */
    public static boolean allTrue(Boolean... bools) {
        return BoolKit.allTrue(bools);
    }

    /**
     * 所有元素为false
     *
     * @param bools
     * @return
     */
    public static boolean allFalse(Boolean... bools) {
        return BoolKit.allFalse(bools);
    }


    /**
     * 至少有1个元素为真
     *
     * @param bools
     * @return
     */
    public static boolean containTrue(Boolean... bools) {
        return BoolKit.containTrue(bools);
    }

    /**
     * 至少有1个元素为假
     *
     * @param bools
     * @return
     */
    public static boolean containFalse(Boolean... bools) {
        return BoolKit.containFalse(bools);
    }


    /*
     ***********************************  ***********************************
     */



    /*
     ***********************************  ***********************************
     */

    /**
     * dp转px
     *
     * @param context
     * @param dpVal
     * @return
     */
    public static int dp2px(Context context, float dpVal) {
        return DensityKit.dp2px(context, dpVal);
    }

    /**
     * sp转px
     *
     * @param context
     * @param spVal
     * @return
     */
    public static int sp2px(Context context, float spVal) {
        return DensityKit.sp2px(context, spVal);
    }

    /**
     * px转dp
     *
     * @param context
     * @param pxVal
     * @return
     */
    public static float px2dp(Context context, float pxVal) {
        return DensityKit.px2dp(context, pxVal);
    }

    /**
     * px转sp
     *
     * @param context
     * @param pxVal
     * @return
     */
    public static float px2sp(Context context, float pxVal) {
        return DensityKit.px2sp(context, pxVal);
    }


}
