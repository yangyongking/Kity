package me.windleafy.kity.java.utils;

import java.math.BigDecimal;

public class MathKit {

    private MathKit() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }


    /**
     * 精度截取
     *
     * @param value  值
     * @param retain 保留小数点后位数
     * @return
     */
    public static float floatCut(float value, int retain) {
        return new BigDecimal(value).setScale(retain, BigDecimal.ROUND_HALF_UP).floatValue();
    }

    /**
     * 精度截取
     *
     * @param value  值
     * @param retain 保留小数点后位数
     * @return
     */
    public static double doubleCut(double value, int retain) {
        return new BigDecimal(value).setScale(retain, BigDecimal.ROUND_HALF_UP).doubleValue();
    }


    /**
     * 查找最大值
     */
    public static int max(int... nums) {
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            max = Math.max(max, num);
        }
        return max;
    }

    /**
     * 查找最大值
     */
    public static long max(long... nums) {
        long max = Integer.MIN_VALUE;
        for (long num : nums) {
            max = Math.max(max, num);
        }
        return max;
    }

    /**
     * 查找最大值
     */
    public static double max(double... nums) {
        double max = Integer.MIN_VALUE;
        for (double num : nums) {
            max = Math.max(max, num);
        }
        return max;
    }

    /**
     * 查找最大值
     */
    public static float max(float... nums) {
        float max = Integer.MIN_VALUE;
        for (float num : nums) {
            max = Math.max(max, num);
        }
        return max;
    }

    /**
     * 查找最小值
     */
    public static int min(int... nums) {
        int min = Integer.MAX_VALUE;
        for (int num : nums) {
            min = Math.min(min, num);
        }
        return min;
    }

    /**
     * 查找最小值
     */
    public static long min(long... nums) {
        long min = Integer.MAX_VALUE;
        for (long num : nums) {
            min = Math.min(min, num);
        }
        return min;
    }

    /**
     * 查找最小值
     */
    public static double min(double... nums) {
        double min = Integer.MAX_VALUE;
        for (double num : nums) {
            min = Math.min(min, num);
        }
        return min;
    }

    /**
     * 查找最小值
     */
    public static float min(float... nums) {
        float min = Integer.MAX_VALUE;
        for (float num : nums) {
            min = Math.min(min, num);
        }
        return min;
    }

}
