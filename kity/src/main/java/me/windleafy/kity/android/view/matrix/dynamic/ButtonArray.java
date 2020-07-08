package me.windleafy.kity.android.view.matrix.dynamic;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutCompat;
import android.widget.Button;

import me.windleafy.kity.android.view.matrix.ViewArray;


/**
 * Created by JOUAV on 2015/6/4.
 */
public class ButtonArray extends ViewArray<Button> {
    public ButtonArray(Activity activity, int container, int size) {
        super(activity, container, size);
    }

    public ButtonArray(Activity activity, int container, int size, float[] width_height, int unit) {
        super(activity, container, size, width_height, 0, unit);
    }

    public ButtonArray(Activity activity, int container, int size, int orient, int x, int y) {
        super(activity,container, size, orient, x, y);
    }

    @Override
    public Button createView(@LinearLayoutCompat.OrientationMode int gravity) {
        return new Button(mActivity);
    }

    public ButtonArray init(String[] texts) {
        for (int i = 0; i < SIZE; i++) {
            getItem(i).setText(texts[i]);
        }
        return this;
    }

    /**
     * usage
     */
    private void initButtonArray(){
//        String[] text = new String[]{"b1", "b2", "b3", "b4", "b5"};
//        ButtonArray buttonArray = new ButtonArray(this, R.id.array_container, text.length)
//                .init(text);
//        buttonArray.setItemOnClickListener(new ViewArray.ItemOnClickListener() {
//            @Override
//            public void onItemClick(int i) {
//                switch (i) {
//                    case 0:
//                        break;
//                    case 1:
//                        break;
//                    case 2:
//                        break;
//                    case 3:
//                        break;
//
//                }
//            }
//        });
    }


}
