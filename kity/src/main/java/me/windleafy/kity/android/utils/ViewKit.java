package me.windleafy.kity.android.utils;

import android.graphics.Point;
import android.view.View;
import android.view.ViewGroup;

/**
 * 跟App相关的辅助类
 */
public class ViewKit {

    private ViewKit() {
        /**cannot be instantiated **/
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static class PARAMS {
        public static final int MATCH_PARENT = ViewGroup.LayoutParams.MATCH_PARENT;
        public static final int WRAP_CONTENT = ViewGroup.LayoutParams.WRAP_CONTENT;
    }

    /**
     * 在onCreate中获取view宽度
     *
     * @param view
     * @return
     */
    public static int getWrapWidthOnCreate(View view) {
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(w, h);
        return view.getMeasuredWidth();
    }

    /**
     * 在onCreate中获取view宽度
     *
     * @param view
     * @return
     */
    public static int getWrapHeightOnCreate(View view) {
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(w, h);
        return view.getMeasuredHeight();
    }


    /**
     * @param view
     * @param runnable
     */
    public static void getWidthHeightOnCreate(View view, final Runnable runnable) {
        view.post(runnable);
    }


    @Deprecated
    private static int getMode(int layoutParams) {
        if (layoutParams == PARAMS.MATCH_PARENT) {
            return View.MeasureSpec.AT_MOST;
        } else if (layoutParams == PARAMS.WRAP_CONTENT) {
            return View.MeasureSpec.UNSPECIFIED;
        }
        return 0;
    }


    /**
     * 设置宽高
     *
     * @param view
     * @param widthPx
     * @param heightPx
     */
    public static void setWidthHeight(View view, int widthPx, int heightPx) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.width = widthPx;
        params.height = heightPx;
        view.setLayoutParams(params);
    }

    /**
     * 设置坐标点，相对于父控件
     *
     * @param view
     * @param x
     * @param y
     */
    public static void setPositionInParent(View view, int x, int y) {
        view.setX(x);
        view.setY(y);
    }


    public static void setPositionInAppScreen(View view, int x, int y) {
        view.setTranslationX(x);
        view.setTranslationY(y);
    }

    public static void move(View view, int offsetX, int offsetY) {
        view.offsetLeftAndRight(offsetX);
        view.offsetTopAndBottom(offsetY);
    }


    /**
     * 获取在当前窗口内的绝对坐标,当前activity显示的大小
     *
     * @param view
     * @return [x, y]
     */
    public static Point getLocationInWindow(View view) {
        int[] location = new int[2];
        view.getLocationInWindow(location);
        return new Point(location[0], location[1]);
    }

    /**
     * 获取在整个屏幕内的绝对坐标,包括通知栏
     *
     * @param view
     * @return [x, y]
     */
    public static Point getLocationOnScreen(View view) {
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        return new Point(location[0], location[1]);
    }


    public static void setLayoutInParent(View view, int l, int t, int r, int b) {
        view.layout(l, t, r, b);
    }

    public static void getViewInfo(View view){

    }


}