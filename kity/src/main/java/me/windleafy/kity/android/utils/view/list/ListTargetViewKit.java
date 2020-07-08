package me.windleafy.kity.android.utils.view.list;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import me.windleafy.kity.android.utils.view.TargetView;
import me.windleafy.kity.android.utils.view.TargetViewKit;

public class ListTargetViewKit {

    /**
     * showLoading
     */
    public static void showLoading(Activity activity, int targetId, int loadingView) {
        TargetViewKit.addView(activity, targetId, loadingView, TargetView.AddType.REPLACE, null, null);
    }

    public static void showLoading(Activity activity, View targetView, int loadingView) {
        TargetViewKit.addView(activity, targetView, loadingView, TargetView.AddType.REPLACE, null, null);
    }

    public static void showLoading(Activity activity, int targetId, View loadingView) {
        TargetViewKit.addView(activity, targetId, loadingView, TargetView.AddType.REPLACE, null, null);
    }

    public static void showLoading(Activity activity, View targetView, View loadingView) {
        TargetViewKit.addView(activity, targetView, loadingView, TargetView.AddType.REPLACE, null, null);
    }

    public static void showLoading(Fragment fragment, int targetId, int loadingView) {
        TargetViewKit.addView(fragment, targetId, loadingView, TargetView.AddType.REPLACE, null, null);
    }

    public static void showLoading(Fragment fragment, View targetView, int loadingView) {
        TargetViewKit.addView(fragment, targetView, loadingView, TargetView.AddType.REPLACE, null, null);
    }

    public static void showLoading(Fragment fragment, int targetId, View loadingView) {
        TargetViewKit.addView(fragment, targetId, loadingView, TargetView.AddType.REPLACE, null, null);
    }

    public static void showLoading(Fragment fragment, View targetView, View loadingView) {
        TargetViewKit.addView(fragment, targetView, loadingView, TargetView.AddType.REPLACE, null, null);
    }

    /**
     * hideLoading
     */
    public static void hideLoading(Activity activity, int targetId, int loadingView) {
        TargetViewKit.removeView(activity, targetId, loadingView, TargetView.AddType.REPLACE);
    }

    public static void hideLoading(Activity activity, View targetView, int loadingView) {
        TargetViewKit.removeView(activity, targetView, loadingView, TargetView.AddType.REPLACE);
    }

    public static void hideLoading(Activity activity, int targetId, View loadingView) {
        TargetViewKit.removeView(activity, targetId, loadingView, TargetView.AddType.REPLACE);
    }

    public static void hideLoading(Activity activity, View targetView, View loadingView) {
        TargetViewKit.removeView(activity, targetView, loadingView, TargetView.AddType.REPLACE);
    }

    public static void hideLoading(Fragment fragment, int targetId, int loadingView) {
        TargetViewKit.removeView(fragment, targetId, loadingView, TargetView.AddType.REPLACE);
    }

    public static void hideLoading(Fragment fragment, View targetView, int loadingView) {
        TargetViewKit.removeView(fragment, targetView, loadingView, TargetView.AddType.REPLACE);
    }

    public static void hideLoading(Fragment fragment, int targetId, View loadingView) {
        TargetViewKit.removeView(fragment, targetId, loadingView, TargetView.AddType.REPLACE);
    }

    public static void hideLoading(Fragment fragment, View targetView, View loadingView) {
        TargetViewKit.removeView(fragment, targetView, loadingView, TargetView.AddType.REPLACE);
    }

    /**
     * showRefresh
     */
    public static void showRefresh(final Activity activity, final int targetId, final int refreshView, final Boolean cancelable, final boolean isParentFrame) {
        if (!isParentFrame)
            TargetViewKit.addParent(activity, targetId, TargetView.LayoutType.FRAME);
        TargetViewKit.addViewToOriginal(activity, targetId, refreshView, TargetView.AddType.TAIL, null, cancelable == null ? null : new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cancelable) {
                    hideRefresh(activity, targetId, refreshView, isParentFrame);
                }
            }
        });
    }

    public static void showRefresh(final Activity activity, final View targetView, final int refreshView, final Boolean cancelable, final boolean isParentFrame) {
        if (!isParentFrame)
            TargetViewKit.addParent(activity, targetView, TargetView.LayoutType.FRAME);
        TargetViewKit.addViewToOriginal(activity, targetView, refreshView, TargetView.AddType.TAIL, null, cancelable == null ? null : new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cancelable) {
                    hideRefresh(activity, targetView, refreshView, isParentFrame);
                }
            }
        });
    }

    public static void showRefresh(final Activity activity, final int targetId, final View refreshView, final Boolean cancelable, final boolean isParentFrame) {
        if (!isParentFrame)
            TargetViewKit.addParent(activity, targetId, TargetView.LayoutType.FRAME);
        TargetViewKit.addViewToOriginal(activity, targetId, refreshView, TargetView.AddType.TAIL, null, cancelable == null ? null : new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cancelable) {
                    hideRefresh(activity, targetId, refreshView, isParentFrame);
                }
            }
        });
    }

    public static void showRefresh(final Activity activity, final View targetView, final View refreshView, final Boolean cancelable, final boolean isParentFrame) {
        if (!isParentFrame)
            TargetViewKit.addParent(activity, targetView, TargetView.LayoutType.FRAME);
        TargetViewKit.addViewToOriginal(activity, targetView, refreshView, TargetView.AddType.TAIL, null, cancelable == null ? null : new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cancelable) {
                    hideRefresh(activity, targetView, refreshView, isParentFrame);
                }
            }
        });
    }

    public static void showRefresh(final Fragment fragment, final int targetId, final int refreshView, final Boolean cancelable, final boolean isParentFrame) {
        if (!isParentFrame)
            TargetViewKit.addParent(fragment, targetId, TargetView.LayoutType.FRAME);
        TargetViewKit.addViewToOriginal(fragment, targetId, refreshView, TargetView.AddType.TAIL, null, cancelable == null ? null : new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cancelable) {
                    hideRefresh(fragment, targetId, refreshView, isParentFrame);
                }
            }
        });
    }

    public static void showRefresh(final Fragment fragment, final View targetView, final int refreshView, final Boolean cancelable, final boolean isParentFrame) {
        if (!isParentFrame)
            TargetViewKit.addParent(fragment, targetView, TargetView.LayoutType.FRAME);
        TargetViewKit.addViewToOriginal(fragment, targetView, refreshView, TargetView.AddType.TAIL, null, cancelable == null ? null : new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cancelable) {
                    hideRefresh(fragment, targetView, refreshView, isParentFrame);
                }
            }
        });
    }

    public static void showRefresh(final Fragment fragment, final int targetId, final View refreshView, final Boolean cancelable, final boolean isParentFrame) {
        if (!isParentFrame)
            TargetViewKit.addParent(fragment, targetId, TargetView.LayoutType.FRAME);
        TargetViewKit.addViewToOriginal(fragment, targetId, refreshView, TargetView.AddType.TAIL, null, cancelable == null ? null : new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cancelable) {
                    hideRefresh(fragment, targetId, refreshView, isParentFrame);
                }
            }
        });
    }

    public static void showRefresh(final Fragment fragment, final View targetView, final View refreshView, final Boolean cancelable, final boolean isParentFrame) {
        if (!isParentFrame)
            TargetViewKit.addParent(fragment, targetView, TargetView.LayoutType.FRAME);
        TargetViewKit.addViewToOriginal(fragment, targetView, refreshView, TargetView.AddType.TAIL, null, cancelable == null ? null : new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cancelable) {
                    hideRefresh(fragment, targetView, refreshView, isParentFrame);
                }
            }
        });
    }

    /**
     * hideRefresh
     */
    public static void hideRefresh(Activity activity, int targetId, int refreshView, boolean isParentFrame) {
        if (TargetViewKit.getViewTimes(activity, targetId, refreshView, TargetView.AddType.TAIL) > 1) {
            TargetViewKit.removeViewTimes(activity, targetId, refreshView, TargetView.AddType.TAIL);
            return;
        }
        TargetViewKit.removeViewFromOriginal(activity, targetId, refreshView, TargetView.AddType.TAIL);
        if (!isParentFrame)
            TargetViewKit.removeParent(activity, targetId);
    }

    public static void hideRefresh(Activity activity, View targetView, int refreshView, boolean isParentFrame) {
        if (TargetViewKit.getViewTimes(activity, targetView, refreshView, TargetView.AddType.TAIL) > 1) {
            TargetViewKit.removeViewTimes(activity, targetView, refreshView, TargetView.AddType.TAIL);
            return;
        }
        TargetViewKit.removeViewFromOriginal(activity, targetView, refreshView, TargetView.AddType.TAIL);
        if (!isParentFrame)
            TargetViewKit.removeParent(activity, targetView);
    }

    public static void hideRefresh(Activity activity, int targetId, View refreshView, boolean isParentFrame) {
        if (TargetViewKit.getViewTimes(activity, targetId, refreshView, TargetView.AddType.TAIL) > 1) {
            TargetViewKit.removeViewTimes(activity, targetId, refreshView, TargetView.AddType.TAIL);
            return;
        }
        TargetViewKit.removeViewFromOriginal(activity, targetId, refreshView, TargetView.AddType.TAIL);
        if (!isParentFrame)
            TargetViewKit.removeParent(activity, targetId);
    }

    public static void hideRefresh(Activity activity, View targetView, View refreshView, boolean isParentFrame) {
        if (TargetViewKit.getViewTimes(activity, targetView, refreshView, TargetView.AddType.TAIL) > 1) {
            TargetViewKit.removeViewTimes(activity, targetView, refreshView, TargetView.AddType.TAIL);
            return;
        }
        TargetViewKit.removeViewFromOriginal(activity, targetView, refreshView, TargetView.AddType.TAIL);
        if (!isParentFrame)
            TargetViewKit.removeParent(activity, targetView);
    }

    public static void hideRefresh(Fragment fragment, int targetId, int refreshView, boolean isParentFrame) {
        if (TargetViewKit.getViewTimes(fragment, targetId, refreshView, TargetView.AddType.TAIL) > 1) {
            TargetViewKit.removeViewTimes(fragment, targetId, refreshView, TargetView.AddType.TAIL);
            return;
        }
        TargetViewKit.removeViewFromOriginal(fragment, targetId, refreshView, TargetView.AddType.TAIL);
        if (!isParentFrame)
            TargetViewKit.removeParent(fragment, targetId);
    }

    public static void hideRefresh(Fragment fragment, View targetView, int refreshView, boolean isParentFrame) {
        if (TargetViewKit.getViewTimes(fragment, targetView, refreshView, TargetView.AddType.TAIL) > 1) {
            TargetViewKit.removeViewTimes(fragment, targetView, refreshView, TargetView.AddType.TAIL);
            return;
        }
        TargetViewKit.removeViewFromOriginal(fragment, targetView, refreshView, TargetView.AddType.TAIL);
        if (!isParentFrame)
            TargetViewKit.removeParent(fragment, targetView);
    }

    public static void hideRefresh(Fragment fragment, int targetId, View refreshView, boolean isParentFrame) {
        if (TargetViewKit.getViewTimes(fragment, targetId, refreshView, TargetView.AddType.TAIL) > 1) {
            TargetViewKit.removeViewTimes(fragment, targetId, refreshView, TargetView.AddType.TAIL);
            return;
        }
        TargetViewKit.removeViewFromOriginal(fragment, targetId, refreshView, TargetView.AddType.TAIL);
        if (!isParentFrame)
            TargetViewKit.removeParent(fragment, targetId);
    }

    public static void hideRefresh(Fragment fragment, View targetView, View refreshView, boolean isParentFrame) {
        if (TargetViewKit.getViewTimes(fragment, targetView, refreshView, TargetView.AddType.TAIL) > 1) {
            TargetViewKit.removeViewTimes(fragment, targetView, refreshView, TargetView.AddType.TAIL);
            return;
        }
        TargetViewKit.removeViewFromOriginal(fragment, targetView, refreshView, TargetView.AddType.TAIL);
        if (!isParentFrame)
            TargetViewKit.removeParent(fragment, targetView);
    }

    /**
     * setNotice
     */
    public static void setNotice(Activity activity, List list, int targetId, int noticeLayout, View.OnClickListener listener) {
        setNotice(activity, list.size() == 0, targetId, noticeLayout, listener);
    }

    public static void setNotice(Activity activity, List list, int targetId, View noticeLayout, View.OnClickListener listener) {
        setNotice(activity, list.size() == 0, targetId, noticeLayout, listener);
    }

    public static void setNotice(Fragment fragment, List list, int targetId, int noticeLayout, View.OnClickListener listener) {
        setNotice(fragment, list.size() == 0, targetId, noticeLayout, listener);
    }

    public static void setNotice(Fragment fragment, List list, int targetId, View noticeLayout, View.OnClickListener listener) {
        setNotice(fragment, list.size() == 0, targetId, noticeLayout, listener);
    }

    public static void setNotice(Activity activity, boolean show, int targetId, int noticeLayout, View.OnClickListener listener) {
        if (show) {
            TargetViewKit.addView(activity, targetId, noticeLayout, TargetView.AddType.REPLACE, null, listener);
        } else {
            TargetViewKit.removeView(activity, targetId, noticeLayout, TargetView.AddType.REPLACE);
        }
    }

    public static void setNotice(Activity activity, boolean show, int targetId, View noticeLayout, View.OnClickListener listener) {
        if (show) {
            TargetViewKit.addView(activity, targetId, noticeLayout, TargetView.AddType.REPLACE, null, listener);
        } else {
            TargetViewKit.removeView(activity, targetId, noticeLayout, TargetView.AddType.REPLACE);
        }
    }

    public static void setNotice(Fragment fragment, boolean show, int targetId, int noticeLayout, View.OnClickListener listener) {
        if (show) {
            TargetViewKit.addView(fragment, targetId, noticeLayout, TargetView.AddType.REPLACE, null, listener);
        } else {
            TargetViewKit.removeView(fragment, targetId, noticeLayout, TargetView.AddType.REPLACE);
        }
    }

    public static void setNotice(Fragment fragment, boolean show, int targetId, View noticeLayout, View.OnClickListener listener) {
        if (show) {
            TargetViewKit.addView(fragment, targetId, noticeLayout, TargetView.AddType.REPLACE, null, listener);
        } else {
            TargetViewKit.removeView(fragment, targetId, noticeLayout, TargetView.AddType.REPLACE);
        }
    }

    /**
     * setHeader
     */
    public static void setHeader(Activity activity, List list, int targetId, int headerView, ViewGroup.LayoutParams params) {
        setHeader(activity, list.size() != 0, targetId, headerView, params);
    }

    public static void setHeader(Activity activity, List list, int targetId, View headerView, ViewGroup.LayoutParams params) {
        setHeader(activity, list.size() != 0, targetId, headerView, params);
    }

    public static void setHeader(Fragment fragment, List list, int targetId, int headerView, ViewGroup.LayoutParams params) {
        setHeader(fragment, list.size() != 0, targetId, headerView, params);
    }

    public static void setHeader(Fragment fragment, List list, int targetId, View headerView, ViewGroup.LayoutParams params) {
        setHeader(fragment, list.size() != 0, targetId, headerView, params);
    }

    public static void setHeader(Activity activity, boolean show, int targetId, int headerView, ViewGroup.LayoutParams params) {
        if (show) {
            TargetViewKit.addView(activity, targetId, headerView, TargetView.AddType.BEFORE, params, null);
        } else {
            TargetViewKit.removeView(activity, targetId, headerView, TargetView.AddType.BEFORE);
        }
    }

    public static void setHeader(Activity activity, boolean show, int targetId, View headerView, ViewGroup.LayoutParams params) {
        if (show) {
            TargetViewKit.addView(activity, targetId, headerView, TargetView.AddType.BEFORE, params, null);
        } else {
            TargetViewKit.removeView(activity, targetId, headerView, TargetView.AddType.BEFORE);
        }
    }

    public static void setHeader(Fragment fragment, boolean show, int targetId, int headerView, ViewGroup.LayoutParams params) {
        if (show) {
            TargetViewKit.addView(fragment, targetId, headerView, TargetView.AddType.BEFORE, params, null);
        } else {
            TargetViewKit.removeView(fragment, targetId, headerView, TargetView.AddType.BEFORE);
        }
    }

    public static void setHeader(Fragment fragment, boolean show, int targetId, View headerView, ViewGroup.LayoutParams params) {
        if (show) {
            TargetViewKit.addView(fragment, targetId, headerView, TargetView.AddType.BEFORE, params, null);
        } else {
            TargetViewKit.removeView(fragment, targetId, headerView, TargetView.AddType.BEFORE);
        }
    }


    /**
     * setFooter
     */
    public static void setFooter(Activity activity, List list, int targetId, int headerView, ViewGroup.LayoutParams params) {
        setFooter(activity, list.size() != 0, targetId, headerView, params);
    }

    public static void setFooter(Activity activity, List list, int targetId, View headerView, ViewGroup.LayoutParams params) {
        setFooter(activity, list.size() != 0, targetId, headerView, params);
    }

    public static void setFooter(Fragment fragment, List list, int targetId, int headerView, ViewGroup.LayoutParams params) {
        setFooter(fragment, list.size() != 0, targetId, headerView, params);
    }

    public static void setFooter(Fragment fragment, List list, int targetId, View headerView, ViewGroup.LayoutParams params) {
        setFooter(fragment, list.size() != 0, targetId, headerView, params);
    }

    public static void setFooter(Activity activity, boolean show, int targetId, int headerView, ViewGroup.LayoutParams params) {
        if (show) {
            TargetViewKit.addView(activity, targetId, headerView, TargetView.AddType.AFTER, params, null);
        } else {
            TargetViewKit.removeView(activity, targetId, headerView, TargetView.AddType.AFTER);
        }
    }

    public static void setFooter(Activity activity, boolean show, int targetId, View headerView, ViewGroup.LayoutParams params) {
        if (show) {
            TargetViewKit.addView(activity, targetId, headerView, TargetView.AddType.AFTER, params, null);
        } else {
            TargetViewKit.removeView(activity, targetId, headerView, TargetView.AddType.AFTER);
        }
    }

    public static void setFooter(Fragment fragment, boolean show, int targetId, int headerView, ViewGroup.LayoutParams params) {
        if (show) {
            TargetViewKit.addView(fragment, targetId, headerView, TargetView.AddType.AFTER, params, null);
        } else {
            TargetViewKit.removeView(fragment, targetId, headerView, TargetView.AddType.AFTER);
        }
    }

    public static void setFooter(Fragment fragment, boolean show, int targetId, View headerView, ViewGroup.LayoutParams params) {
        if (show) {
            TargetViewKit.addView(fragment, targetId, headerView, TargetView.AddType.AFTER, params, null);
        } else {
            TargetViewKit.removeView(fragment, targetId, headerView, TargetView.AddType.AFTER);
        }
    }


    /**
     * 打印信息
     */
    public static void print(Activity activity, int targetId) {
        TargetViewKit.print(activity, targetId);
    }

    public static void print(Activity activity, View targetView) {
        TargetViewKit.print(activity, targetView);
    }

    public static void print(Fragment fragment, int targetId) {
        TargetViewKit.print(fragment, targetId);
    }

    public static void print(Fragment fragment, View targetView) {
        TargetViewKit.print(fragment, targetView);
    }

}
