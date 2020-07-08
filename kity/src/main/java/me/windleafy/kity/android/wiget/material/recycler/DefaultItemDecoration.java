package me.windleafy.kity.android.wiget.material.recycler;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;

import me.windleafy.kity.R;

/**
 * RecyclerView分隔线
 */
public class DefaultItemDecoration extends DividerItemDecoration {

    public DefaultItemDecoration(Context context) {
        super(context, DividerItemDecoration.VERTICAL);
        setDrawable(ContextCompat.getDrawable(context, R.drawable.kity_line_divider_1px));
    }

    public DefaultItemDecoration(Context context,int drawable) {
        super(context, DividerItemDecoration.VERTICAL);
        setDrawable(ContextCompat.getDrawable(context, drawable));
    }

}
