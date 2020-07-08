package me.windleafy.kity.android.utils.view.list;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import me.windleafy.kity.android.utils.view.TargetView;
import me.windleafy.kity.android.utils.view.TargetViewKit;

public class ListTargetView extends TargetView {

    //Constructor
    public ListTargetView(Activity activity, int containerLayoutId) {
        super(activity, containerLayoutId);
    }

    public ListTargetView(Activity activity, View containerLayoutId) {
        super(activity, containerLayoutId);

    }

    public ListTargetView(Fragment fragment, int containerLayoutId) {
        super(fragment, containerLayoutId);
    }

    public ListTargetView(Fragment fragment, View containerLayoutId) {
        super(fragment, containerLayoutId);
    }

    protected View mLoadingView;
    protected View mRefreshView;
    protected View mNoticeView;
    protected View mHeaderView;
    protected View mFooterView;

    public void setHeaderView(View headerView) {
        mHeaderView = headerView;
    }

    public void setFooterView(View footerView) {
        mFooterView = footerView;
    }

    public void setLoadingView(View loadingView) {
        mLoadingView = loadingView;
    }

    public void setRefreshView(View refreshView, final Boolean cancelable) {
        mRefreshView = refreshView;
        if (cancelable != null) {
            mRefreshView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (cancelable) {
                        hideRefresh();
                    }
                }
            });
        }
    }

    public void setNoticeView(View noticeView) {
        mNoticeView = noticeView;
    }

    public void setHeaderView(int headerResId) {
        mHeaderView = inflate(headerResId);
    }

    public void setFooterView(int footerResId) {
        mFooterView = inflate(footerResId);
    }

    public void setLoadingView(int loadingResId) {
        mLoadingView = inflate(loadingResId);
    }

    public void setRefreshView(int refreshView) {
        mRefreshView = inflate(refreshView);
    }

    public void setRefreshView(int refreshView, final Boolean cancelable) {
        mRefreshView = inflate(refreshView);
        if (cancelable != null) {
            mRefreshView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (cancelable) {
                        hideRefresh();
                    }
                }
            });
        }
    }

    public void setNoticeView(int noticeResId) {
        mNoticeView = inflate(noticeResId);
    }

    /**
     * Loading
     */
    //Loading mLoadingView
    public void showLoading() {
        showLoading(mLoadingView);
    }

    public void hideLoading() {
        hideLoading(mLoadingView);
    }

    /**
     * @param showHeaderFooter 显示loading时，是否显示header和footer
     */
    public void showLoading(boolean showHeaderFooter) {
        header(showHeaderFooter);
        footer(showHeaderFooter);
        showLoading(mLoadingView);
    }

    /**
     * @param showHeaderFooter 隐藏loading后，是否显示header和footer
     */
    public void hideLoading(boolean showHeaderFooter) {
        header(showHeaderFooter);
        footer(showHeaderFooter);
        hideLoading(mLoadingView);
    }

    //Loading
    public void showLoading(int loadingLayout) {
        setLoadingView(loadingLayout);
        addView(loadingLayout, AddType.REPLACE);
    }

    public void showLoading(View loadingLayout) {
        setLoadingView(loadingLayout);
        addView(loadingLayout, AddType.REPLACE);
    }

    public void hideLoading(int loadingLayout) {
        removeView(loadingLayout, AddType.REPLACE);
    }

    public void hideLoading(View loadingLayout) {
        removeView(loadingLayout, AddType.REPLACE);
    }


    /**
     * Refresh
     */
    //Refresh mRefreshView
    public void showRefresh() {
        showRefresh(mRefreshView, null);
    }

    public void showRefresh(Boolean cancelable) {
        showRefresh(mRefreshView, cancelable);
    }

    public void hideRefresh() {
        hideRefresh(mRefreshView);
    }

    //Refresh
    public void showRefresh(int refreshLayout) {
        showRefresh(refreshLayout, null);
    }

    public void showRefresh(View refreshLayout) {
        showRefresh(refreshLayout, null);
    }

    public void showRefresh(int refreshLayout, Boolean cancelable) {
        hideLoading();//先关闭loading
        setRefreshView(refreshLayout, cancelable);
        addParent(LayoutType.FRAME);
        addViewToOriginal(refreshLayout, AddType.TAIL);
    }

    public void showRefresh(View refreshLayout, Boolean cancelable) {
        hideLoading();//先关闭loading
        setRefreshView(refreshLayout, cancelable);
        addParent(LayoutType.FRAME);
        addViewToOriginal(refreshLayout, AddType.TAIL);
    }

    public void hideRefresh(int refreshLayout) {
        if (getViewTimes(refreshLayout, AddType.TAIL) > 1) {
            removeViewTimes(refreshLayout, AddType.TAIL);
            return;
        }
        removeViewFromOriginal(refreshLayout, AddType.TAIL);
        removeParent();
    }

    public void hideRefresh(View refreshLayout) {
        if (getViewTimes(refreshLayout, AddType.TAIL) > 1) {
            removeViewTimes(refreshLayout, AddType.TAIL);
            return;
        }
        removeViewFromOriginal(refreshLayout, AddType.TAIL);
        removeParent();
    }


    /**
     * Notice
     */
    //Notice mNoticeView
    public void notice(List list) {
        notice(list, mNoticeView, null);
    }

    public void notice(boolean show) {
        notice(show, mNoticeView, null);
    }

    public void notice(List list, View.OnClickListener onClickListener) {
        notice(list, mNoticeView, onClickListener);
    }

    public void notice(boolean show, View.OnClickListener onClickListener) {
        notice(show, mNoticeView, onClickListener);
    }

    //Notice
    public void notice(boolean show, int noticeView, View.OnClickListener onClickListener) {
        if (show) {
            showNotice(noticeView, onClickListener);
        } else {
            hideNotice(noticeView);
        }
    }

    public void notice(boolean show, View noticeView, View.OnClickListener onClickListener) {
        if (show) {
            showNotice(noticeView, onClickListener);
        } else {
            hideNotice(noticeView);
        }
    }

    public void notice(List list, int noticeView, View.OnClickListener onClickListener) {
        notice(list.size() == 0, noticeView, onClickListener);
    }

    public void notice(List list, View noticeView, View.OnClickListener onClickListener) {
        notice(list.size() == 0, noticeView, onClickListener);
    }

    //Notice DO
    public void showNotice(int noticeView, View.OnClickListener onClickListener) {
        setNoticeView(noticeView);
        addView(noticeView, AddType.REPLACE, onClickListener);
    }

    public void showNotice(View noticeView, View.OnClickListener onClickListener) {
        if (noticeView == null) return;
        setNoticeView(noticeView);
        addView(noticeView, AddType.REPLACE, onClickListener);
    }

    public void hideNotice(int noticeView) {
        removeView(noticeView, AddType.REPLACE);
    }

    public void hideNotice(View noticeView) {
        removeView(noticeView, AddType.REPLACE);
    }


    /**
     * Header
     */
    //Header mHeaderView
    public void header(List list) {
        if (mHeaderView != null) setHeader(list, mHeaderView);
    }


    public void header(boolean show) {
        if (mHeaderView != null) setHeader(show, mHeaderView);
    }

    public void header(List list, View headerView) {
        mHeaderView = headerView;
        setHeader(list, mHeaderView);
    }


    public void header(boolean show, View headerView) {
        mHeaderView = headerView;
        setHeader(show, mHeaderView);
    }

    //Header
    public void setHeader(boolean show, int headerView) {
        if (show) {
            showHeader(headerView);
        } else {
            hideHeader(headerView);
        }
    }

    public void setHeader(boolean show, View headerView) {
        if (show) {
            showHeader(headerView);
        } else {
            hideHeader(headerView);
        }
    }

    public void setHeader(List list, int headerView) {
        setHeader(list.size() != 0, headerView);
    }

    public void setHeader(List list, View headerView) {
        setHeader(list.size() != 0, headerView);
    }

    //Header DO
    public void showHeader(int headerView) {
        setHeaderView(headerView);
        addView(headerView, AddType.BEFORE, new ViewGroup.LayoutParams(-1, -2), null);
    }

    public void showHeader(View headerView) {
        if (headerView == null) return;
        setHeaderView(headerView);
        addView(headerView, AddType.BEFORE, new ViewGroup.LayoutParams(-1, -2), null);
    }

    public void hideHeader(int headerView) {
        removeView(headerView, AddType.BEFORE);
    }

    public void hideHeader(View headerView) {
        removeView(headerView, AddType.BEFORE);
    }

    /**
     * Footer
     */
    //Footer mFooterView
    public void footer(List list) {
        if (mFooterView != null) setFooter(list, mFooterView);
    }

    public void footer(boolean show) {
        if (mFooterView != null) setFooter(show, mFooterView);
    }

    public void footer(List list, View footerView) {
        mFooterView = footerView;
        setFooter(list, mFooterView);
    }


    public void footer(boolean show, View footerView) {
        mFooterView = footerView;
        setFooter(show, mFooterView);
    }

    //Footer
    public void setFooter(boolean show, int footerView) {
        if (show) {
            showFooter(footerView);
        } else {
            hideFooter(footerView);
        }
    }

    public void setFooter(boolean show, View footerView) {
        if (show) {
            showFooter(footerView);
        } else {
            hideFooter(footerView);
        }
    }

    public void setFooter(List list, int footerView) {
        setFooter(list.size() != 0, footerView);
    }

    public void setFooter(List list, View footerView) {
        setFooter(list.size() != 0, footerView);
    }

    //Footer DO
    public void showFooter(int footerView) {
        setFooterView(footerView);
        addView(footerView, AddType.AFTER, new ViewGroup.LayoutParams(-1, -2), null);
    }

    public void showFooter(View footerView) {
        if (footerView == null) return;
        setFooterView(footerView);
        addView(footerView, AddType.AFTER, new ViewGroup.LayoutParams(-1, -2), null);
    }

    public void hideFooter(int footerView) {
        removeView(footerView, AddType.AFTER);
    }

    public void hideFooter(View footerView) {
        removeView(footerView, AddType.AFTER);
    }


}

