package me.windleafy.kity.java.judge;

/**
 * 元素判断
 */
public final class JudgeKit {

    private JudgeKit() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 是否集合里是否包含element值
     *
     * @param elements
     * @param <E>
     * @return
     */
    @SafeVarargs
    public static <E> boolean contain(E element, E... elements) {
        for (E e : elements) {
            if (element instanceof CharSequence) {
                if (element.equals(e))
                    return true;
            } else {
                if (element == e)
                    return true;
            }
        }
        return false;
    }

    /**
     * 是否所有元素都与element相同
     *
     * @param element
     * @param elements
     * @param <E>
     * @return
     */
    @SafeVarargs
    public static <E> boolean equals(E element, E... elements) {
        for (E e : elements) {
            if (element instanceof CharSequence) {
                if (!element.equals(e))
                    return false;
            } else {
                if (element != e)
                    return false;
            }
        }
        return true;
    }

    /**
     * 是否所有元素都与element相同
     *
     * @param elements
     * @param <E>
     * @return
     */
    @SafeVarargs
    public static <E> boolean allEquals(E... elements) {
        int length = elements.length;
        if (length < 2) {
            return false;
        }
        if (length == 2) {
            return isEqual(elements[0], elements[1]);
        }
        //length > 2
        for (int i = 0; i < length; i++) {
            for (int j = 1; j < length; j++) {
                if (notEqual(elements[i], elements[j])) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 是否所有元素都与element相同
     *
     * @param elements
     * @param <E>
     * @return
     */
    @SafeVarargs
    public static <E> boolean allNotEquals(E... elements) {
        int length = elements.length;
        if (length < 2) {
            return true;
        }
        if (length == 2) {
            return notEqual(elements[0], elements[1]);
        }
        //length > 2
        for (int i = 0; i < length; i++) {
            for (int j = 1; j < length; j++) {
                if (isEqual(elements[i], elements[j])) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 判断元素值是否相同或相等
     *
     * @param element1
     * @param element2
     * @param <E>
     * @return
     */
    public static <E> boolean isEqual(E element1, E element2) {
        if (element1 instanceof CharSequence) {
            if (element1.equals(element2))
                return true;
        } else {
            if (element1 == element2)
                return true;
        }
        return false;
    }

    /**
     * 判断元素值是否不相同或不相等
     *
     * @param element1
     * @param element2
     * @param <E>
     * @return
     */
    public static <E> boolean notEqual(E element1, E element2) {
        return !isEqual(element1, element2);
    }

}
