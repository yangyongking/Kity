package me.windleafy.kity.android.utils;

import android.util.SparseArray;

import me.windleafy.kity.java.timer.Timery;

/**
 * 点击相关的辅助类
 */
public class ClickKit {

    /**
     * 默认超时时间
     */
    public static final long TIMEOUT = 1000;

    private ClickKit() {
        /**cannot be instantiated **/
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    private static SparseArray<Click> map = new SparseArray<>();

    /**
     * 点击学习
     */
    public static class Click {

        private boolean first = true;//是否是第一次点击
        private Time time = new Time();

        public boolean isFirst() {
            return first;
        }

        public void setFirst(boolean first) {
            this.first = first;
        }

        public Time getTime() {
            return time;
        }

        public void setTime(Time time) {
            this.time = time;
        }
    }

    /**
     *
     */
    public static class Time {

        private long firstTime;
        private long lastTime;
        private long currentTime;
        private Timery timery;

        Time() {
            long time = System.currentTimeMillis();
            firstTime = time;
            lastTime = time;
            currentTime = time;
//            timery = new Timery();
        }

        public long getFirstTime() {
            return firstTime;
        }

        public void setFirstTime(long firstTime) {
            this.firstTime = firstTime;
        }

        public long getLastTime() {
            return lastTime;
        }

        public void setLastTime(long lastTime) {
            this.lastTime = lastTime;
        }

        public long getCurrentTime() {
            return currentTime;
        }

        public void setCurrentTime(long currentTime) {
            this.currentTime = currentTime;
        }

        public Timery getTimery() {
            return timery;
        }

        public void setTimery(Timery timery) {
            this.timery = timery;
        }

        /**
         * 更新
         */
        public void updateLastTime() {
            lastTime = currentTime;
            currentTime = System.currentTimeMillis();
        }

        public long getFirstInterval() {
            return currentTime - firstTime;
        }

        public long getLastInterval() {
            return currentTime - lastTime;
        }
    }

    public static class TimeOutResult {
        private boolean firstTimeOutResult;
        private boolean lastTimeOutResult;

        public TimeOutResult(boolean firstTimeOutResult, boolean lastTimeOutResult) {
            this.firstTimeOutResult = firstTimeOutResult;
            this.lastTimeOutResult = lastTimeOutResult;
        }

        public boolean isFirstTimeOutResult() {
            return firstTimeOutResult;
        }

        public void setFirstTimeOutResult(boolean firstTimeOutResult) {
            this.firstTimeOutResult = firstTimeOutResult;
        }

        public boolean isLastTimeOutResult() {
            return lastTimeOutResult;
        }

        public void setLastTimeOutResult(boolean lastTimeOutResult) {
            this.lastTimeOutResult = lastTimeOutResult;
        }
    }

    /**
     * 点击间隔是否过快（小于指定时间）
     */
    public static boolean isFastClick(Object obj) {
        return isFastClick(obj, TIMEOUT);
    }

    /**
     * 点击间隔是否过快（小于指定时间）
     */
    public static boolean isFastClick(Object obj, long time) {
        return !isFirstClickAndLastTimeOut(obj, time);
    }

    /**
     * 第一次点击或者与上次点击时间间隔是否超时
     *  @return 第一次点击和超时点击才返回true
     */
    private static boolean isFirstClickAndLastTimeOut(Object obj, long time) {
        return isLastTimeOut(obj, time) || isFirstClick(obj);//判断顺序不能变
    }


    /**
     * 与第一次点击时间间隔是否超时
     */
    public static boolean isFirstTimeOut(Object obj) {
        return isFirstTimeOut(obj, TIMEOUT);
    }

    /**
     * 与第一次点击时间间隔是否超时
     */
    public static boolean isFirstTimeOut(int code) {
        return isFirstTimeOut(code, TIMEOUT);
    }

    /**
     * 与第一次点击时间间隔是否超时
     */
    public static boolean isFirstTimeOut(Object obj, long time) {
        return isFirstTimeOut(obj.hashCode(), time, null);
    }

    /**
     * 与第一次点击时间间隔是否超时
     */
    public static boolean isFirstTimeOut(int code, long time) {
        return isFirstTimeOut(code, time, null);
    }

    /**
     * 与第一次点击时间间隔是否超时
     */
    public static boolean isFirstTimeOut(Object obj, long time, FirstTimeOutCallback callback) {
        return isFirstTimeOut(obj.hashCode(), time, callback);
    }

    /**
     * 与第一次点击时间间隔是否超时
     */
    public static boolean isFirstTimeOut(final int code, final long time, final FirstTimeOutCallback firstTimeOutCallback) {
        TimeOutResult timeOutResult = isTimeOut(code, time, firstTimeOutCallback == null ? null : new TimeOutOutCallback() {
            @Override
            public void firstTimeOut(boolean isTimeOut) {
                firstTimeOutCallback.firstTimeOut(isTimeOut);
            }

            @Override
            public void onClickFirstTimeOut(boolean isTimeOut) {
                firstTimeOutCallback.onClickFirstTimeOut(isTimeOut);
            }

            @Override
            public void lastTimeOut(boolean isTimeOut) {
            }

            @Override
            public void onClickLastTimeOut(boolean isTimeOut) {

            }
        });
        return timeOutResult.isFirstTimeOutResult();
    }


    /**
     * 与上次点击时间间隔是否超时
     */
    public static boolean isLastTimeOut(Object obj) {
        return isLastTimeOut(obj, TIMEOUT);
    }

    /**
     * 与上次点击时间间隔是否超时
     */
    public static boolean isLastTimeOut(int code) {
        return isLastTimeOut(code, TIMEOUT);
    }

    /**
     * 与上次点击时间间隔是否超时
     */
    public static boolean isLastTimeOut(Object obj, long time) {
        return isLastTimeOut(obj.hashCode(), time, null);
    }

    /**
     * 与上次点击时间间隔是否超时
     */
    public static boolean isLastTimeOut(int code, long time) {
        return isLastTimeOut(code, time, null);
    }

    /**
     * 与上次点击时间间隔是否超时
     */
    public static boolean isLastTimeOut(Object obj, long time, LastTimeOutCallback callback) {
        return isLastTimeOut(obj.hashCode(), time, callback);
    }

    /**
     * 与上次点击时间间隔是否超时
     */
    public static boolean isLastTimeOut(final int code, final long time, final LastTimeOutCallback lastTimeOutCallback) {
        TimeOutResult timeOutResult = isTimeOut(code, time, lastTimeOutCallback == null ? null : new TimeOutOutCallback() {
            @Override
            public void firstTimeOut(boolean isTimeOut) {

            }

            @Override
            public void onClickFirstTimeOut(boolean isTimeOut) {

            }

            @Override
            public void lastTimeOut(boolean isTimeOut) {
                lastTimeOutCallback.lastTimeOut(isTimeOut);
            }

            @Override
            public void onClickLastTimeOut(boolean isTimeOut) {
                lastTimeOutCallback.onClickLastTimeOut(isTimeOut);
            }
        });
        return timeOutResult.isLastTimeOutResult();
    }


    /**
     * 与第一次和上一次点击时间间隔是否超时
     *
     * @param obj  对象
     * @param time 定时(毫秒)
     * @return
     */
    public static TimeOutResult isTimeOut(Object obj, long time) {
        return isTimeOut(obj.hashCode(), time, null);
    }

    /**
     * 与第一次和上一次点击时间间隔是否超时
     *
     * @param code
     * @param time 定时(毫秒)
     * @return
     */
    public static TimeOutResult isTimeOut(int code, long time) {
        return isTimeOut(code, time, null);
    }

    /**
     * 与第一次和上一次点击时间间隔是否超时
     *
     * @param obj  对象
     * @param time 定时(毫秒)
     * @return
     */
    public static TimeOutResult isTimeOut(Object obj, long time, TimeOutOutCallback callback) {
        return isTimeOut(obj.hashCode(), time, callback);
    }

    /**
     * 与第一次和上一次点击时间间隔是否超时
     *
     * @param code Id
     * @param time 定时(毫秒)
     * @return
     */
    public static TimeOutResult isTimeOut(final int code, final long time, final TimeOutOutCallback callback) {

        Click click = map.get(code);
        if (click == null) {
            click = new Click();
            click.setFirst(true);
            map.put(code, click);
        } else {
            click.setFirst(false);
        }

        click.getTime().updateLastTime();

        final boolean isFirstOverTime = click.getTime().getFirstInterval() > time;
        final boolean isLastOverTime = click.getTime().getLastInterval() > time;

        if (callback != null) {
            Timery timery = click.getTime().getTimery();
            if (timery == null) {
                click.getTime().setTimery(new Timery(new Timery.Task() {
                    @Override
                    public void end(long time) {
                        callback.firstTimeOut(true);
                        callback.lastTimeOut(true);
                        //成功触发则删除
                        clear(code);
                    }
                }, (int) time).start());
            } else {
                timery.restart();
                callback.onClickFirstTimeOut(isFirstOverTime);
                callback.onClickLastTimeOut(isLastOverTime);
            }
        }


        return new TimeOutResult(isFirstOverTime, isLastOverTime);

    }

    /**
     * 是否是第一次点击
     *
     * @param obj
     */
    public static boolean isFirstClick(Object obj) {
        return isFirstClick(obj.hashCode());
    }

    /**
     * 是否是第一次点击
     *
     * @param code
     */
    public static boolean isFirstClick(final int code) {
        Click click = map.get(code);
        if (click == null) {
            click = new Click();
            click.setFirst(true);
            map.put(code, click);
            return true;
        } else {
            return click.isFirst();
        }
    }

    /**
     * 重置点击历史
     */
    public static void clear(Object obj) {
        clear(obj.hashCode());
    }

    /**
     * 重置点击历史
     */
    public static void clear(int code) {
        Click click = map.get(code);
        if (click != null) {
            Timery timery = click.getTime().getTimery();
            if (timery != null) {
                timery.destroy();
            }
        }
        map.remove(code);
    }

    public interface TimeOutOutCallback extends FirstTimeOutCallback, LastTimeOutCallback {
    }

    public interface FirstTimeOutCallback {
        void firstTimeOut(boolean isTimeOut);

        void onClickFirstTimeOut(boolean isTimeOut);
    }

    public interface LastTimeOutCallback {
        void lastTimeOut(boolean isTimeOut);

        void onClickLastTimeOut(boolean isTimeOut);
    }

}