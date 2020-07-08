package me.windleafy.kity.android.view.matrix;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by JOUAV on 2015/6/8.
 */
public abstract class ViewMatrixHelper {

    protected ViewMatrix mViewMatrix;

    protected ViewMatrixHelper(ViewMatrix viewMatrix) {
        mViewMatrix = viewMatrix;
    }

    protected void setView(int row, int col, Object object) {
        View view = mViewMatrix.getView(row, col);
        if (view instanceof TextView) {
            ((TextView) view).setText((String) object);
        }
        if (view instanceof Button) {
            ((Button) view).setText((String) object);
        }
        if (view instanceof ImageView) {
            ((ImageView) view).setImageResource((Integer) object);
        }
    }

    protected View getView(int row, int col) {
        return mViewMatrix.getView(row, col);
    }
}
