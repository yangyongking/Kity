package me.windleafy.kity.android.wiget.material.recycler;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * RecyclerView行间隔
 * usage:recycleView.addItemDecoration(new SpaceItemDecoration(Kit.dp2px(mContext, 14)));
 */
public class SpaceItemDecoration extends RecyclerView.ItemDecoration {

    private int mSpace;
    private int mSpaceTop;
    private int mSpaceBottom;
    private int mSpaceLeft;
    private int mSpaceRight;

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int position = parent.getChildAdapterPosition(view);
        outRect.left = mSpaceLeft;
        outRect.right = mSpaceRight;
        outRect.top = position == 0 ? mSpaceTop : mSpace;
        outRect.bottom = mSpaceBottom;
    }

    /**
     * @param spacePX 间隔，单位px
     */
    public SpaceItemDecoration(int spacePX) {
        mSpace = spacePX;
    }

    /**
     * @param spacePX
     * @param spaceTopPX
     * @param spaceBottomPX
     * @param spaceLeftPX
     * @param spaceRightPX
     */
    public SpaceItemDecoration(int spacePX, int spaceTopPX, int spaceBottomPX, int spaceLeftPX, int spaceRightPX) {
        mSpace = spacePX;
        mSpaceTop = spaceTopPX;
        mSpaceBottom = spaceBottomPX;
        mSpaceLeft = spaceLeftPX;
        mSpaceRight = spaceRightPX;
    }

    /**
     * 设置上下左右间隔，
     *
     * @param spaceTopPX
     * @param spaceBottomPX
     * @param spaceLeftPX
     * @param spaceRightPX
     */
    public SpaceItemDecoration(int spaceTopPX, int spaceBottomPX, int spaceLeftPX, int spaceRightPX, boolean isVertial) {
        mSpace = isVertial ? spaceTopPX + spaceBottomPX : spaceLeftPX + spaceRightPX;
        mSpaceTop = spaceTopPX;
        mSpaceBottom = spaceBottomPX;
        mSpaceLeft = spaceLeftPX;
        mSpaceRight = spaceRightPX;
    }

    /**
     * @param spacePX        间隔，单位px
     * @param spaceAllDirect 上下左右都设置为space宽度
     */
    public SpaceItemDecoration(int spacePX, boolean spaceAllDirect) {
        mSpace = spacePX;
        mSpaceTop = spaceAllDirect ? spacePX : 0;
        mSpaceBottom = spaceAllDirect ? spacePX : 0;
        mSpaceLeft = spaceAllDirect ? spacePX : 0;
        mSpaceRight = spaceAllDirect ? spacePX : 0;
    }

}
