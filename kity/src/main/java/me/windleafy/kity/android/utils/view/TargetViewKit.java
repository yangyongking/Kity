package me.windleafy.kity.android.utils.view;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import me.windleafy.kity.java.collection.MapList;


public class TargetViewKit {

    private static MapList<Object, TargetView> mMapList;

    static {
        mMapList = new MapList<>();
    }

    private static TargetView getListTargetView(List<TargetView> list, Object targetIdOrTargetView) {
        if (targetIdOrTargetView instanceof Integer) {
            for (TargetView view : list) {
                if (view.getId() == (int) targetIdOrTargetView)
                    return view;
            }
        }
        if (targetIdOrTargetView instanceof View) {
            for (TargetView view : list) {
                if (view.getTargetView().hashCode() == targetIdOrTargetView.hashCode())
                    return view;
            }
        }
        return null;
    }

    /**
     * View Activity
     */

    public static TargetView getTargetView(Activity activity, int targetId) {
        TargetView view = getListTargetView(mMapList.getList(activity), targetId);
        if (view == null) {
            view = new TargetView(activity, targetId);
            mMapList.addValue(activity, view);
        }
        return view;
    }

    public static TargetView getTargetView(Activity activity, View targetView) {
        TargetView view = getListTargetView(mMapList.getList(activity), targetView);
        if (view == null) {
            view = new TargetView(activity, targetView);
            mMapList.addValue(activity, view);
        }
        return view;
    }

    public static void addView(Activity activity, int targetId, int resId, TargetView.AddType addType, ViewGroup.LayoutParams params, View.OnClickListener listener) {
        if (activity == null) return;
        TargetView view = getTargetView(activity, targetId);
        view.addView(resId, addType, params, listener);
    }

    public static void addView(Activity activity, int targetId, View resView, TargetView.AddType addType, ViewGroup.LayoutParams params, View.OnClickListener listener) {
        if (activity == null) return;
        TargetView view = getTargetView(activity, targetId);
        view.addView(resView, addType, params, listener);
    }

    public static void addView(Activity activity, View targetView, int resId, TargetView.AddType addType, ViewGroup.LayoutParams params, View.OnClickListener listener) {
        if (activity == null) return;
        TargetView view = getTargetView(activity, targetView);
        view.addView(resId, addType, params, listener);
    }

    public static void addView(Activity activity, View targetView, View resView, TargetView.AddType addType, ViewGroup.LayoutParams params, View.OnClickListener listener) {
        if (activity == null) return;
        TargetView view = getTargetView(activity, targetView);
        view.addView(resView, addType, params, listener);
    }

    public static void addViewToOriginal(Activity activity, int targetId, int resId, TargetView.AddType addType, ViewGroup.LayoutParams params, View.OnClickListener listener) {
        if (activity == null) return;
        TargetView view = getTargetView(activity, targetId);
        view.addViewToOriginal(resId, addType, params, listener);
    }

    public static void addViewToOriginal(Activity activity, int targetId, View resView, TargetView.AddType addType, ViewGroup.LayoutParams params, View.OnClickListener listener) {
        if (activity == null) return;
        TargetView view = getTargetView(activity, targetId);
        view.addViewToOriginal(resView, addType, params, listener);
    }

    public static void addViewToOriginal(Activity activity, View targetView, int resId, TargetView.AddType addType, ViewGroup.LayoutParams params, View.OnClickListener listener) {
        if (activity == null) return;
        TargetView view = getTargetView(activity, targetView);
        view.addViewToOriginal(resId, addType, params, listener);
    }

    public static void addViewToOriginal(Activity activity, View targetView, View resView, TargetView.AddType addType, ViewGroup.LayoutParams params, View.OnClickListener listener) {
        if (activity == null) return;
        TargetView view = getTargetView(activity, targetView);
        view.addViewToOriginal(resView, addType, params, listener);
    }

    public static void removeView(Activity activity, int targetId, int resId, TargetView.AddType addType) {
        if (activity == null) return;
        if (!mMapList.containKey(activity)) return;
        TargetView view = getListTargetView(mMapList.getList(activity), targetId);
        if (view == null) return;
        view.removeView(resId, addType);
        removeListMap(activity, view);
    }

    public static void removeView(Activity activity, int targetId, View resView, TargetView.AddType addType) {
        if (activity == null) return;
        if (!mMapList.containKey(activity)) return;
        TargetView view = getListTargetView(mMapList.getList(activity), targetId);
        if (view == null) return;
        view.removeView(resView, addType);
        removeListMap(activity, view);
    }

    public static void removeView(Activity activity, View targetView, int resId, TargetView.AddType addType) {
        if (activity == null) return;
        if (!mMapList.containKey(activity)) return;
        TargetView view = getListTargetView(mMapList.getList(activity), targetView);
        if (view == null) return;
        view.removeView(resId, addType);
        removeListMap(activity, view);
    }

    public static void removeView(Activity activity, View targetView, View resView, TargetView.AddType addType) {
        if (activity == null) return;
        if (!mMapList.containKey(activity)) return;
        TargetView view = getListTargetView(mMapList.getList(activity), targetView);
        if (view == null) return;
        view.removeView(resView, addType);
        removeListMap(activity, view);
    }

    public static void removeViewFromOriginal(Activity activity, int targetId, int resId, TargetView.AddType addType) {
        if (activity == null) return;
        if (!mMapList.containKey(activity)) return;
        TargetView view = getListTargetView(mMapList.getList(activity), targetId);
        if (view == null) return;
        view.removeViewFromOriginal(resId, addType);
        removeListMap(activity, view);
    }

    public static void removeViewFromOriginal(Activity activity, int targetId, View resView, TargetView.AddType addType) {
        if (activity == null) return;
        if (!mMapList.containKey(activity)) return;
        TargetView view = getListTargetView(mMapList.getList(activity), targetId);
        if (view == null) return;
        view.removeViewFromOriginal(resView, addType);
        removeListMap(activity, view);
    }

    public static void removeViewFromOriginal(Activity activity, View targetView, int resId, TargetView.AddType addType) {
        if (activity == null) return;
        if (!mMapList.containKey(activity)) return;
        TargetView view = getListTargetView(mMapList.getList(activity), targetView);
        if (view == null) return;
        view.removeViewFromOriginal(resId, addType);
        removeListMap(activity, view);
    }

    public static void removeViewFromOriginal(Activity activity, View targetView, View resView, TargetView.AddType addType) {
        if (activity == null) return;
        if (!mMapList.containKey(activity)) return;
        TargetView view = getListTargetView(mMapList.getList(activity), targetView);
        if (view == null) return;
        view.removeViewFromOriginal(resView, addType);
        removeListMap(activity, view);
    }


    public static int getViewTimes(Activity activity, int targetId, View resView, TargetView.AddType addType) {
        if (activity == null) return 0;
        if (!mMapList.containKey(activity)) return 0;
        TargetView view = getListTargetView(mMapList.getList(activity), targetId);
        return view.getViewTimes(resView, addType);
    }

    public static int getViewTimes(Activity activity, int targetId, int resId, TargetView.AddType addType) {
        if (activity == null) return 0;
        if (!mMapList.containKey(activity)) return 0;
        TargetView view = getListTargetView(mMapList.getList(activity), targetId);
        return view.getViewTimes(resId, addType);
    }

    public static int getViewTimes(Activity activity, View targetView, View resView, TargetView.AddType addType) {
        if (activity == null) return 0;
        if (!mMapList.containKey(activity)) return 0;
        TargetView view = getListTargetView(mMapList.getList(activity), targetView);
        return view.getViewTimes(resView, addType);
    }

    public static int getViewTimes(Activity activity, View targetView, int resId, TargetView.AddType addType) {
        if (activity == null) return 0;
        if (!mMapList.containKey(activity)) return 0;
        TargetView view = getListTargetView(mMapList.getList(activity), targetView);
        return view.getViewTimes(resId, addType);
    }

    public static void removeViewTimes(Activity activity, int targetId, View resView, TargetView.AddType addType) {
        if (activity == null) return ;
        if (!mMapList.containKey(activity)) return ;
        TargetView view = getListTargetView(mMapList.getList(activity), targetId);
        view.removeViewTimes(resView, addType);
    }

    public static void removeViewTimes(Activity activity, int targetId, int resId, TargetView.AddType addType) {
        if (activity == null) return ;
        if (!mMapList.containKey(activity)) return ;
        TargetView view = getListTargetView(mMapList.getList(activity), targetId);
        view.removeViewTimes(resId, addType);
    }

    public static void removeViewTimes(Activity activity, View targetView, View resView, TargetView.AddType addType) {
        if (activity == null) return ;
        if (!mMapList.containKey(activity)) return ;
        TargetView view = getListTargetView(mMapList.getList(activity), targetView);
        view.removeViewTimes(resView, addType);
    }

    public static void removeViewTimes(Activity activity, View targetView, int resId, TargetView.AddType addType) {
        if (activity == null) return ;
        if (!mMapList.containKey(activity)) return ;
        TargetView view = getListTargetView(mMapList.getList(activity), targetView);
        view.removeViewTimes(resId, addType);
    }

    /**
     * View Fragment
     */

    public static TargetView getTargetView(Fragment fragment, int targetId) {
        TargetView view = getListTargetView(mMapList.getList(fragment), targetId);
        if (view == null) {
            view = new TargetView(fragment, targetId);
            mMapList.addValue(fragment, view);
        }
        return view;
    }

    public static TargetView getTargetView(Fragment fragment, View targetView) {
        TargetView view = getListTargetView(mMapList.getList(fragment), targetView);
        if (view == null) {
            view = new TargetView(fragment, targetView);
            mMapList.addValue(fragment, view);
        }
        return view;
    }

    public static void addView(Fragment fragment, int targetId, int resId, TargetView.AddType addType, ViewGroup.LayoutParams params, View.OnClickListener listener) {
        if (fragment == null || fragment.getActivity() == null) return;
        TargetView view = getTargetView(fragment, targetId);
        view.addView(resId, addType, params, listener);
    }

    public static void addView(Fragment fragment, int targetId, View resView, TargetView.AddType addType, ViewGroup.LayoutParams params, View.OnClickListener listener) {
        if (fragment == null || fragment.getActivity() == null) return;
        TargetView view = getTargetView(fragment, targetId);
        view.addView(resView, addType, params, listener);
    }

    public static void addView(Fragment fragment, View targetView, int resId, TargetView.AddType addType, ViewGroup.LayoutParams params, View.OnClickListener listener) {
        if (fragment == null || fragment.getActivity() == null) return;
        TargetView view = getTargetView(fragment, targetView);
        view.addView(resId, addType, params, listener);
    }

    public static void addView(Fragment fragment, View targetView, View resView, TargetView.AddType addType, ViewGroup.LayoutParams params, View.OnClickListener listener) {
        if (fragment == null || fragment.getActivity() == null) return;
        TargetView view = getTargetView(fragment, targetView);
        view.addView(resView, addType, params, listener);
    }

    public static void addViewToOriginal(Fragment fragment, int targetId, int resId, TargetView.AddType addType, ViewGroup.LayoutParams params, View.OnClickListener listener) {
        if (fragment == null || fragment.getActivity() == null) return;
        TargetView view = getTargetView(fragment, targetId);
        view.addViewToOriginal(resId, addType, params, listener);
    }

    public static void addViewToOriginal(Fragment fragment, int targetId, View resView, TargetView.AddType addType, ViewGroup.LayoutParams params, View.OnClickListener listener) {
        if (fragment == null || fragment.getActivity() == null) return;
        TargetView view = getTargetView(fragment, targetId);
        view.addViewToOriginal(resView, addType, params, listener);
    }

    public static void addViewToOriginal(Fragment fragment, View targetView, int resId, TargetView.AddType addType, ViewGroup.LayoutParams params, View.OnClickListener listener) {
        if (fragment == null || fragment.getActivity() == null) return;
        TargetView view = getTargetView(fragment, targetView);
        view.addViewToOriginal(resId, addType, params, listener);
    }

    public static void addViewToOriginal(Fragment fragment, View targetView, View resView, TargetView.AddType addType, ViewGroup.LayoutParams params, View.OnClickListener listener) {
        if (fragment == null || fragment.getActivity() == null) return;
        TargetView view = getTargetView(fragment, targetView);
        view.addViewToOriginal(resView, addType, params, listener);
    }

    public static void removeView(Fragment fragment, int targetId, int resId, TargetView.AddType addType) {
        if (fragment == null || fragment.getActivity() == null) return;
        if (!mMapList.containKey(fragment)) return;
        TargetView view = getListTargetView(mMapList.getList(fragment), targetId);
        if (view == null) return;
        view.removeView(resId, addType);
        removeListMap(fragment, view);
    }

    public static void removeView(Fragment fragment, int targetId, View resView, TargetView.AddType addType) {
        if (fragment == null || fragment.getActivity() == null) return;
        if (!mMapList.containKey(fragment)) return;
        TargetView view = getListTargetView(mMapList.getList(fragment), targetId);
        if (view == null) return;
        view.removeView(resView, addType);
        removeListMap(fragment, view);
    }

    public static void removeView(Fragment fragment, View targetView, int resId, TargetView.AddType addType) {
        if (fragment == null || fragment.getActivity() == null) return;
        if (!mMapList.containKey(fragment)) return;
        TargetView view = getListTargetView(mMapList.getList(fragment), targetView);
        if (view == null) return;
        view.removeView(resId, addType);
        removeListMap(fragment, view);
    }

    public static void removeView(Fragment fragment, View targetView, View resView, TargetView.AddType addType) {
        if (fragment == null || fragment.getActivity() == null) return;
        if (!mMapList.containKey(fragment)) return;
        TargetView view = getListTargetView(mMapList.getList(fragment), targetView);
        if (view == null) return;
        view.removeView(resView, addType);
        removeListMap(fragment, view);
    }

    public static void removeViewFromOriginal(Fragment fragment, int targetId, int resId, TargetView.AddType addType) {
        if (fragment == null || fragment.getActivity() == null) return;
        if (!mMapList.containKey(fragment)) return;
        TargetView view = getListTargetView(mMapList.getList(fragment), targetId);
        if (view == null) return;
        view.removeViewFromOriginal(resId, addType);
        removeListMap(fragment, view);
    }

    public static void removeViewFromOriginal(Fragment fragment, int targetId, View resView, TargetView.AddType addType) {
        if (fragment == null || fragment.getActivity() == null) return;
        if (!mMapList.containKey(fragment)) return;
        TargetView view = getListTargetView(mMapList.getList(fragment), targetId);
        if (view == null) return;
        view.removeViewFromOriginal(resView, addType);
        removeListMap(fragment, view);
    }

    public static void removeViewFromOriginal(Fragment fragment, View targetView, int resId, TargetView.AddType addType) {
        if (fragment == null || fragment.getActivity() == null) return;
        if (!mMapList.containKey(fragment)) return;
        TargetView view = getListTargetView(mMapList.getList(fragment), targetView);
        if (view == null) return;
        view.removeViewFromOriginal(resId, addType);
        removeListMap(fragment, view);
    }

    public static void removeViewFromOriginal(Fragment fragment, View targetView, View resView, TargetView.AddType addType) {
        if (fragment == null || fragment.getActivity() == null) return;
        if (!mMapList.containKey(fragment)) return;
        TargetView view = getListTargetView(mMapList.getList(fragment), targetView);
        if (view == null) return;
        view.removeViewFromOriginal(resView, addType);
        removeListMap(fragment, view);
    }


    public static int getViewTimes(Fragment fragment, int targetId, View resView, TargetView.AddType addType) {
        if (fragment == null || fragment.getActivity() == null) return 0;
        if (!mMapList.containKey(fragment)) return 0;
        TargetView view = getListTargetView(mMapList.getList(fragment), targetId);
        return view.getViewTimes(resView, addType);
    }

    public static int getViewTimes(Fragment fragment, int targetId, int resId, TargetView.AddType addType) {
        if (fragment == null || fragment.getActivity() == null) return 0;
        if (!mMapList.containKey(fragment)) return 0;
        TargetView view = getListTargetView(mMapList.getList(fragment), targetId);
        return view.getViewTimes(resId, addType);
    }

    public static int getViewTimes(Fragment fragment, View targetView, View resView, TargetView.AddType addType) {
        if (fragment == null || fragment.getActivity() == null) return 0;
        if (!mMapList.containKey(fragment)) return 0;
        TargetView view = getListTargetView(mMapList.getList(fragment), targetView);
        return view.getViewTimes(resView, addType);
    }

    public static int getViewTimes(Fragment fragment, View targetView, int resId, TargetView.AddType addType) {
        if (fragment == null || fragment.getActivity() == null) return 0;
        if (!mMapList.containKey(fragment)) return 0;
        TargetView view = getListTargetView(mMapList.getList(fragment), targetView);
        return view.getViewTimes(resId, addType);
    }

    public static void removeViewTimes(Fragment fragment, int targetId, View resView, TargetView.AddType addType) {
        if (fragment == null || fragment.getActivity() == null) return ;
        if (!mMapList.containKey(fragment)) return ;
        TargetView view = getListTargetView(mMapList.getList(fragment), targetId);
        view.removeViewTimes(resView, addType);
    }

    public static void removeViewTimes(Fragment fragment, int targetId, int resId, TargetView.AddType addType) {
        if (fragment == null || fragment.getActivity() == null) return ;
        if (!mMapList.containKey(fragment)) return ;
        TargetView view = getListTargetView(mMapList.getList(fragment), targetId);
        view.removeViewTimes(resId, addType);
    }

    public static void removeViewTimes(Fragment fragment, View targetView, View resView, TargetView.AddType addType) {
        if (fragment == null || fragment.getActivity() == null) return ;
        if (!mMapList.containKey(fragment)) return ;
        TargetView view = getListTargetView(mMapList.getList(fragment), targetView);
        view.removeViewTimes(resView, addType);
    }

    public static void removeViewTimes(Fragment fragment, View targetView, int resId, TargetView.AddType addType) {
        if (fragment == null || fragment.getActivity() == null) return ;
        if (!mMapList.containKey(fragment)) return ;
        TargetView view = getListTargetView(mMapList.getList(fragment), targetView);
        view.removeViewTimes(resId, addType);
    }


    /**
     * Parent Activity
     */
    public static void addParent(Activity activity, View targetView, TargetView.LayoutType layoutType) {
        if (activity == null) return;
        TargetView view = getTargetView(activity, targetView);
        view.addParent(layoutType);
    }

    public static void addParent(Activity activity, int targetView, TargetView.LayoutType layoutType) {
        if (activity == null) return;
        TargetView view = getTargetView(activity, targetView);
        view.addParent(layoutType);
    }

    public static void removeParent(Activity activity, View targetView) {
        if (activity == null) return;
        if (!mMapList.containKey(activity)) return;
        TargetView view = getListTargetView(mMapList.getList(activity), targetView);
        if (view == null) return;
        view.removeParent();
        removeListMap(activity, view);
    }

    public static void removeParent(Activity activity, int targetId) {
        if (activity == null) return;
        if (!mMapList.containKey(activity)) return;
        TargetView view = getListTargetView(mMapList.getList(activity), targetId);
        if (view == null) return;
        view.removeParent();
        removeListMap(activity, view);
    }

    /**
     * Parent Fragment
     */

    public static void addParent(Fragment fragment, View targetView, TargetView.LayoutType layoutType) {
        if (fragment == null || fragment.getActivity() == null) return;
        TargetView view = getTargetView(fragment, targetView);
        view.addParent(layoutType);
    }

    public static void addParent(Fragment fragment, int targetView, TargetView.LayoutType layoutType) {
        if (fragment == null || fragment.getActivity() == null) return;
        TargetView view = getTargetView(fragment, targetView);
        view.addParent(layoutType);
    }

    public static void removeParent(Fragment fragment, View targetView) {
        if (fragment == null || fragment.getActivity() == null) return;
        if (!mMapList.containKey(fragment)) return;
        TargetView view = getListTargetView(mMapList.getList(fragment), targetView);
        if (view == null) return;
        view.removeParent();
        removeListMap(fragment, view);

    }

    public static void removeParent(Fragment fragment, int targetId) {
        if (fragment == null || fragment.getActivity() == null) return;
        if (!mMapList.containKey(fragment)) return;
        TargetView view = getListTargetView(mMapList.getList(fragment), targetId);
        if (view == null) return;
        view.removeParent();
        removeListMap(fragment, view);
    }


    /**
     * 移除Map和List中的view
     */
    private static void removeListMap(Object key, TargetView view) {
        if (!view.hasParent() && view.size() == 0) {
            mMapList.removeValue(key, view);
        }
    }

    /**
     * 不使用时必须调用清除使用过得view
     *
     * @param obj activity或者fragment
     */
    public static void clear(Object obj) {
        mMapList.clearList(obj);
    }



    /**
     * 打印信息
     */
    public static void print(Activity activity, int targetId) {
        if (activity == null) return;
        TargetView view = getTargetView(activity, targetId);
        view.print();
    }

    public static void print(Activity activity, View targetView) {
        if (activity == null) return;
        TargetView view = getTargetView(activity, targetView);
        view.print();
    }

    public static void print(Fragment fragment, int targetId) {
        if (fragment == null) return;
        TargetView view = getTargetView(fragment, targetId);
        view.print();
    }

    public static void print(Fragment fragment, View targetView) {
        if (fragment == null) return;
        TargetView view = getTargetView(fragment, targetView);
        view.print();
    }
}
