package me.windleafy.kity.android.wiget.edittext;

import android.content.Context;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;

import java.math.BigDecimal;

/**
 * 小数Edittext
 * <p>
 * DecimalEditText mEditText = viewHolder.getView(R.id.reward_edit);
 * mEditText.setDecimalDigits(2);
 * mEditText.setRange(1, 10000);
 */
public class DecimalEditText extends android.support.v7.widget.AppCompatEditText implements TextWatcher {

    private String mBeforeChangeString;
    private int mBeforeChangeSelect;
    private String mTextString;

    private int mDecimalDigits = 2; //小数位数

    private boolean isInnerChange = false;

    private double min = Double.MIN_VALUE;
    private double max = Double.MAX_VALUE;


    public DecimalEditText(Context context) {
        super(context);
        init(context);
    }

    public DecimalEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public DecimalEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private TextWatcher mListener;

    public void setTextChangedListener(TextWatcher watcher) {
        mListener = watcher;
    }

    private void init(Context context) {
        //只设置InputType.TYPE_NUMBER_FLAG_DECIMAL是无法实现只能输入数字和小数点的，
        //必须InputType.TYPE_CLASS_NUMBER 和InputType.TYPE_NUMBER_FLAG_DECIMAL同时设置才可以
        setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);

        initListener();
    }

    private void initListener() {
        addTextChangedListener(this);
    }


    /**
     * 删除光标左侧字符
     *
     * @param editText
     */
    private void deleteCursorLeftChar(EditText editText) {
        String text = editText.getText().toString();
        int length = text.length();

        if (TextUtils.isEmpty(text))
            return;
        int selection = editText.getSelectionEnd();
        if (selection == 0)
            return;
        String text1 = text.substring(0, selection - 1);
        String text2 = text.substring(selection, text.length());
        String newText = text1 + text2;
        editText.setText(newText);//递归，又触发了文字变化监听

        String current = editText.getText().toString();
        int curLength = current.length();
        int diffLength = length - curLength;
        editText.setSelection(selection - diffLength);
    }

    /**
     * 设置小数位数
     *
     * @param decimalDigits
     */
    public void setDecimalDigits(int decimalDigits) {
        mDecimalDigits = decimalDigits;
    }

    /**
     * 设置输入范围
     *
     * @param min
     * @param max
     */
    public void setRange(double min, double max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        if (isInnerChange) {
            return;
        }
        mBeforeChangeString = getText().toString();
        mBeforeChangeSelect = getSelectionEnd();
        if (mListener != null)
            mListener.beforeTextChanged(s, start, count, after);
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

        if (isInnerChange) {
            return;
        }

        //判断输入范围
        mTextString = getText().toString();
        Double decimal;
        try {
            decimal = Double.valueOf(mTextString);
        } catch (Exception e) {
            //不是有效小数
            return;
        }
        if (decimal < min || decimal > max) {
            isInnerChange = true;
            setText(mBeforeChangeString);
            setSelection(mBeforeChangeSelect);
            return;
        }

        //小数判断
        mTextString = getText().toString();
        //多个小数点判断
        String temp = " " + mTextString + " ";
        if (temp.split("\\.").length > 2) {
            //删除刚输入的小数点
            deleteCursorLeftChar(DecimalEditText.this);
        }


        //有1个小数点，设置小数位为mDecimalDigits位长度
        mTextString = getText().toString();
        if (mTextString.contains(".")) {
            int pointIndex = mTextString.indexOf(".");
            //小数位大于mDecimalDigits位
            if (length() - pointIndex > mDecimalDigits + 1) {
                //是否输入的为小数点
                boolean isInputPoint = (getSelectionEnd() - pointIndex) == 1;
                if (isInputPoint) {
                    String newExchange = new BigDecimal(mTextString).setScale(mDecimalDigits, BigDecimal.ROUND_DOWN).toString();
                    int select = getSelectionEnd();
                    setText(newExchange);
                    setSelection(select);//恢复光标位置
                } else {
                    deleteCursorLeftChar(DecimalEditText.this);
                }
            }

        }

        mTextString = getText().toString();
        if (mTextString.startsWith("0") && !mTextString.startsWith("0.") && mTextString.length() > 1 && getSelectionEnd() != 1) {
            mTextString = mTextString.replaceFirst("0", "");
            int selection = getSelectionEnd();
            setText(mTextString);
            if (selection > 0)
                setSelection(selection - 1);
        }

        //输入的数字用于计算，先去掉尾部的小数点，否则报错
//                if (mTextString.endsWith("."))
//                    mTextString = mTextString.replace(".", "");

        if (mListener != null)
            mListener.onTextChanged(s, start, before, count);
    }

    @Override
    public void afterTextChanged(Editable s) {
        if (isInnerChange) {
            isInnerChange = false;
            return;
        }
        if (mListener != null)
            mListener.afterTextChanged(s);
    }
}
