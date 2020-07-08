package me.windleafy.kity.android.base;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 *
 */
public class CloseActivityClass {

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
     *
     * @param activity
     */
    public static void markActivity(Activity activity) {
        finishedActivityList.add(activity);
    }


    /**
     * finish并标记Activity
     * @param activity
     */
    public static void finishAndMarkActivity(Activity activity) {
        activity.finish();
        markActivity(activity);
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
        boolean isFinished = finishedActivityList.contains(activity);
        if (isFinished)
            activity.finish();
    }

    /**
     * 返回到targetActivity，移除并finish之上的所有Activity
     * 使用本方法钱必须实现方法{link{markActivity}}
     *
     * @param targetActivityClass 目标Activity，之前必须开启过
     */
    public static void backToActivity(Class<? extends Activity> targetActivityClass) {
        Log.d("CloseActivity", "targetActivity.getName() = " + targetActivityClass.getName());
        //先检测是否存在targetActivity
        while (true) {
            Activity activity = activityList.peekLast();
            if (activity == null) {
                Log.d("CloseActivity", "activity is null");
                return;
            }
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



    public static void exitAllActivity(Context ctx) {
        // 关闭所有Activity
        while (true) {
            Activity activity = activityList.pollLast();
            if (activity == null)
                return;
            activity.finish();
        }
    }

    public static void exitLogin(Context ctx) {
        exitAllActivity(ctx);
    }



}