package me.windleafy.kity.android.base.service;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.support.annotation.Nullable;

import me.windleafy.kity.android.base.BaseApplication;
import me.windleafy.kity.android.tool.log.CatLog;

public abstract class BaseService extends Service {

    protected BaseApplication mApplication;

    protected SharedPreferences mSharedPreferences;
    protected SharedPreferences.Editor mEditor;

    @Override
    public void onCreate() {
        super.onCreate();
        log("onCreate");
        mApplication = (BaseApplication) getApplication();
        mSharedPreferences = mApplication.mSharedPreferences;
        mEditor = mApplication.mEditor;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        log("onBind");
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        log("onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        log("onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    public BaseService() {
        super();
    }

    /**
     * 日志
     *
     * @param msg
     */
    /**
     * 日志
     *
     * @param msg
     */
    private void log(String msg) {
        if (((BaseApplication) getApplication()).isLog()) {
            //终端Log
            CatLog.d(getClass().getSimpleName(), msg);
        }
    }
}
