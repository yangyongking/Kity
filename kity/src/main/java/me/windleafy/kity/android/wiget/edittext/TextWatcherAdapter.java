package me.windleafy.kity.android.wiget.edittext;

import android.text.Editable;
import android.text.TextWatcher;

/**
 * edit_text.addTextChangedListener(new TextWatcherAdapter() {
 *                 @Override
 *                 public void onTextChanged(CharSequence s, int start, int before, int count) {
 *                     edit_count.setText(edit_text.getText().length() + "/" + MAX_COUNT);
 *                 }
 *             });
 */
public abstract class TextWatcherAdapter implements TextWatcher {

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
