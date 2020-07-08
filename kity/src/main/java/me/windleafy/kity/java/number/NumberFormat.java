package me.windleafy.kity.java.number;


public class NumberFormat {

    /**
     * 保留1位小数
     */
    public static String p1(double number) {
        return String.format("%.1f", number);
    }

    /**
     * 保留2位小数
     */
    public static String p2(double number) {
        return String.format("%.2f", number);
    }

    /**
     * 保留3位小数
     */
    public static String p3(double number) {
        return String.format("%.3f", number);
    }

    /**
     * 保留4位小数
     */
    public static String p4(double number) {
        return String.format("%.4f", number);
    }

    public static String p6(double number) {
        return String.format("%.6f", number);
    }

    public static String p8(double number) {
        return String.format("%.8f", number);
    }


    /**
     * 保留1位小数
     */
    public static String p1(float number) {
        return String.format("%.1f", number);
    }

    /**
     * 保留2位小数
     */
    public static String p2(float number) {
        return String.format("%.2f", number);
    }

    /**
     * 保留3位小数
     */
    public static String p3(float number) {
        return String.format("%.3f", number);
    }

    /**
     * 保留4位小数
     */
    public static String p4(float number) {
        return String.format("%.4f", number);
    }

    public static String p6(float number) {
        return String.format("%.6f", number);
    }

    public static String p8(float number) {
        return String.format("%.8f", number);
    }

}
