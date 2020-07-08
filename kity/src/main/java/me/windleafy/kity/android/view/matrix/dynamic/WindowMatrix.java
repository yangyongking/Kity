package me.windleafy.kity.android.view.matrix.dynamic;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.Arrays;
import java.util.List;

import me.windleafy.kity.android.view.matrix.HybridView;
import me.windleafy.kity.android.view.matrix.ViewMatrix;

/**
 * Created by JOUAV on 2015/6/4.
 */
public class WindowMatrix extends ViewMatrix<WindowMatrix.WindowHybirdView> {

    public List<Integer> mTag;

    public WindowMatrix(Activity activity, int container, int row) {
        super(activity, container, row);
    }

    public WindowMatrix(Activity activity, int container, int row, float[] width_height, int unit) {
        super(activity, container, row, width_height, unit);
    }

    @Override
    public ViewMatrix init(Object[]... objects) {
        Integer[] image = (Integer[]) objects[0];
        String[] title = (String[]) objects[1];
        String[] content = (String[]) objects[2];

        if (objects.length > 3) {
            mTag = Arrays.asList((Integer[]) objects[3]);
        }

        for (int i = 0; i < getSize(); i++) {
            getItem(i).setResources(image[i], title[i], content[i]);
        }

        return this;
    }

    @Override
    public void set(int index, Object... objects) {
        getRowItem(index).setImage(0, (int) objects[0]);
        getRowItem(index).setText(1, (String) objects[1]);
        getRowItem(index).setText(2, (String) objects[2]);
    }


    @Override
    public WindowHybirdView create(@LinearLayoutCompat.OrientationMode int orient) {
        return new WindowHybirdView(mActivity, orient);
    }

    /**
     * 返回标签对应的索引
     * <p/>
     * 不包含则返回-1
     *
     * @param tag
     * @return
     */
    public int getIndex(Integer tag) {
        return mTag.indexOf(tag);
    }

    /**
     * Created by JOUAV on 2015/6/3.
     */
    public class WindowHybirdView extends HybridView {

        private ImageView vImageView;
        private TextView vTitleView;
        private TextView vContentView;


        public WindowHybirdView(Context context) {
            super(context);
        }

        public WindowHybirdView(Context context, int orient) {
            super(context, orient);
        }

        @Override
        protected void set() {
            setOrient(HORIZONTAL);
            setMargin(10);
            setGravity(Gravity.CENTER);
        }

        @Override
        protected void add() {
            vImageView = new ImageView(mActivity);
            vTitleView = new TextView(mActivity);
            vContentView = new TextView(mActivity);

            vTitleView.setTextColor(Color.WHITE);
            vContentView.setTextColor(Color.WHITE);

            //大小设置无效
            //        vImageView.layout(0, 0, 20, 20);
            //        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(20,20);
            //        vImageView.setLayoutParams(layoutParams);
            vImageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            //        vImageView.setMaxWidth(20);
            //        vImageView.setMaxHeight(20);

            addView(vImageView);
            addView(vTitleView);
            addView(vContentView);
        }

        @Override
        protected void listen() {

        }

    }
}
