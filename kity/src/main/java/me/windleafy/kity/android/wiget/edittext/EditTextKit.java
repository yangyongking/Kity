package me.windleafy.kity.android.wiget.edittext;

import android.text.InputFilter;
import android.text.Spanned;
import android.view.ViewGroup;
import android.widget.EditText;

public class EditTextKit {

    /**
     * 禁止EditText输入空格
     *
     * @param editText
     */
    public static void setEditTextInhibitInputSpace(EditText editText) {
        InputFilter filter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                return source.equals(" ") ? "" : null;
            }
        };
        editText.setFilters(new InputFilter[]{filter});
    }

    /**
     * 最开始让光标不闪烁
     */
    public static void clearCursorBlinkAtFirst(EditText editText) {
        ViewGroup parent = (ViewGroup) editText.getParent();
        parent.setFocusable(true);
        parent.setFocusableInTouchMode(true);
    }

}
