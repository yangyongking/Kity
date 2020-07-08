package me.windleafy.kity.java.judge;

/**
 * 布尔值判断
 */
public final class BoolKit {

    private BoolKit() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 至少有1个元素为真
     *
     * @param bools
     * @return
     */
    public static boolean containTrue(Boolean... bools) {
        return JudgeKit.contain(true, bools);
    }

    /**
     * 至少有1个元素为假
     *
     * @param bools
     * @return
     */
    public static boolean containFalse(Boolean... bools) {
        return JudgeKit.contain(false, bools);
    }

    /**
     * 所有元素为true
     *
     * @param bools
     * @return
     */
    public static boolean allTrue(Boolean... bools) {
        return JudgeKit.equals(true, bools);
    }

    /**
     * 所有元素为false
     *
     * @param bools
     * @return
     */
    public static boolean allFalse(Boolean... bools) {
        return JudgeKit.equals(false, bools);
    }

    /**
     * 所有元素两两相等
     *
     * @param bools
     * @return
     */
    public static boolean allEqual(Boolean... bools) {
        return JudgeKit.equals(bools[0], bools);
    }

    /**
     * 所有元素两两不相等
     *
     * @param bools
     * @return
     */
    public static boolean allNotEqual(Boolean... bools) {
        return JudgeKit.equals(bools[0], bools);
    }



}
