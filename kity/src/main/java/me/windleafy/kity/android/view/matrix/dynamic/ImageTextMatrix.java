package me.windleafy.kity.android.view.matrix.dynamic;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import me.windleafy.kity.android.utils.LayoutKit;
import me.windleafy.kity.android.view.matrix.HybridView;
import me.windleafy.kity.android.view.matrix.ViewMatrix;


/**
 * Created by JOUAV on 2015/6/4.
 */
public class ImageTextMatrix extends ViewMatrix<ImageTextMatrix.ImageTextHybirdView> {

    public ImageTextMatrix(Activity activity, int container, int size) {
        super(activity, container, size);
    }

    public ImageTextMatrix(Activity activity, int container, int size, float[] width_height, int unit) {
        super(activity, container, size, width_height, unit);
    }


    @Override
    public ViewMatrix init(Object[]... objects) {
        Integer[] image = (Integer[]) objects[0];
        String[] text = (String[]) objects[1];

        for (int i = 0; i < getSize(); i++) {
            getItem(i).setResources(image[i], text[i]);
        }
        return this;
    }

    @Override
    public void set(int index, Object... objects) {

    }

    @Override
    public ImageTextHybirdView create(@LinearLayoutCompat.OrientationMode int orient) {
        return new ImageTextHybirdView(mActivity, LayoutKit.getOppositeOrientation(orient));
    }


    public void setImageViewWidthHeight(int width_dp, int height_dp) {
        for (View view : getColViews(0)) {
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            layoutParams.width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                    width_dp, mActivity.getResources().getDisplayMetrics());
            layoutParams.height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                    height_dp, mActivity.getResources().getDisplayMetrics());
            view.setLayoutParams(layoutParams);
        }
    }


    /**
     * Created by JOUAV on 2015/6/4.
     */
    public class ImageTextHybirdView extends HybridView {

        public ImageTextHybirdView(Context context) {
            super(context);
        }

        public ImageTextHybirdView(Activity mActivity, int orient) {
            super(mActivity, orient);
        }

//        public ImageTextHybirdView(Context context, int orient) {
//            super(context);
//            setOrient(orient);
//        }

        @Override
        protected void set() {

            setGravity(Gravity.CENTER);
            setMargin(20);
        }

        @Override
        protected void add() {
            //添加imageview
//            ImageView imageView = new ImageView(mActivity);
//            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams((int) mActivity.getResources().getDimension(R.dimen.handle_image_cmd_width), (int) mActivity.getResources().getDimension(R.dimen.handle_image_cmd_height));
//            imageView.setLayoutParams(lp);
            addView(new ImageView(mActivity));
            //添加textview
            addView(new TextView(mActivity));
        }

        @Override
        protected void listen() {

        }

    }
}
