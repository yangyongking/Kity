package me.windleafy.kity.android.utils;

import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import me.windleafy.kity.java.judge.JudgeKit;


public class ViewGroupKit {

    private static final String TAG = "ViewGroupKit";

    private ViewGroupKit() {
        /**cannot be instantiated **/
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static List<Object> getAllChild(View view) {
        List<Object> list = new LinkedList<>();
        if (view instanceof ViewGroup && ((ViewGroup) view).getChildCount() > 0) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View child = ((ViewGroup) view).getChildAt(i);
                if (child instanceof ViewGroup && ((ViewGroup) child).getChildCount() > 0) {
                    list.add(getAllChild(child)); //递归
                } else {
                    list.add(child);
                }
            }
        } else {
            list.add(view);
        }
        return list;
    }

    public static Map<View, Object> getAllChildMap(View view) {
        Map<View, Object> map = new HashMap<>();
        if (view instanceof ViewGroup && ((ViewGroup) view).getChildCount() > 0) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View child = ((ViewGroup) view).getChildAt(i);
                if (child instanceof ViewGroup && ((ViewGroup) child).getChildCount() > 0) {
                    map.put(view, getAllChildMap(child)); //递归
                } else {
                    map.put(view, child);
                }
            }
        } else {
            map.put(view, view);
        }
        return map;
    }

    public static JSONObject getAllChildJsonObject(View view) {
        JSONObject json = new JSONObject();
        if (view instanceof ViewGroup && ((ViewGroup) view).getChildCount() > 0) {
            JSONObject item = new JSONObject();
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View child = ((ViewGroup) view).getChildAt(i);
                if (child instanceof ViewGroup && ((ViewGroup) child).getChildCount() > 0) {
                    item.put(view.getClass().getSimpleName(), getAllChildJsonObject(child));//递归
                } else {
                    item.put(view.getClass().getSimpleName(), child);
                }
            }
            json.put(view.getClass().getSimpleName(), item);
        } else {
            json.put(view.getClass().getSimpleName(), view);
        }
        return json;
    }

    public static String getAllChildJsonString(View view) {
        return getAllChildJsonObject(view).toString();
    }

    /**
     * 打印
     */
    public static void printAllChild(View view) {
        printAllChild(view, false);
    }

    public static void printAllChild(View view, boolean detail) {
        printAllChild(view, detail, 6);
    }

    public static void printAllChild(View view, boolean detail, int maxLevel) {
        Class[] classes = new Class[]{RecyclerView.class, ListView.class};
        printAllChild(view, detail, maxLevel, classes);
    }

    public static void printAllChild(View view, boolean detail, int maxLevel, Class<?>... notShowChildClass) {
        printAllChild(view, 0, maxLevel, detail, notShowChildClass);
    }

    public static void printAllChild(View view, int level, int maxLevel, boolean detail, Class<?>... notShowChildClass) {
        if (level >= maxLevel) return;
        printViewInfo(view, level, detail);
        if (view instanceof ViewGroup && ((ViewGroup) view).getChildCount() > 0) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View child = ((ViewGroup) view).getChildAt(i);
                if (!JudgeKit.contain(child.getClass(), notShowChildClass)
                        && child instanceof ViewGroup
                        && ((ViewGroup) child).getChildCount() > 0) {
                    printAllChild(child, level + 1, maxLevel, detail, notShowChildClass); //递归
                } else {
                    printViewInfo(child, level + 1, detail);
                }
            }
        }
    }

    private static void printViewInfo(View view, int level, boolean detail) {
        String space = "";
        for (int j = 0; j < level; j++) {
            space += "  ";
        }

        StringBuilder buffer = new StringBuilder();
        buffer.append("[");
        buffer.append(level).append(", ");
        if (detail) {
            buffer.append(view.toString());
        } else {
            buffer.append(view.getClass().getSimpleName()).append(", ");
            buffer.append("#").append(Integer.toHexString(System.identityHashCode(view))).append(", ");
            buffer.append("{").append(view.getId() != View.NO_ID ? view.getResources().getResourceEntryName(view.getId()) : "").append("}, ");
        }
        buffer.append("[").append(getViewLength(view.getLayoutParams().width)).append(",").append(getViewLength(view.getLayoutParams().height)).append("]").append(", ");
        buffer.append(view.getVisibility() == View.VISIBLE ? "VISIBLE" : view.getVisibility() == View.GONE ? "GONE" : "INVISIBLE").append(", ");
        buffer.append("]");

//        LogKit.d(TAG, space + buffer);


        switch (level) {
            case 0:
                LogKit.e(TAG, space + buffer);
                break;
            case 1:
                LogKit.w(TAG, space + buffer);
                break;
            case 2:
                LogKit.d(TAG, space + buffer);
                break;
            case 3:
                LogKit.i(TAG, space + buffer);
                break;
            default:
                LogKit.v(TAG, space + buffer);
                break;
        }


    }

    private static String getViewLength(int length) {
        switch (length) {
            case ViewGroup.LayoutParams.MATCH_PARENT:
                return "match";
            case ViewGroup.LayoutParams.WRAP_CONTENT:
                return "wrap";
            default:
                return length + "";
        }
    }

}