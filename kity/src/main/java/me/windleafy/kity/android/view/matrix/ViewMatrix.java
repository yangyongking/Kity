package me.windleafy.kity.android.view.matrix;


import android.app.Activity;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * 控件矩阵
 * <p/>
 * Created by JOUAV on 2015/6/4.
 */
public abstract class ViewMatrix<T extends HybridView> extends ViewArray<T> {

    /**
     * 行
     */
    private int mRow;

    /**
     * 列
     */
    private int mCol;


    public ViewMatrix(Activity activity, int container, int size) {
        super(activity, container, size);
    }

    public ViewMatrix(Activity activity, int container, int size, float[] width_height, int unit) {
        super(activity, container, size, width_height, 0, unit);
    }

    /**
     * 初始化设置，子类实现，外部调用
     *
     * @param objects
     */
    public abstract ViewMatrix init(Object[]... objects);


    /**
     * 设置单行数据
     *
     * @param objects
     */
    public abstract void set(int index, Object... objects);


    /**
     * 创建实例
     *
     * @return
     */
    @Override
    public T createView(@LinearLayoutCompat.OrientationMode int orient) {
        return create(orient);
    }

    /**
     * 子类创建实例
     *
     * @return
     */
    protected abstract T create(@LinearLayoutCompat.OrientationMode int orient);


    /*-----------------------方法-------------------------*/


    /**
     * 获取单个View
     *
     * @param row
     * @param col
     * @return
     */
    public View getView(int row, int col) {
        if (isVertical()) {
            return getItemView(row, col);
        } else {
            return getItemView(col, row);
        }
    }

    /**
     * 获取单个View
     *
     * @param itemIndex
     * @param viewIndex
     * @return
     */
    public View getItemView(int itemIndex, int viewIndex) {
        return getItem(itemIndex).getView(viewIndex);
    }


    /**
     * 获取Item
     *
     * @param index
     * @return
     */
    public T getItem(int index) {
        return super.getItem(index);
    }

    /**
     * 获取Views
     *
     * @param index
     * @return
     */
    public List<View> getViews(int index) {
        List<View> list = new ArrayList<>();
        for (int i = 0; i < getSize(); i++) {
            list.add(getItem(i).getView(index));
        }
        return list;
    }


    /**
     * 获取一行
     *
     * @param row
     * @return
     */
    public Object getRowLine(int row) {
        if (isVertical()) {
            return getRowItem(row);
        } else {
            return getRowViews(row);
        }
    }

    /**
     * 获取一列
     *
     * @param col
     * @return
     */
    public Object getColLine(int col) {
        if (isVertical()) {
            return getColViews(col);
        } else {
            return getColItem(col);
        }
    }


    /**
     * 获取一整行，当为竖直方向
     *
     * @return 类型为T
     */
    public T getRowItem(int row) {
        return getItem(row);
    }

    /**
     * 获取一整行，当为水平方向
     *
     * @return 类型为List<View>
     */
    public List<View> getRowViews(int row) {
        return getViews(row);
    }

    /**
     * 获取一整列，当为水平方向
     *
     * @return 类型为T
     */
    public T getColItem(int col) {
        return getItem(col);
    }


    /**
     * 获取一整列，当为竖直方向
     *
     * @param col 列号
     * @return 类型为List<View>
     */
    public List<View> getColViews(int col) {
        return getViews(col);
    }


    /*-----------------------显示隐藏-------------------------*/

    /**
     * 容器中的内容是否可见（适合容器装载单个ViewArray类的时候）
     *
     * @param visibility
     */
    public void setVisibility(int visibility) {
        mLayout.setVisibility(visibility);
    }

    /**
     * 改变容器中的内容数据
     *
     * @param visibility
     */
    public void setVisibility(boolean visibility) {
        if (visibility) {
            resumeView();
        } else {
            removeView();
        }
    }


    /*-----------------------点击监听-------------------------*/
    private OnClickListener mClickListener;

    public void setOnClicListener(OnClickListener mClicListener) {
        this.mClickListener = mClicListener;
    }

    public interface OnClickListener {
        void OnRowColClick(View view, int row, int column);
        void OnItemViewClick(View view, int item, int index);
    }

    public static class OnClickListenerAdapter implements OnClickListener {

        @Override
        public void OnRowColClick(View view, int row, int column) {

        }

        @Override
        public void OnItemViewClick(View view, int item, int index) {

        }
    }

    private OnLongClickListener mLongClickListener;

    public void setOnLongClicListener(OnLongClickListener mLongClickListener) {
        this.mLongClickListener = mLongClickListener;
    }

    public interface OnLongClickListener {
        void OnRowColLongClick(View view, int row, int column);
        void OnItemViewLongClick(View view, int row, int column);
    }

    public static class OnLongClickListenerAdapter implements OnLongClickListener{

        @Override
        public void OnRowColLongClick(View view, int row, int column) {

        }

        @Override
        public void OnItemViewLongClick(View view, int row, int column) {

        }
    }


    public void setOnItemClickListener(OnClickListener columnListener, int... line) {
        mClickListener = columnListener;
        for (int i = 0; i < SIZE; i++) {
            final int finalI = i;
            getItem(i).setOnViewListener(new HybridView.OnViewListener() {
                @Override
                public void onClick(View view, int index, String tag) {
                    if (isVertical()) {
                        mClickListener.OnRowColClick(view, finalI, index);
                    } else {
                        mClickListener.OnRowColClick(view, index, finalI);
                    }
                    mClickListener.OnItemViewClick(view, finalI, index);
                }

                @Override
                public boolean onLongClick(View view, int index, String tag) {
                    if (isVertical()) {
                        mLongClickListener.OnRowColLongClick(view, finalI, index);
                    } else {
                        mLongClickListener.OnRowColLongClick(view, index, finalI);
                    }
                    mLongClickListener.OnItemViewLongClick(view, finalI, index);
                    return true;
                }

                @Override
                public boolean onTouch(View view, int index, String tag, MotionEvent event) {
                    return false;
                }

            }, line);
        }
    }


}
