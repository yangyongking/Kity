package me.windleafy.kity.android.base;

import android.app.Activity;
import android.app.Application;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;

import me.windleafy.kity.BuildConfig;
import me.windleafy.kity.android.tool.log.CatLog;
import me.windleafy.kity.android.tool.log.SdLog;
import me.windleafy.kity.android.wiget.toast.Toaster;


public abstract class BaseApplication extends Application {

    public SharedPreferences mSharedPreferences;
    public SharedPreferences.Editor mEditor;

    private boolean isLog = false;

    public boolean isLog() {
        return isLog;
    }

    public void setLog(boolean log) {
        isLog = log;
    }

    @Override
    public void onCreate() {
        log("onCreate");
        super.onCreate();
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();

        Toaster.init(this);

        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });
    }


    @Override
    public void onTerminate() {
        log("onTerminate");
        super.onTerminate();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        log("onConfigurationChanged");
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onLowMemory() {
        log("onLowMemory");
        super.onLowMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        log("onTrimMemory");
        super.onTrimMemory(level);
    }

    /**
     * 日志
     *
     * @param msg
     */
    private void log(String msg) {
        if (isLog && BuildConfig.DEBUG) {
            //终端Log
            CatLog.d(getClass().getSimpleName(), msg);
            //文件Log
            SdLog.line("Life.txt", getClass().getSimpleName() + " -> " + msg, true);
        }
    }

}
