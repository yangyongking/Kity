package me.windleafy.kity.android.base.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.yokeyword.fragmentation.SupportFragment;
import me.windleafy.kity.android.base.BaseApplication;
import me.windleafy.kity.android.tool.log.CatLog;
import me.windleafy.kity.android.tool.log.SdLog;

public abstract class BaseSupportFragment extends SupportFragment {

    public BaseSupportFragment() {
        super();
    }

    @Override
    public void onAttach(Context context) {
        log("onAttach");
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        log("onCreate");
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        log("onViewCreated");
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        log("onActivityCreated");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        log("onStart");
        super.onStart();
    }

    @Override
    public void onResume() {
        log("onResume");
        super.onResume();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        log("onSaveInstanceState");
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onPause() {
        log("onPause");
        super.onPause();
        if (getActivity().isFinishing())
            releaseResource();
    }

    @Override
    public void onStop() {
        log("onStop");
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        log("onDestroyView");
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        log("onDestroy");
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        log("onDetach");
        super.onDetach();
    }

    /**
     * 释放资源
     */
    protected void releaseResource() {

    }

    /**
     * 日志
     *
     * @param msg
     */
    private void log(String msg) {
        if (((BaseApplication) getActivity().getApplication()).isLog()) {
            //终端Log
            CatLog.d(getClass().getSimpleName(), msg);
        }
    }
}
