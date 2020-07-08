package me.windleafy.kity.android.utils;

import android.support.v7.widget.LinearLayoutCompat;
import android.widget.LinearLayout;

public class LayoutKit {

    private LayoutKit() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }


    /**
     * 获取LinearLayout中Orient相反的朝向
     *
     * @param orient
     * @return
     */
    public static @LinearLayoutCompat.OrientationMode int getOppositeOrientation(@LinearLayoutCompat.OrientationMode int orient){
        if (orient == LinearLayout.HORIZONTAL){
            return LinearLayout.VERTICAL;
        }else if (orient == LinearLayout.VERTICAL) {
            return LinearLayout.HORIZONTAL;
        }
        return orient;
    }

}
