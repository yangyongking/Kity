package me.windleafy.kity.android.bug;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * 解决java.lang.IndexOutOfBoundsException: Inconsistency detected. Invalid item position 2
 * <p>
 * mRecyclerView.setLayoutManager(new FixBugLinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
 * <p>
 * https://blog.csdn.net/juhua2012/article/details/80002815
 * https://blog.csdn.net/u014769864/article/details/79129654
 * https://www.cnblogs.com/kim-liu/p/7513837.html
 */
public class FixBugLinearLayoutManager extends LinearLayoutManager {

    public FixBugLinearLayoutManager(Context context) {
        super(context);
    }

    public FixBugLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public FixBugLinearLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        try {
            super.onLayoutChildren(recycler, state);
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }
}
