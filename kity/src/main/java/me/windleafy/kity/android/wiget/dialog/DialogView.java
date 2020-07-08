package me.windleafy.kity.android.wiget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import me.windleafy.kity.R;

/**
 * Dialog显示自定义View
 * <p>
 * 使用方法
 */
public class DialogView extends Dialog {

    public static final int MATCH_PARENT = WindowManager.LayoutParams.MATCH_PARENT;
    public static final int WRAP_CONTENT = WindowManager.LayoutParams.WRAP_CONTENT;

    public static final int GRAVITY_CENTER = Gravity.CENTER;
    public static final int GRAVITY_BOTTOM = Gravity.BOTTOM;
    public static final int GRAVITY_TOP = Gravity.TOP;

    public static final int ANIM_CENTER = R.style.dialog_center_animation;
    public static final int ANIM_BOTTOM = R.style.dialog_bottom_animation;
    public static final int ANIM_TOP = R.style.dialog_top_animation;

    private Context context;
    private View view;
    private double percent = 0.6;
    private double rate = 0.6;
    private double alpha = 1;
    //点击外部区域可取消
    private boolean mBackEnable = true;//返回按钮是否有效

    public static DialogView initCenter(Context context, View view) {
        return new DialogView(context, view, R.style.dialog_center);
    }

    public static DialogView initBottom(Context context, View view) {
        return new DialogView(context, view, R.style.dialog_bottom);
    }

    public static DialogView initTop(Context context, View view) {
        return new DialogView(context, view, R.style.dialog_top);
    }

    /**
     * @param context
     * @param resId   自定义view的layout
     */
    public DialogView(@NonNull Context context, @NonNull int resId) {
        super(context);
        this.context = context;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(resId, null);
        init();
    }


    /**
     * @param context
     * @param view    自定义view
     */
    public DialogView(@NonNull Context context, @NonNull View view) {
        super(context);
        this.context = context;
        this.view = view;
        init();
    }

    /**
     * @param context
     * @param resId       自定义view的layout
     * @param dialogStyle 对话框Style
     */
    public DialogView(@NonNull Context context, @NonNull int resId, @StyleRes int dialogStyle) {
        super(context, dialogStyle);
        this.context = context;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(resId, null);
        init();
    }

    /**
     * @param context
     * @param view    自定义view
     *                * @param dialogStyle 对话框Style
     */
    public DialogView(@NonNull Context context, @NonNull View view, @StyleRes int dialogStyle) {
        super(context, dialogStyle);
        this.context = context;
        this.view = view;
        init();
    }

    private void init() {
        //无title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.setContentView(view);
    }

    /**
     * dialog宽度与手机屏幕宽度百分比
     *
     * @param percent
     */
    public void setPercent(double percent) {
        this.percent = percent;
    }

    /**
     * dialog高度与dialog宽度百分比
     *
     * @param rate
     */
    public void setRate(double rate) {
        this.rate = rate;
    }

    /**
     * dialog透明度
     *
     * @param alpha
     */
    public void setAlpha(double alpha) {
        this.alpha = alpha;

    }

    /**
     * dialog背景颜色
     *
     * @param bgId
     */
    @Deprecated
    public void setBackground(int bgId) {
        view.setBackgroundResource(bgId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public <T extends View> T findViewById(int id) {
        return view.findViewById(id);
    }


    /**
     * 显示
     *
     * @param percent dialog宽与屏幕宽度百分比
     * @param rate    dialog高度与宽度百分比
     */
    public DialogView setPercentRate(double percent, double rate) {

        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        if (wm != null) {
            wm.getDefaultDisplay().getMetrics(dm);
        }
        lp.width = (int) (dm.widthPixels * percent);
        lp.height = (int) (dm.widthPixels * percent * rate);
        lp.alpha = (float) alpha;
        dialogWindow.setAttributes(lp);

        return this;

//        Log.d("DialogView", "lp = " + lp.width + ", " + lp.height);

    }


    /**
     * 显示
     *
     * @param percent dialog宽与屏幕宽度百分比
     */
    public DialogView setPercentRate(double percent) {
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        if (wm != null) {
            wm.getDefaultDisplay().getMetrics(dm);
        }
        lp.width = (int) (dm.widthPixels * percent);
//        lp.height = (int) (dm.widthPixels * percent * rate);
        lp.height = WRAP_CONTENT;
        lp.alpha = (float) alpha;
        dialogWindow.setAttributes(lp);

        return this;

//        Log.d("DialogView", "lp = " + lp.width + ", " + lp.height);

    }

    public static final int DISMISS_ALWAYS = 0;
    public static final int DISMISS_TOUCH_DIALOG = 1; //默认
    public static final int DISMISS_TOUCH_OUTSIDE = 2;
    public static final int DISMISS_NEVER = 3;


    /**
     * 设置取消对话框的情况
     *
     * @param cancel
     * @return
     */
    public DialogView setDismiss(int cancel) {
//        View root = view.getRootView();
        switch (cancel) {
            case DISMISS_ALWAYS:
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dismiss();
                    }
                });
                setCanceledOnTouchOutside(true);
                break;
            case DISMISS_TOUCH_DIALOG:
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dismiss();
                    }
                });
                setCanceledOnTouchOutside(false);
                break;
            case DISMISS_TOUCH_OUTSIDE:
                setCanceledOnTouchOutside(true);
                break;
            case DISMISS_NEVER:
                setCanceledOnTouchOutside(false);
                break;
        }
        return this;
    }


    /**
     * 显示
     *
     * @param witdhDp
     * @param heightDp
     */
    public DialogView setWidthHeight(int witdhDp, int heightDp) {
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = dp2px(context, witdhDp);
        lp.height = dp2px(context, heightDp);
        lp.alpha = (float) alpha;
        dialogWindow.setAttributes(lp);
        return this;
    }

    /**
     * 显示
     *
     * @param witdhDp
     * @return
     */
    public DialogView setWidth(int witdhDp) {
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = dp2px(context, witdhDp);
        lp.height = WRAP_CONTENT;
        lp.alpha = (float) alpha;
        dialogWindow.setAttributes(lp);
        return this;
    }


    /**
     * 中部弹出弹入对话框(宽度占满屏幕)
     */
    public DialogView setCenter() {
        return setWindow(MATCH_PARENT, WRAP_CONTENT, GRAVITY_CENTER, ANIM_CENTER);
    }

    /**
     * 底部弹出弹入对话框(宽度占满屏幕)
     */
    public DialogView setBottom() {
        return setWindow(MATCH_PARENT, WRAP_CONTENT, GRAVITY_BOTTOM, ANIM_BOTTOM);
    }

    /**
     * 底部弹出弹入对话框(宽度占满屏幕)
     */
    public DialogView setTop() {
        return setWindow(MATCH_PARENT, WRAP_CONTENT, GRAVITY_TOP, ANIM_TOP);
    }


    /**
     * 设置窗口信息
     *
     * @param width
     * @param height
     * @param gravity
     * @param animation
     * @return
     */
    public DialogView setWindow(int width, int height, int gravity, int animation) {
        Window window = getWindow();
        window.setLayout(width, height);
        window.setGravity(gravity);
        window.setWindowAnimations(animation);
        return this;
    }


    public void show() {
        super.show();

    }


    /**
     * 返回键是否有效
     *
     * @param able
     * @return
     */
    public DialogView setBackEnable(boolean able) {
        mBackEnable = able;
        return this;
    }

    @Override
    public void onBackPressed() {
        if (mBackPressListener != null)
            mBackPressListener.click();
        if (mBackEnable)
            super.onBackPressed();
    }

    public interface OnBackPressListener {
        void click();
    }

    private OnBackPressListener mBackPressListener;

    /**
     * 返回按钮点击监听事件
     *
     * @param backPressListener
     * @return
     */
    public DialogView setOnBackPressed(OnBackPressListener backPressListener) {
        mBackPressListener = backPressListener;
        return this;
    }

    /**
     * DP to PX
     *
     * @param context
     * @param dpValue
     * @return
     */
    private int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


}
