package me.windleafy.kity.java.number;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import me.windleafy.kity.java.utils.MathKit;

public class NumberKit {

    private NumberKit() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static String number10000(int num) {
        if (num > 10000) {
            DecimalFormat df = new DecimalFormat("0.00");
            return df.format(num / 10000.0f) + "万";
        }
        return String.valueOf(num);
    }

    public static String number(int num) {
        return convertFormat(num, new NumberFormat(10000, 1, "万"));
    }

    public static String numbers(int num) {
        return convert(num, new NumberFormat(10000, 2, "万"), new NumberFormat(100000000, 2, "亿"));
    }

    public static String convertFormat(int num, NumberFormat numberFormat) {
        if (num > numberFormat.getDivisor()) {
            return MathKit.floatCut(1.0f * num / numberFormat.getDivisor(), numberFormat.getDecimals()) + numberFormat.getUnit();
        }
        return String.valueOf(num);
    }

    public static String convert(int num, NumberFormat... numberFormats) {
        List<NumberFormat> list = Arrays.asList(numberFormats);
        Collections.sort(list);
        for (NumberFormat numberFormat : list) {
            if (num > numberFormat.getDivisor())
                return convertFormat(num, numberFormat);
        }
        return String.valueOf(num);
    }

    public static class NumberFormat implements Comparable<NumberFormat> {
        int divisor;
        int decimals;
        String unit;

        public int getDivisor() {
            return divisor;
        }

        public void setDivisor(int divisor) {
            this.divisor = divisor;
        }

        public int getDecimals() {
            return decimals;
        }

        public void setDecimals(int decimals) {
            this.decimals = decimals;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public NumberFormat(int divisor, int decimals, String unit) {
            this.divisor = divisor;
            this.decimals = decimals;
            this.unit = unit;
        }

        @Override
        public int compareTo(NumberFormat o) {
            return o.divisor - divisor;
        }
    }


}
