package me.windleafy.kity.android.view.combine;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.StringRes;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import me.windleafy.kity.R;

/**
 * 条状栏控件
 */
public class CardBarView extends RelativeLayout {

    private CardView vCardViewRoot;
    private LinearLayout vLayout;
    private LinearLayout vItemMainContentLayout;
    private ImageView vImage;
    private ImageView vJump;
    private TextView vMainText;
    private TextView vContentText;
    private View vLineTop;
    private View vLineBottom;

    private Bitmap mImageSrc;
    private Bitmap mJumpSrc;
    private String mMainText;
    private String mContentText;
    private int mMainTextSize;
    private int mContentTextSize;
    private int mMainTextColor;
    private int mContentTextColor;
    private int mShowLine = Line.NONE;
    private int mLinePaddingHorizon;
    private int mPaddingHorizon;
    private int mPaddingVertical;
    private float mRadiusDp;

    private static class Line {
        public static final int NONE = 0; //DEFAULT
        public static final int BOTH = 1;
        public static final int TOP = 2;
        public static final int BOTTOM = 3;
    }

    public CardBarView(Context context) {
        this(context, null);
    }

    public CardBarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CardBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        obtainAttrs(attrs);
        initView(context);
//        initListener();
    }

    /**
     * 解析数据
     *
     * @param attrs
     */
    private void obtainAttrs(AttributeSet attrs) {

        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.CardBarView);

        mImageSrc = getBitmapFromAttrs(typedArray, R.styleable.CardBarView_image);
        mJumpSrc = getBitmapFromAttrs(typedArray, R.styleable.CardBarView_jump);

        mMainText = typedArray.getString(R.styleable.CardBarView_mainText);
        mContentText = typedArray.getString(R.styleable.CardBarView_contentText);

        mMainTextSize = typedArray.getDimensionPixelSize(R.styleable.CardBarView_mainTextSize, dp2px(14));
        mContentTextSize = typedArray.getDimensionPixelSize(R.styleable.CardBarView_contentTextSize, dp2px(14));

        mMainTextColor = typedArray.getColor(R.styleable.CardBarView_mainTextColor, getResources().getColor(android.R.color.black));
        mContentTextColor = typedArray.getColor(R.styleable.CardBarView_contentTextColor, getResources().getColor(android.R.color.darker_gray));

        mShowLine = typedArray.getInteger(R.styleable.CardBarView_line, Line.NONE);

        mRadiusDp = typedArray.getDimensionPixelSize(R.styleable.CardBarView_radiusPx, dp2px(0));

        mLinePaddingHorizon = typedArray.getDimensionPixelSize(R.styleable.CardBarView_linePadding, dp2px(0));
        mPaddingHorizon = typedArray.getDimensionPixelSize(R.styleable.CardBarView_paddingHorizon, dp2px(0));
        mPaddingVertical = typedArray.getDimensionPixelSize(R.styleable.CardBarView_paddingVertical, dp2px(0));

        typedArray.recycle();
    }


    /**
     * 获取Bitmap
     *
     * @param typedArray
     * @param src
     * @return
     */
    private Bitmap getBitmapFromAttrs(TypedArray typedArray, int src) {
        Drawable drawable = typedArray.getDrawable(src);
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        return null;
    }

    public int dp2px(final float dpValue) {
        return (int) (dpValue * getResources().getDisplayMetrics().density + 0.5f);
    }

    public int px2dp(final float pxValue) {
        return (int) (pxValue / getResources().getDisplayMetrics().density + 0.5f);
    }


    /**
     * 初始化View
     *
     * @param context
     */
    private void initView(Context context) {

        //获取数据
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.kity_view_card_item, this);

        vCardViewRoot = view.findViewById(R.id.item_root);
        vLayout = view.findViewById(R.id.item_layout);
        vItemMainContentLayout = view.findViewById(R.id.item_main_content_layout);
        vImage = view.findViewById(R.id.item_image);
        vJump = view.findViewById(R.id.item_jump);
        vMainText = view.findViewById(R.id.item_main);
        vContentText = view.findViewById(R.id.item_content);
        vLineTop = view.findViewById(R.id.item_line_top);
        vLineBottom = view.findViewById(R.id.item_line_bottom);

        vCardViewRoot.setRadius(dp2px(mRadiusDp));

        if (mImageSrc != null) {
            vImage.setImageBitmap(mImageSrc);
        }
        if (mJumpSrc != null) {
            vJump.setImageBitmap(mJumpSrc);
        }

        if (mMainText != null) {
            vMainText.setText(mMainText);
        }
        if (mContentText != null) {
            vContentText.setText(mContentText);
        }

        vMainText.setTextSize(px2dp(mMainTextSize));
        vMainText.setTextColor(mMainTextColor);
        vContentText.setTextSize(px2dp(mContentTextSize));
        vContentText.setTextColor(mContentTextColor);

        vLineTop.setVisibility(mShowLine == Line.TOP || mShowLine == Line.BOTH ? VISIBLE : GONE);
        vLineBottom.setVisibility(mShowLine == Line.BOTTOM || mShowLine == Line.BOTH ? VISIBLE : GONE);

        LinearLayout.LayoutParams lineTopLayoutParams = (LinearLayout.LayoutParams) vLineTop.getLayoutParams();
        lineTopLayoutParams.setMargins(mLinePaddingHorizon, 0, mLinePaddingHorizon, 0);
        vLineTop.setLayoutParams(lineTopLayoutParams);

        LinearLayout.LayoutParams lineBottomLayoutParams = (LinearLayout.LayoutParams) vLineBottom.getLayoutParams();
        lineBottomLayoutParams.setMargins(mLinePaddingHorizon, 0, mLinePaddingHorizon, 0);
        vLineBottom.setLayoutParams(lineBottomLayoutParams);

        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) vItemMainContentLayout.getLayoutParams();
        layoutParams.setMargins(mPaddingHorizon, mPaddingVertical, mPaddingHorizon, mPaddingVertical);
        vItemMainContentLayout.setLayoutParams(layoutParams);
    }


    /**
     * RootCardView
     */

    public void setRadius(int radiusPx) {
        vCardViewRoot.setRadius(radiusPx);
    }

    /**
     * MainText
     */

    public void setMainText(CharSequence mainText) {
        vMainText.setText(mainText);
    }

    public void setMainText(@StringRes int mainText) {
        vMainText.setText(mainText);
    }

    public void setMainTextSize(float textSize) {
        vMainText.setTextSize(textSize);
    }

    public void setMainTextColor(@ColorInt int color) {
        vMainText.setTextColor(color);
    }

    /**
     * ContentText
     */

    public void setContentText(CharSequence contentText) {
        vContentText.setText(contentText);
    }

    public void setContentText(@StringRes int contentText) {
        vContentText.setText(contentText);
    }

    public void setContentTextSize(float textSize) {
        vContentText.setTextSize(textSize);
    }

    public void setContentTextColor(@ColorInt int color) {
        vContentText.setTextColor(color);
    }

    /**
     * Line
     */

    public void showTopLine() {
        vLineTop.setVisibility(VISIBLE);
    }

    public void hideTopLine() {
        vLineTop.setVisibility(INVISIBLE);
    }

    public void showBottomLine() {
        vLineBottom.setVisibility(VISIBLE);
    }

    public void hideBottomLine() {
        vLineBottom.setVisibility(INVISIBLE);
    }

}
