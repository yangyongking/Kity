package me.windleafy.kity.android.base.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

import me.yokeyword.fragmentation.SupportActivity;
import me.windleafy.kity.android.base.BaseApplication;
import me.windleafy.kity.android.manager.ActivityController;
import me.windleafy.kity.android.tool.log.CatLog;


public abstract class BaseSupportActivity extends SupportActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        log("onCreate");
        super.onCreate(savedInstanceState);
        ActivityController.addActivity(this);
    }

    @Override
    protected void onStart() {
        log("onStart");
        super.onStart();
    }

    @Override
    protected void onRestart() {
        log("onRestart");
        super.onRestart();
    }

    @Override
    protected void onResume() {
        log("onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        log("onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        log("onStop");
        super.onStop();
        if (isFinishing())
            releaseResource();
    }

    @Override
    protected void onDestroy() {
        log("onDestroy");
        super.onDestroy();
        ActivityController.removeActivity(this);
    }

    /**
     * 释放资源
     */
    protected void releaseResource() {

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        log("onSaveInstanceState");
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        log("onSaveInstanceState");
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        log("onRestoreInstanceState");
        super.onRestoreInstanceState(savedInstanceState);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        log("onCreateOptionsMenu");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        log("onOptionsItemSelected");
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        log("onActivityResult");
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        log("onKeyDown");
//        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
//            doDouubleBackClickAction();
//        }
        return super.onKeyDown(keyCode, event);
    }


    // 是否双击退出
    private boolean isDoubleClickExitEnable = false;
    private DoubleBackClick mDoubleBackClick;
    private long mDoubleBackClickLastMillis;
    private long mDoubleBackClickDiff;

    /**
     * 是否设置双击退出
     *
     * @param doubleBackClick 回调
     */
    protected void startDoubleBackClick(DoubleBackClick doubleBackClick) {
        setDoubleBackClick(doubleBackClick, 1500);
    }

    /**
     * 设置双击退出
     *
     * 设置：在需要双击退出的地方设置，例如onKeyDown，onBackPressed中
     *
     * @param doubleBackClick 回调
     * @param diff
     */
    protected void setDoubleBackClick(DoubleBackClick doubleBackClick, long diff) {
        //设置参数
        isDoubleClickExitEnable = true;
        mDoubleBackClick = doubleBackClick;
        mDoubleBackClickDiff = diff;
        //执行
        doDouubleBackClickAction();
    }


    /**
     * 双击返回事件
     */
    protected void doDouubleBackClickAction() {
        //是否执行双击退出
        if (isDoubleClickExitEnable) {
            if ((System.currentTimeMillis() - mDoubleBackClickLastMillis) > mDoubleBackClickDiff) {
                mDoubleBackClickLastMillis = System.currentTimeMillis();
                //提示再按一次后退键退出程序
                if (mDoubleBackClick != null) {
                    mDoubleBackClick.onToast();
                }
            } else {
                //退出需要执行的代码
                if (mDoubleBackClick != null)
                    mDoubleBackClick.onExited();
            }
        }

    }

    public interface DoubleBackClick {
        /**
         * 提示再按一次后退键退出程序
         */
        void onToast();

        /**
         * 退出需要执行的代码
         */
        void onExited();
    }

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
