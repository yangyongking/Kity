package me.windleafy.kity.java.timer;

import android.support.annotation.NonNull;

import java.util.Timer;
import java.util.TimerTask;

import me.windleafy.kity.java.utils.TimeKit;

/**
 * [多功能定时器]
 * 基本功能类似Timer：延迟执行，间隔执行
 * 定时器可复用，功能包括：暂停/恢复，停止/启动，重启，摧毁（不可重启）
 * <p>
 * 定时任务分类：
 * 1> 定时任务T1，定时D1：
 * 定时时间D1到，执行任务T1，然后结束
 * 2> 间隔任务T2，延迟D2，间隔P2：
 * 延迟D2后执行任务T2，然后过了P2时间执行任务T2，又过了P2时间执行任务T2。。。无限循环
 * 3> 间隔任务T3，结束任务E3，延迟D3，间隔P3，次数C3：（注：最后一次任务T3与任务E3相隔P3时间）
 * 延迟时间D3后执行任务T3，然后过了P3时间执行任务T3，又过了P3时间执行任务T3。。。循环C3次，循环结束后执行E3。。
 * <p>
 * 情况：
 * 延迟为0：初始化时取消参数设置，启动定时器后会立即执行间隔任务T
 * <p>
 * 方法：
 * start   启动
 * pause   暂停
 * stop    停止
 * restart 重启
 * destroy 摧毁
 * <p>
 * 回调：
 * prepare 准备
 * period  间隔触发
 * end     定时结束时触发   停止或摧毁不会触发
 * <p>
 * 说明：所有时间单位为：毫秒
 */
public class Timery {

    private Timer timer;
    private TimerTask timertask;
    private Task task;

    private long delay;//延迟
    private long period;//间隔
    private long num;//次数

    private long startTime;//启动时间

    private int counter = 0;//循环任务执行次数计数
    /**
     * 是否正在运行
     */
    private boolean isRunning = false;
    /**
     * 是否暂停
     */
    private boolean isPaused = false;
    private long pauseDelay;//暂停时更新延迟时间

    /**
     * @param task  任务
     * @param delay 延迟
     */
    public Timery(@NonNull Task task, int delay) {
        this(task, delay, 0);
    }

    /**
     * @param task   任务
     * @param delay  延迟
     * @param period 周期
     */
    public Timery(@NonNull Task task, int delay, int period) {
        this(task, delay, period, 0);
    }

    /**
     * @param task   任务
     * @param delay  延迟
     * @param period 周期
     * @param num    重复次数
     */
    public Timery(@NonNull Task task, int delay, int period, int num) {
        init(task, delay, period, num);
    }

    /**
     * 初始化
     *
     * @param task
     * @param delay
     * @param period
     * @param num
     */
    private void init(@NonNull Task task, int delay, int period, int num) {
        init();//共有初始化
        setTask(task);
        setDelay(delay);
        setPeriod(period);
        setNum(num);
    }

    /**
     * 设置定时任务
     *
     * @param task
     * @return
     */
    public Timery setTask(@NonNull Task task) {
        this.task = task;
        return this;
    }

    public Timery setDelay(long delay) {
        this.delay = delay > 0 ? delay : 0;
        return this;
    }

    public Timery setPeriod(long period) {
        this.period = period > 0 ? period : 0;
        return this;
    }

    public Timery setNum(long num) {
        this.num = num > 0 ? num : 0;
        return this;
    }

    private void init() {
        timer = new Timer();
    }

    public Timery() {
        init();
    }

    /**
     * 定时器是否正在工作
     */
    public boolean isRunning() {
        return isRunning;
    }


    /**
     * 改变任务，定时器正在工作时不会成功
     *
     * @param task
     * @return 是否成功设置
     */
    public boolean changeTask(Task task) {
        if (isRunning) {
            return false;
        } else {
            setTask(task);
            return true;
        }
    }

    /**
     * 销毁，不能再启动
     */
    public void destroy() {
        stop();
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }


    /**
     * 停止，可以start()再次启动
     */
    public Timery stop() {
        if (timertask != null) {
            timertask.cancel();
            timertask = null;
        }
        counter = 0;
        isRunning = false;
        isPaused = false;
        return this;
    }


    /**
     * 暂停，可以start()恢复启动
     * 恢复启动后，再次执行为period或者delay
     */
    public Timery pause() {

        if (!isRunning)
            return this;

        if (isPaused)
            return this;

        isRunning = false;
        isPaused = true;

        //保存参数状态
        if (timertask != null) {
            timertask.cancel();
            timertask = null;
        }

        //已用时时间
        long pastTime = System.currentTimeMillis() - startTime;

        //调整倒计时

        //无间隔
        if (period == 0) {
            pauseDelay = delay - pastTime; //减去已用时时间
        }
        //有间隔
        else {
            if (delay >= pastTime) {
                pauseDelay = delay - pastTime;
            } else {
                pauseDelay = period - (pastTime - delay) % period;
            }
        }

        return this;

    }


    /**
     * 重新开始，先停止再启动
     */
    public Timery restart() {
        stop();
        start();
        return this;
    }


    /**
     * 启动
     */
    public Timery start() {

        //已经启动了就不再启动
        if (isRunning && !isPaused)
            return this;

        //检查参数
        if (!check())
            return this;

        //开始执行
        isRunning = true;

        //初始化任务
        if (timertask == null)
            createTimerTask();

        //非暂停才调用
        if (!isPaused) {
            startTime = System.currentTimeMillis();
            task.prepare();
        }

        //无间隔
        if (period == 0) {
            timer.schedule(timertask, isPaused ? pauseDelay : delay);
        }
        //有间隔
        else {
            timer.schedule(timertask, isPaused ? pauseDelay : delay, period);
        }

        //复位
        isPaused = false;

        return this;
    }

    /**
     * 创建新任务
     */
    private void createTimerTask() {
        timertask = new TimerTask() {
            @Override
            public void run() {
                counter++;
                //当period不为零时才会执行周期回调task.period(...)
                //无限循环条件：num == 0
                //次数限制条件：counter <= num
                if (period != 0 && (num == 0 || counter <= num)) {
                    task.period(System.currentTimeMillis() - startTime, counter);
                }
                //此时为非循环且延迟执行 period == 0 && delay > 0
                //或者为循环但循环次数执行完毕 period > 0 && counter > num
                else {
                    task.end(System.currentTimeMillis() - startTime);
                    stop();
                }
            }
        };
    }

    /**
     * 参数检查
     *
     * @return
     */
    private boolean check() {
        //异常
        if (timer == null || task == null)
            return false;
        //参数异常
        if (delay == 0 && period == 0)
            return false;
        if (period == 0 && num != 0)
            return false;
        //正常
        return true;
    }

    /**
     * 定时器回调接口
     */
    public interface ITask {
        /**
         * 准备
         */
        void prepare();

        /**
         * 周期执行
         *
         * @param time 执行时间
         * @param num  循环第几次 (1-n)
         */
        void period(long time, int num);

        /**
         * 执行结束
         *
         * @param time 定时执行总时间
         */
        void end(long time);
    }

    /**
     * 适配接口
     */
    public static abstract class Task implements ITask {

        /**
         * 准备
         */
        @Override
        public void prepare() {
        }

        /**
         * 周期执行
         *
         * @param time 执行时间
         * @param num  循环第几次 (1-n)
         */
        @Override
        public void period(long time, int num) {
        }

        /**
         * 执行结束
         *
         * @param time 定时执行总时间
         */
        @Override
        public void end(long time) {
        }
    }

    private void usage() {
//        Timery timery = new Timery(new Timery.Task() {
//            @Override
//            public void prepare() {
//            }
//
//            @Override
//            public void period(long currentTime, int num) {
//            }
//
//            @Override
//            public void end(long currentTime) {
//            }
//        }, 3000);
//        timery.start();
    }

}
