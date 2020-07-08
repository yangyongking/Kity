package me.windleafy.kity.android.manager;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Activity管理类
 */
public class ActivityController {

    private static Set<Activity> finishedActivityList = new HashSet<>();

    private static LinkedList<Activity> activityList = new LinkedList<>();

    /**
     * 添加Activity
     *
     * @param activity
     */
    public static void addActivity(Activity activity) {
        activityList.addLast(activity);
    }

    /**
     * 移除顶部Activity
     */
    public static void removeTopActivity() {
        activityList.pollLast();
    }

    /**
     * 移除Activity
     */
    public static void removeActivity(Activity activity) {
        if (activityList.contains(activity))
            activityList.remove(activity);
    }

    /**
     * 标记已被finish的Activity
     * <p>
     * 使用：如果当前Activity被手动finish了，但可能会从其它Activity直接返回，需要在finish后做标记
     * <p>
     * 例：
     * LoginActivity登录成功后会finish，此时调用 {@link ActivityController#markActivity(Activity)} }
     * 在登录时间过久或者登出时调用 {@link ActivityController#backToActivity(Class)} }
     *
     * @param activity
     */
    public static void markActivity(Activity activity) {
        finishedActivityList.add(activity);
    }

    /**
     * 关闭被标记的Activity
     * <p>
     * 使用：在Activity中的onResume中执行
     *
     * @param activity
     */
    @Deprecated
    public static void finishMarkedActivity(Activity activity) {
        boolean isMarkedFinished = finishedActivityList.contains(activity);
        if (isMarkedFinished)
            activity.finish();
    }

    /**
     * 返回到目标targetActivity，移除并finish之上的所有Activity
     * <p>
     * 如果目标Activity已经finish，则必须先实现方法 {@link ActivityController#markActivity(Activity)}
     *
     * @param targetActivityClass 目标Activity
     */
    public static void backToActivity(Class<? extends Activity> targetActivityClass) {
        Log.d("CloseActivity", "targetActivity.getName() = " + targetActivityClass.getName());
        while (true) {
            Activity activity = activityList.peekLast();
            if (activity == null)
                return;
            Log.d("CloseActivity", "activity.getClass().getName() = " + activity.getClass().getName());
            if (activity.getClass().getName().equals(targetActivityClass.getName())){
                //如果目标Activity已经被标记finish了，需要重启新的目标Activity
                Log.d("CloseActivity", "finishedActivityList = " + finishedActivityList.toString());
                if (finishedActivityList.contains(activity)){
                    Activity targetActivity = activityList.pollLast();
                    Activity underTargetActivity = activityList.pollLast();
                    if (underTargetActivity != null){
                        //重新启动目标Activity
                        underTargetActivity.startActivity(targetActivity.getIntent());
                        finishedActivityList.remove(activity);
                    }
                }
                return;
            }
            activityList.pollLast().finish();
        }
    }


    /**
     * 退出程序
     *
     * @param ctx
     */
    public static void exitAllActivity(Context ctx) {
        // 关闭所有Activity
        while (true) {
            Activity activity = activityList.pollLast();
            if (activity == null)
                return;
            activity.finish();
        }
    }


}
