package me.windleafy.kity.java.utils;

import android.text.TextUtils;

public class StringKit {

    private StringKit() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 字符串为NULL或者""时，返回默认值，否则返回字符串本身
     *
     * @param str 字符串
     * @param def 当字符串为空或者为NULL时的返回值
     * @return
     */
    public static String emptyOrNull(String str, String def) {
        return TextUtils.isEmpty(str) ? def : str;
    }

}
