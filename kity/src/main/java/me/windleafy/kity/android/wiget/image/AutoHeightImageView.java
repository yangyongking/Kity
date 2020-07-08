package me.windleafy.kity.android.wiget.image;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

/**
 * ImageView宽度填满屏幕，高度自适应
 * <p>
 * https://www.jianshu.com/p/4aae17b7a402 方法二
 * <p>
 * 使用：
 * <me.windleafy.kity.android.wiget.image.AutoHeightImageView
 * android:layout_width="match_parent"
 * android:layout_height="wrap_content"
 * android:adjustViewBounds="true"
 * android:src="@mipmap/vip_title" />
 */
public class AutoHeightImageView extends android.support.v7.widget.AppCompatImageView {
    public AutoHeightImageView(Context context) {
        super(context);
    }

    public AutoHeightImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Drawable d = getDrawable();

        if (d != null) {
            // ceil not round - avoid thin vertical gaps along the left/right edges
            int width = MeasureSpec.getSize(widthMeasureSpec);
            //高度根据使得图片的宽度充满屏幕计算而得
            int height = (int) Math.ceil((float) width * (float) d.getIntrinsicHeight() / (float) d.getIntrinsicWidth());
            setMeasuredDimension(width, height);
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }
}
